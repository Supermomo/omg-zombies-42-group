package zombiegame.people;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
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
        public Human(String name, int healthPoints) {
                super(name, healthPoints);
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
        public void endOfTurn(Field field) {
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
         * Use weapon if he has one
         */
        public void encounterCharacter(Character c,Field field) {
                
                if(isArmed()){
                        weapon.Use(c);
                        if(weapon.getAmmunition()<=0){
                                weapon=null;
                        }
                        this.say("humm...may be i shoulden't have done that...");
                }
                if(edible!=null){
                        if(c.isVampire()&& edible.isCureVamp()){
                                edible.Use(c);
                        }
                        else if(c.isWerewolf() && edible.isCureLycan()){
                                edible.Use(c);
                        }
                        else if(c.isZombie() && edible.isCureZomb()){
                                edible.Use(c);
                        }
                        else if(edible.isIncreasingHp() && super.getHealthPoints()<30){
                                edible.Use(this);
                        }
                        
                        if(edible.getUses()<=0){
                                edible=null;
                        }
                        
                }
                if(this.item!=null){
                        if(this.item.getUses()<=0){
                                item=null;
                        }
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
                Vampire v = new Vampire("(Vamp)"+super.name, super.healthPoints);
                return v;
        }
        
        public Werewolf turnIntoWerewolf()
        {
            Werewolf w = new Werewolf("(Wolf)"+super.name, super.healthPoints);
            return w;
        }
        
        public Zombie turnIntoZombie()
        {
            Zombie z = new Zombie("(Zomb)"+super.name, super.healthPoints);
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
         * Replace the current object by the new one
         * associate the objects if possible
         */
        public void pickUpObject(Field fieldObj , Location loc){
                
                if(fieldObj.getObjectAt(loc)!=null){
                        Item it=null;
                        try {
                                it=(Item)fieldObj.getObjectAt(loc);
                                
                                if(it.isEdible()){
                                        fieldObj.placeItem(this.edible, loc.getRow(),loc.getCol());
                                        this.edible=(Edible)it;
                                }
                                else if(it.isMiscellaneous()){
                                        fieldObj.placeItem(this.item, loc.getRow(),loc.getCol());
                                        this.item=(Miscellaneous)it;
                                }
                                else if(it.isWeapon()){
                                        fieldObj.placeItem(this.weapon, loc.getRow(),loc.getCol());
                                        this.weapon=(Weapon)it;
                                }
                                
                                if(weapon!=null && item!=null){
                                        if(item.isUsableWith(weapon)){
                                                weapon.useWith(item);
                                        }
                                }
                                
                                System.out.println(super.getName()+"  just picked up an object");
                                
                        } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Fail to pick up the object");
                        }
                        
                        
                }
        }
}