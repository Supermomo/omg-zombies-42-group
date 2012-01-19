package zombiegame.people;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;

import zombiegame.engine.Field;
import zombiegame.engine.FieldFrame;
import zombiegame.engine.Location;
import zombiegame.engine.Simulator;
import zombiegame.objects.Item;
import zombiegame.objects.edible.Edible;
import zombiegame.objects.micellaneous.Miscellaneous;
import zombiegame.objects.weapons.Weapon;

/**
 * Human class, derives from Character
 * 
 * @author pylaffon
 * 
 */
public class Human extends Character {

        /** false, until a vampire bites this human */
        private boolean hasBeenBittenByVamp;

        /** false, until a werewolf bites this human */
        private boolean hasBeenBittenByLycan;

        /** the human will lose health if he's too hungry */
        private int turnsSinceLastMeal;

        protected Weapon weapon;
        protected Edible edible;
        protected Miscellaneous item;

        /**
         * Constructor of Human class. At the beginning of the game, humans just
         * had dinner, and have not been bitten yet.
         * 
         * @param name
         *                name of the character
         * @param healthPoints
         *                initial HP
         * @param field
         */
        public Human(String name, int healthPoints) {
                super(name, healthPoints);
                hasBeenBittenByVamp = false;
                hasBeenBittenByLycan=false;
                turnsSinceLastMeal = 0;
                weapon = null;
                edible = null;
                item = null;
        }

        public boolean getHasBeenBittenByVamp() {
                return hasBeenBittenByVamp;
        }

        public void setHasBeenBittenByVamp(boolean hasBeenBitten, JTextArea cons) {
                if(hasBeenBitten){
                        this.say("I have been bitten by a vampire", cons);
                }
                this.hasBeenBittenByVamp = hasBeenBitten;
        }

        /**
         * Method triggered on each character at the end of each turn. increase
         * the turns since last meal turn into werewolf or vampire if he has
         * been bitten by one
         */
        public void endOfTurn(Field field) {
                // Increment the number of turns since the last time the human
                // ate
                turnsSinceLastMeal++;
                // If the human is too hungry, he will lose health...
                if (turnsSinceLastMeal > 3) {
                        healthPoints -= 2;
                }

                if (super.healthPoints > 0 && hasBeenBittenByVamp) {
                        Vampire vamp = turnIntoVampire();
                        vamp.setLocation(this.location, field.getConsolePanel());
                        field.clear(this.location);
                        field.place(vamp, vamp.location);
                        vamp.say("I'm a vampire now", field.getConsolePanel());
                } else if (super.healthPoints > 0 && hasBeenBittenByLycan) {
                        Werewolf w = turnIntoWerewolf();
                        w.setLocation(this.location, field.getConsolePanel());
                        field.clear(this.location);
                        field.place(w, w.location);
                        w.say("I'm a lycan now", field.getConsolePanel());
                }

        }

        /**
         * the encounter between this character and c Greet the encountered
         * character if he is human Use weapon if he has one and if the
         * encountered character is an enemy Eat something if he need to
         */
        public boolean encounterCharacter(Character c, Field field) {

                boolean result = false;
                if (edible != null) {
                        if (c.isVampire() && edible.isCureVamp()) {
                                edible.Use(c, field);
                                result = true;
                        } else if (c.isWerewolf() && edible.isCureLycan()) {
                                edible.Use(c, field);
                                result = true;
                        } else if (c.isZombie() && edible.isCureZomb()) {
                                edible.Use(c, field);
                                result = true;
                        } else if (edible.isIncreasingHp() && super.getHealthPoints() < 30) {
                                edible.Use(this, field);
                        }

                        if (edible.getUses() <= 0) {
                                edible = null;
                        }

                }
                if (this.item != null) {
                        if (this.item.getUses() <= 0) {
                                item = null;
                        }
                }

                if (isArmed() && !c.isHuman() && !c.defend(this,field)) {
                        result = weapon.Use(c, field);
                        say("I use a "+weapon.getType()+" on "+c.getName(), field.getConsolePanel());
                        if (weapon.getUses() <= 0) {
                                weapon = null;
                        }
                        this.say("humm...may be i shoulden't have done that...", field.getConsolePanel());
                } else if (c.isHuman()) {
                        this.say("Hi " + c.getName() + "...what's up ?", field.getConsolePanel());
                        baby(field);

                } else {
                        //result=true;
                        this.say("Go away " + c.getName() + " before i start to...humm...beg you to leave me alive ???", field.getConsolePanel());
                }
                return result;
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
                        if(c.isEvilCharacter()){
                                super.defend(c, field);
                                boolean result = false;
                                if (edible != null) {
                                        if (c.isVampire() && edible.isCureVamp()) {
                                                edible.Use(c, field);
                                                result = true;
                                        } else if (c.isWerewolf() && edible.isCureLycan()) {
                                                edible.Use(c, field);
                                                result = true;
                                        } else if (c.isZombie() && edible.isCureZomb()) {
                                                edible.Use(c, field);
                                                result = true;
                                        } else if (edible.isIncreasingHp() && super.getHealthPoints() < 30) {
                                                edible.Use(this, field);
                                        }

                                        if (edible.getUses() <= 0) {
                                                edible = null;
                                        }

                                }
                                if (this.item != null) {
                                        if (this.item.getUses() <= 0) {
                                                item = null;
                                        }
                                }

                                if (isArmed() && !c.isHuman()) {
                                        result = weapon.Use(c, field);
                                        say("I use a "+weapon.getType()+" on "+c.getName()+" result : "+result, field.getConsolePanel());
                                        if (weapon.getUses() <= 0) {
                                                weapon = null;
                                        }
                                        this.say("humm...may be i shoulden't have done that...", field.getConsolePanel());
                                } else if (c.isHuman()) {
                                        this.say("Hi " + c.getName() + "...what's up ?", field.getConsolePanel());
                                        baby(field);

                                } else {
                                        //result=true;
                                        this.say("There is nothing I can do", field.getConsolePanel());
                                }
                                return result;
                        }
                        this.say("I can't defend myself against "+c.getName(), field.getConsolePanel());
                        return false;
        }

