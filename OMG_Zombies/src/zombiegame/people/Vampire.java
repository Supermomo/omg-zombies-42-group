package zombiegame.people;

import java.util.List;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.engine.Simulator;

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
         * the encounter between this character and c attack c
         * if the encountered is a vampire, take a bite of his blood to become more powerful
         */
        public void encounterCharacter(Character c, Field field) 
        {
            if(c.isVampire())
            {
                this.increaseHealthPoints(5);
            }
            else
            {
                if(c.isHuman() && this.getIsThirsty())
                {
                    this.bite((Human)c);
                }
                else
                {
                    attack(c);
                }
                    
            }
        }

        /**
         * attack a character
         * 
         * @param c
         */
        protected void attack(Character c) {
                super.attack(c);
                c.reduceHealthPoints(10);
        }

        /**
         * Method triggered on each character at the end of each turn.
         */
        public void endOfTurn(Field field) {
                // The vampire has 1/6 chance of becoming thirsty, if he is not
                // already
                if (isThirsty || (Simulator.GenerateRandomBoolean() && Simulator.GenerateRandomBoolean() && Simulator.GenerateRandomBoolean())) {
                        isThirsty = true;
                        say("I am thirsty now!!");
                }
        }

        /**
         * Method called when a vampire decides to bite a human
         * 
         * @param h
         *                Human who gets bitten by this vampire
         */
        public void bite(Human h) {
                // The human has no way to escape. He gets bitten.
                h.setHasBeenBittenByVamp(true);
                say("I have bitten you, " + h.getName() + "!");
                // Vampire is not thirsty anymore
                isThirsty = false;
        }
        
        /**
         * Do the best move for the character
         * @param field
         * @return null if no best location have been found
         */
        public Location bestMove(Field field){
                Location dest=null;
                List<Location> loc=field.adjacentLocations(this.location);
                Location human=null;
                Location vamp=null;
                
                for(Location l : loc){
                        if(field.getObjectAt(l)!= null &&((Character)field.getObjectAt(l)).isHuman()){
                                human=l;
                        }
                        else if(field.getObjectAt(l)!= null &&((Character)field.getObjectAt(l)).isVampire()){
                                vamp=l;
                        }
                }
                if(human!=null){
                        dest=human;;
                }
                else if(field.getFreeAdjacentLocations(location)!=null){
                        dest=field.getFreeAdjacentLocations(location).get(0);
                }
                else if(vamp!=null){
                        dest=vamp;
                }

                
                return dest;
        }
}
