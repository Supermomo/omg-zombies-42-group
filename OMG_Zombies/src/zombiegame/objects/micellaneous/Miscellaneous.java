package zombiegame.objects.micellaneous;

import zombiegame.objects.Item;
import zombiegame.objects.Unusable;
import zombiegame.objects.weapons.Weapon;

/**
 * The enum enumerating the various Unusable objects available in the game
 * 
 * @author gaubert
 * 
 */
public abstract class Miscellaneous extends Item implements Unusable {

        private static final String TYPE="MISCELLANEOUS";
        
        public Miscellaneous(int uses){
                super(uses);
        }
        
        /**
         * return whether or not the current object is usable with the weapon
         */
        @Override
        public boolean isUsableWith(Weapon item) {
                return false;
        }


        
        public boolean isMicellaneous(){
                return true;
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
