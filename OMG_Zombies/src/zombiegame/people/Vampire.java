package zombiegame.people;

import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.engine.Simulator;
import zombiegame.objects.micellaneous.VampireCape;
import zombiegame.objects.weapons.WoodenStick;

/**
 * Vampire class, derives from EvilCharacter.
 * 
 * @author pylaffon
 * 
 */
public class Vampire extends EvilCharacter {

        private boolean isThirsty;

        /**
         * Create new vampire, which is not thirsty at that point
         * 
         * @param name
         * @param healthPoints
         * @param field
         */
        public Vampire(String name, int healthPoints) {
                super(name, healthPoints);
                this.isThirsty = false;
        }

        public boolean getIsThirsty() {
                return isThirsty;
        }

        public void setIsThirsty(boolean isThirsty) {
                this.isThirsty = isThirsty;
        }

        /**
         * wether or not this character is a vamp
         * 
         * @return
         */
        public boolean isVampire() {
                return true;
        }

        /**
         * the encounter between this character and c attack c if the
         * encountered is a vampire, take a bite of his blood to become more
         * powerful
         */
        public boolean encounterCharacter(Character c, Field field) {

                if (c.isVampire()) {
                        this.increaseHealthPoints(5);
                } else if(!c.defend(this, field)){
                        if (c.isHuman() && this.getIsThirsty()) {
                                this.bite((Human) c, field.getConsolePanel());                             
                        } else {
                                attack(c, field.getConsolePanel());
                        }

                }
                return false;
        }

        /**
         * The defense method, called in some occasion during encounters
         * 
         * @param c
         *                the character against the one you have to defend
         * @param field
         * @return true if the defend method is preventing the attack to happen
         */
        public boolean defend(Character c, Field field) {
                        if(c.isHuman() && ((Human)c).getWeapon()!=null && !((Human)c).getWeapon().getType().equals(new WoodenStick().getType())){                             
                                //if this a a human, we can try to suck blood from him
                                if(Simulator.GenerateRandomBoolean())
                                {
                                        super.defend(c, field);
                                        c.reduceHealthPoints(15);
                                        this.increaseHealthPoints(15);
                                        return true;
                                }                                    
                        }
                        this.say("I can't defend myself against "+c.getName(), field.getConsolePanel());
                        return false;
        }
        
        /**
         * attack a character
         * 
         * @param c
         */
        protected boolean attack(Character c, JTextArea cons) {
                super.attack(c, cons);
                c.reduceHealthPoints(10);
                return true;
        }

        /**
         * Method triggered on each character at the end of each turn.
         */
        public void endOfTurn(Field field) {
                // The vampire has 1/6 chance of becoming thirsty, if he is not
                // already
                super.endOfTurn(field);
                if (isThirsty
                                || (Simulator.GenerateRandomBoolean()
                                                && Simulator.GenerateRandomBoolean() && Simulator
                                                .GenerateRandomBoolean())) {
                        isThirsty = true;
                        say("I am thirsty now!!", field.getConsolePanel());
                }
        }

        /**
         * Method called when a vampire decides to bite a human
         * 
         * @param h
         *                Human who gets bitten by this vampire
         */
        public void bite(Human h, JTextArea cons) {
                // The human has no way to escape. He gets bitten.
                h.setHasBeenBittenByVamp(true,cons);
                say("I have bitten you, " + h.getName() + "!", cons);
                // Vampire is not thirsty anymore
                isThirsty = false;
        }

        /**
         * Do the best move for the character
         * 
         * @param field
         * @return null if no best location have been found
         */
        public Character bestMove(Field field) {
                Location dest = null;
                List<Location> loc = field.adjacentLocations(this.location);
                Character human = null;
                Character vamp = null;
                Character zomb = null;

                for (Location l : loc) {
                        if (field.getCharactertAt(l) != null
                                        && ((Character) field.getCharactertAt(l)).isHuman()) {
                                human = (Character) field.getCharactertAt(l);
                        } else if (field.getCharactertAt(l) != null
                                        && ((Character) field.getCharactertAt(l)).isZombie()) {
                                zomb = (Character) field.getCharactertAt(l);
                        } else if (field.getCharactertAt(l) != null
                                        && ((Character) field.getCharactertAt(l)).isVampire()) {
                                vamp = (Character) field.getCharactertAt(l);
                        }

                }
                if (human != null) {
                        if(seek(human)){
                                encounterCharacter(human, field);
                                endCharacter(human, field);
                        }
                        return human;
                } else {
                        wander();
                } 

                return null;
        }

        /**
         * Perform the usual character method ...just twice
         * 
         */
        @Override
        public void action(Field field) {
                super.action(field);
                if(healthPoints>0 && (field.getCharactertAt(location)!=null && ((Character)field.getCharactertAt(location)).isVampire())){
                        System.out.println("Vamp : "+super.name+" 2eme attaque");
                        super.action(field); 
                }
                else{
                        field.clearCharacter(this);
                        field.placeItem(new VampireCape(), location.getRow(), location.getCol());
                }
                
        }
}
