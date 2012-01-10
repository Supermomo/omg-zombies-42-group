package zombiegame.objects;

import zombiegame.objects.weapons.Weapon;

/**
 * Interface of a not usable item Still, you can combine this item with some
 * weapon
 * 
 * @author gaubert
 * 
 */
public interface Unusable {

        /**
         * Test if the Weapon can be used with the current unusable item
         * 
         * @param item
         * @return true if it can be used with this weapon
         */
        public boolean isUsableWith(Weapon item);
}
