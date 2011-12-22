package zombiegame.objects.edible;

import zombiegame.objects.Usable;
import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;
import zombiegame.people.Zombie;

/**
 * The enum enumerating all the eatable/drinkable stuffs of the game
 * 
 * @author gaubert
 *
 */
public abstract class Edible implements Usable {

        private int numberOfUses;
        
        public Edible(int use){
                this.numberOfUses=use;
        }
        
        @Override
        public void Use(Character character) {
               this.loseUses();
                
        }
              
        /**
         * @param ammunition the ammunition to set
         */
        private void loseUses() {
                this.numberOfUses--;
        }

        /**
         * @return the ammunition
         */
        public int getUses() {
                return this.numberOfUses;
        }

}