        /**
         * wether or not this character is human
         * 
         * @return
         */
        public boolean isHuman() {
                return true;
        }

        /**
         * Whether or not this human has a weapon
         * 
         * @return true if armed
         */
        public boolean isArmed() {
                return weapon != null;
        }

        /**
         * Create a baby
         * 
         */
        public boolean baby(Field field) {

                boolean res = false;
                Random rand = new Random();
                if (rand.nextInt(5) == 4) {
                        List<Nom> noms = new LinkedList<Nom>();
                        for (int i = 0; Nom.values().length > i; i++) {
                                noms.add(Nom.values()[i]);
                        }

                        Collections.shuffle(noms, rand);
                        List<Location> free = field.getFreeAdjacentLocations(this.location);
                        if (free.size() != 0) {
                                field.place(new Human(noms.get(0).toString(), FieldFrame.HP_HUMANS), free.get(0));
                        }
                        res = true;
                }
                return res;
        }

        /**
         * Transform this human who has been bitten, into a blood-thirsty
         * vampire.
         * 
         * @return a new object of class Vampire, with the same name and
         *         healthpoints
         * 
         *         as this human; the new vampire is immediately thirsty
         */
        public Vampire turnIntoVampire() {
                Vampire v = new Vampire("(Vamp)" + super.name, super.healthPoints);
                v.justPlayed();
                return v;
        }

        /**
         * Transform the human that has been bitten by a werewolf into one of
         * them
         * 
         * @return a new object of class Werewolf, with the same name and
         *         healthpoints
         */
        public Werewolf turnIntoWerewolf() {
                Werewolf w = new Werewolf("(Wolf)" + super.name, super.healthPoints);
                w.justPlayed();
                return w;
        }

        /**
         * Transform the human into a Zombie
         * 
         * @return a new object of class Zombie, with the same name and
         *         healthpoints
         */
        public Zombie turnIntoZombie() {
                System.out.println(getName()+" is turning into a zombie");
                Zombie z = new Zombie("(Zomb)" + super.name, FieldFrame.HP_ZOMBIES);
                z.justPlayed();
                return z;
        }

        /**
         * perform the action of eating food, set the trunsSinceLastMeal to 0
         */
        public void eatFood() {
                this.turnsSinceLastMeal = 0;
        }

        /**
         * @param hasBeenBittenByLycan
         *                the hasBeenBittenByLycan to set
         */
        public void setHasBeenBittenByLycan(boolean hasBeenBittenByLycan, JTextArea cons) {
                if(hasBeenBittenByLycan){
                        this.say("I have been bitten by a Lycan", cons);
                }
                this.hasBeenBittenByLycan = hasBeenBittenByLycan;
        }

        /**
         * @return the hasBeenBittenByLycan
         */
        public boolean getHasBeenBittenByLycan() {
                return hasBeenBittenByLycan;
        }

        /**
         * pick up an object Replace the current object by the new one associate
         * the objects if possible if the object find is already in the
         * inventory, add the found one to the possessed one
         */
        public void pickUpObject(Field fieldObj, Location loc) {

                if (fieldObj.getObjectAt(loc) != null) {
                        Item it = null;
                        try {
                                it = (Item) fieldObj.getObjectAt(loc);

                                if (this.edible != null && it.getType().equals(this.edible.getType())) {
                                        this.edible.addUses(it.getUses());
                                } else if (this.item != null && it.getType().equals(this.item.getType())) {
                                        this.item.addUses(it.getUses());
                                } else if (this.weapon != null && it.getType().equals(this.weapon.getType())) {
                                        this.weapon.addUses(it.getUses());
                                } else if (it.isEdible()) {
                                        fieldObj.placeItem(this.edible, loc.getRow(), loc.getCol());
                                        this.edible = (Edible) it;
                                } else if (it.isMiscellaneous()) {
                                        fieldObj.placeItem(this.item, loc.getRow(), loc.getCol());
                                        this.item = (Miscellaneous) it;
                                } else if (it.isWeapon()) {
                                        fieldObj.placeItem(this.weapon, loc.getRow(), loc.getCol());
                                        this.weapon = (Weapon) it;
                                }

                                if (weapon != null && item != null) {
                                        if (item.isUsableWith(weapon)) {
                                                weapon.useWith(item);
                                        }
                                }

                                say("I just picked up an object", fieldObj.getConsolePanel());

                        } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Fail to pick up the object");
                        }

                }
        }

        /**
         * Get the weapon used by this human
         * 
         * @return null if there is no weapon, a weapon object if there is
         */
        public Weapon getWeapon() {
                return this.weapon;
        }
        
        public void setWeapon(Weapon w){
                weapon=w;
        }

        public Miscellaneous getItem() {
                return this.item;
        }
        
        public Edible getEdible(){
                return edible;
        }

}
