package zombiegame.objects;

import zombiegame.objects.edible.Bread;
import zombiegame.objects.edible.CureLycan;
import zombiegame.objects.edible.CureVamp;
import zombiegame.objects.edible.CureZombie;
import zombiegame.objects.edible.MajorPotion;
import zombiegame.objects.edible.MinorPotion;
import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.objects.weapons.LiquidNitrogen;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.WoodenStick;

/**
 * Class for an item <BR>
 * Abstract superclass of every kind of item
 * 
 * @author gaubert
 * 
 */
public abstract class Item {

        private int numberOfUses;

        private static final String TYPE = "ITEM";

        public Item(int uses) {
                if (uses <= 0) {
                        RuntimeException e = new RuntimeException(
                                        "Number of uses in Item constructor should be >0");
                        e.printStackTrace();
                        throw e;
                }
                this.numberOfUses = uses;
        }

        public boolean isWeapon() {
                return false;
        }

        public boolean isEdible() {
                return false;
        }

        public boolean isMiscellaneous() {
                return false;
        }

        public void loseUses() {
                this.numberOfUses--;
        }

        public int getUses() {
                return this.numberOfUses;
        }

        /**
         * add some uses to the number of uses counter
         * 
         * @param uses
         */
        public void addUses(int uses) {
                if (uses <= 0) {
                        RuntimeException e = new RuntimeException(
                                        "Number of uses to add should be >0");
                        e.printStackTrace();
                        throw e;
                }
                this.numberOfUses += uses;
        }

        /**
         * Get a string representing the type of the object
         * 
         * @return
         */
        public String getType() {
                return TYPE;
        }
        
        public static Item getItem( String type){
                Item t=null;
                if(type.equals(new Bread().getType())){
                        t=new Bread();
                }
                else if(type.equals(new CureLycan().getType())){
                        t=new CureLycan();
                }
                else if(type.equals(new CureVamp().getType())){
                        t=new CureVamp();
                }
                else if(type.equals(new CureZombie().getType())){
                        t=new CureZombie();
                }
                else if(type.equals(new MajorPotion().getType())){
                        t=new MajorPotion();
                }
                else if(type.equals(new MinorPotion().getType())){
                        t=new MinorPotion();
                }
                else if(type.equals(new SilverBullet().getType())){
                        t=new SilverBullet();
                }
                else if(type.equals(new LiquidNitrogen().getType())){
                        t=new LiquidNitrogen();
                }
                else if(type.equals(new Shotgun().getType())){
                        t=new Shotgun();
                }
                else if(type.equals(new WoodenStick().getType())){
                        t=new WoodenStick();
                }
                return t;
        }
}
