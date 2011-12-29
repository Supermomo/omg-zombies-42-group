package zombiegame.people;

import zombiegame.engine.Field;
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
        private boolean hasBeenBittenByVamp; // false, until a vampire bites this
        // human
        
        private boolean hasBeenBittenByLycan;
        
        private int turnsSinceLastMeal; // the human will lose health if he's
        // too hungry
        
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
        public Human(String name, int healthPoints, Field field) {
                super(name, healthPoints,field);
                hasBeenBittenByVamp = false;
                setHasBeenBittenByLycan(false);
                turnsSinceLastMeal = 0;
                weapon=null;
                edible=null;
                item=null;
        }

        // Accessors and mutators
        public boolean getHasBeenBittenByVamp() {
                return hasBeenBittenByVamp;
        }

        public void setHasBeenBittenByVamp(boolean hasBeenBitten) {
                this.hasBeenBittenByVamp = hasBeenBitten;
        }

        /**
         * Method triggered on each character at the end of each turn.
         */
        public void endOfTurn() {
                // Increment the number of turns since the last time the human
                // ate
                turnsSinceLastMeal++;
                // If the human is too hungry, he will lose health...
                if (turnsSinceLastMeal > 3) {
                        healthPoints -= 2;
                }
                
                if(hasBeenBittenByVamp)
                {
                    Vampire vamp = turnIntoVampire();
                    vamp.setLocation(this.location);
                    field.clear(this.location);
                    field.place(vamp,vamp.location);
                    this.say("I'm a vampire now");
                }
                else if(hasBeenBittenByLycan)
                {
                        Werewolf w = turnIntoWerewolf();
                        w.setLocation(this.location);
                        field.clear(this.location);
                        field.place(w,w.location);
                        this.say("I'm a vampire now");
                 }
                
                
        }

        /**
         * the encounter between this character and c
         */
        public void encounterCharacter(Character c) {
                
                if(isArmed()){
                        
                }
                else {
                        this.say("Go away " + c.getName()
                                        + " before i start to...humm...beg you to leave me alive ???");
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
         * @return true if armed
         */
        public boolean isArmed(){
                return weapon!=null;
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
                Vampire v = new Vampire("(Vamp)"+super.name, super.healthPoints,super.field);
                return v;
        }
        
        public Werewolf turnIntoWerewolf()
        {
            Werewolf w = new Werewolf("(Wolf)"+super.name, super.healthPoints,super.field);
            return w;
        }
        
        public Zombie turnIntoZombie()
        {
            Zombie z = new Zombie("(Zomb)"+super.name, super.healthPoints,super.field);
            return z;
        }
        
        /**
         * perform the action of eating food, set the trunsSinceLastMeal to 0
         */
        public void eatFood(){
                this.turnsSinceLastMeal=0;
        }

        /**
         * @param hasBeenBittenByLycan the hasBeenBittenByLycan to set
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
         * pick up an object
         */
        public void pickUpObject(){
                
        }
}
