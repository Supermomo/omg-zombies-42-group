package zombiegame.objects.edible;

import zombiegame.objects.Item;
import zombiegame.objects.Usable;
import zombiegame.people.Character;


/**
 * The enum enumerating all the eatable/drinkable stuffs of the game
 * 
 * @author gaubert
 *
 */
public abstract class Edible extends Item implements Usable {

        private int numberOfUses;
        
        /**
         * creator for an edible object
         * @param use
         */
        public Edible(int use){
                this.numberOfUses=use;
        }
        
        /**
         * Use the object, remaining uses is decreased
         */
        @Override
        public void Use(Character character) {
               this.loseUses();
                
        }
              
        /**
         * @param ammunition the remaining uses to set
         */
        private void loseUses() {
                this.numberOfUses--;
        }

        /**
         * @return the remaining uses
         */
        public int getUses() {
                return this.numberOfUses;
        }
        
        public boolean isEdible(){
                return true;
        }
        
        public boolean isCureVamp(){
                return false;
        }
        
        public boolean isCureZomb(){
                return false;
        }
        
        public boolean isCureLycan(){
                return false;
        }
        
        public boolean isIncreasingHp(){
                return false;
        }

}
