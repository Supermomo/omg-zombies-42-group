package zombiegame.objects.micellaneous;

import zombiegame.objects.weapons.Weapon;

/**
 * Class for a silver bullet object <BR>
 * extends Miscellaneous and is usable with a shotgun
 * @author gaubert
 *
 */
public class SilverBullet extends Miscellaneous{
        
        static final int numberOfBullets=4;
        
        public SilverBullet(){
                super(SilverBullet.numberOfBullets);     
        }

        /**
         * return whether or not the current object is usable with the weapon
         */
        public boolean isUsableWith(Weapon item) {
                return item.isShotgun();
        }  
}
