package zombiegame.objects.micellaneous;

import zombiegame.objects.weapons.Weapon;

public class SilverBullet extends Miscellaneous{
        
        public SilverBullet(int number){
                super(number);     
        }

        /**
         * return whether or not the current object is usable with the weapon
         */
        public boolean isUsableWith(Weapon item) {
                return item.isShotgun();
        }  
}
