package zombiegame.people;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import zombiegame.engine.Field;
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

        private Weapon weapon;
        private Edible edible;
        private Miscellaneous item;

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
                setHasBeenBittenByLycan(false);
                turnsSinceLastMeal = 0;
                weapon = null;
                edible = null;
                item = null;
        }

        public boolean getHasBeenBittenByVamp() {
                return hasBeenBittenByVamp;
        }

        public void setHasBeenBittenByVamp(boolean hasBeenBitten) {
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

                if (hasBeenBittenByVamp) {
                        Vampire vamp = turnIntoVampire();
                        vamp.setLocation(this.location, field.getConsolePanel());
                        field.clear(this.location);
                        field.place(vamp, vamp.location);
                        vamp.say("I'm a vampire now", field.getConsolePanel());
                } else if (hasBeenBittenByLycan) {
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
        public void encounterCharacter(Character c, Field field) {

                if (edible != null) {
                        if (c.isVampire() && edible.isCureVamp()) {
                                edible.Use(c);
                        } else if (c.isWerewolf() && edible.isCureLycan()) {
                                edible.Use(c);
                        } else if (c.isZombie() && edible.isCureZomb()) {
                                edible.Use(c);
                        } else if (edible.isIncreasingHp() && super.getHealthPoints() < 30) {
                                edible.Use(this);
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
                        weapon.Use(c);
                        if (weapon.getUses() <= 0) {
                                weapon = null;
                        }
                        this.say("humm...may be i shoulden't have done that...", field
                                        .getConsolePanel());
                } else if (c.isHuman()) {
                        this.say("Hi " + c.getName() + "...what's up ?", field.getConsolePanel());
                        baby(field);

                } else {
                        this
                                        .say(
                                                        "Go away "
                                                                        + c.getName()
                                                                        + " before i start to...humm...beg you to leave me alive ???",
                                                        field.getConsolePanel());
                }
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
                                field.place(new Human(noms.get(0).toString(), Simulator.HP_HUMANS),
                                                free.get(0));
                        }
                        res=true;
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
                Zombie z = new Zombie("(Zomb)" + super.name, Simulator.HP_ZOMBIES);
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
        public void setHasBeenBittenByLycan(boolean hasBeenBittenByLycan) {
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

                                if (this.edible != null
                                                && it.getType().equals(this.edible.getType())) {
                                        this.edible.addUses(it.getUses());
                                } else if (this.item != null
                                                && it.getType().equals(this.item.getType())) {
                                        this.item.addUses(it.getUses());
                                } else if (this.weapon != null
                                                && it.getType().equals(this.weapon.getType())) {
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

}
