package zombiegame.objects.micellaneous;

import zombiegame.objects.weapons.Weapon;

/**
 * Class for a silver bullet object <BR>
 * extends Miscellaneous and is usable with a shotgun
 * @author gaubert
 *
 */
public class SilverBullet extends Miscellaneous{
        
        private static final String TYPE="SILVER BULLET";
        
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
        
        /**
         * Get a string representing the type of the object
         * @return
         */
        @Override
        public String getType(){
                return TYPE;
        }
}
