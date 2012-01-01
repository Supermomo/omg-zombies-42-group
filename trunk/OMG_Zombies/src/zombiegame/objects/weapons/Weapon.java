package zombiegame.objects.weapons;

import zombiegame.objects.Item;
import zombiegame.objects.Usable;
import zombiegame.objects.micellaneous.Miscellaneous;
import zombiegame.people.Character;

/**
 * The Class of the weapon available in the game
 * 
 * @author gaubert
 * 
 */
public abstract class Weapon extends Item implements Usable {

        private Miscellaneous usedWith = null;

        /**
         * Constructor for a weapon, will not be used directly
         * @param ammo number of ammunition for this weapon
         */
        public Weapon(int ammo) {
                super(ammo);
        }

        @Override
        public void Use(Character character) {
                if (usedWith == null) {
                        super.loseUses();
                } else {
                        usedWith.loseUses();
                        if (usedWith.getUses() == 0) {
                                usedWith = null;
                        }
                }

        }

        /**
         * Set the item to be used with this weapon
         * @param item
         */
        public void useWith(Miscellaneous item) {
                this.usedWith = item;
        }
        
        public boolean isShotgun(){
                return false;
        }
        
        public boolean isWoodenStick(){
                return false;
        }
        
        public boolean isLiquidNitrogen(){
                return false;
        }
        
        public boolean isWeapon(){
                return true;
        }

}
