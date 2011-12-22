package zombiegame.objects.micellaneous;

import zombiegame.objects.Unusable;
import zombiegame.objects.weapons.Weapon;

/**
 * The enum enumerating the various Unusable objects available in the game
 * 
 * @author gaubert
 * 
 */
public abstract class Miscellaneous implements Unusable {

        private int numberOfUses;
        
        public Miscellaneous(int uses){
                this.numberOfUses=uses;
        }
        
        @Override
        public boolean isUsableWith(Weapon item) {
                return false;
        }

        public void loseUses(){
                this.numberOfUses--;
        }
        
        public int getUses(){
                return this.numberOfUses;
        }
}
