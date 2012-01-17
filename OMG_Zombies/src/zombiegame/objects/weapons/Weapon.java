package zombiegame.objects.weapons;

import zombiegame.engine.Field;
import zombiegame.objects.Item;
import zombiegame.objects.Usable;
import zombiegame.objects.Wearable;
import zombiegame.objects.micellaneous.Miscellaneous;
import zombiegame.people.Character;
import zombiegame.people.Player;

/**
 * The Class of the weapon available in the game
 * 
 * @author gaubert
 * 
 */
public abstract class Weapon extends Item implements Usable, Wearable {

        private static final String TYPE = "WEAPON";

        private Miscellaneous usedWith = null;

        /**
         * Constructor for a weapon, will not be used directly
         * 
         * @param ammo
         *                number of ammunition for this weapon
         */
        public Weapon(int ammo) {
                super(ammo);
        }

        @Override
        public boolean Use(Character character, Field field) {
                if (usedWith == null) {
                        super.loseUses();
                } else {
                        usedWith.loseUses();
                        if (usedWith.getUses() == 0) {
                                usedWith = null;
                        }
                }
                return false;
        }

        /**
         * Set the item to be used with this weapon
         * 
         * @param item
         */
        public void useWith(Miscellaneous item) {
                this.usedWith = item;
        }

        public boolean isShotgun() {
                return false;
        }

        public boolean isWoodenStick() {
                return false;
        }

        public boolean isLiquidNitrogen() {
                return false;
        }

        public boolean isWeapon() {
                return true;
        }

        /**
         * Get a string representing the type of the object
         * 
         * @return
         */
        @Override
        public String getType() {
                return TYPE;
        }

        public boolean usedWith(Miscellaneous m) {
                return (this.usedWith != null && this.usedWith.getType().equals(m.getType()));
        }
        
        public void select(Player p){
                p.setWeapon(this);
        }
}
