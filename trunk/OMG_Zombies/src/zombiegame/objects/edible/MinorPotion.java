package zombiegame.objects.edible;

import zombiegame.people.Character;
/**
 * class for a minor potion
 * @author gaubert
 *
 */
public class MinorPotion extends Edible{

        private static final String TYPE="MINOR POTION";
        
        private static final int potionUses=1;
        
        /**
         * create a minor potion object
         * @param use
         */
        public MinorPotion() {
                super(MinorPotion.potionUses);            
        }

        /** 
         * use the minor potion to health the charater
         */
        public void Use(Character character) {
                character.increaseHealthPoints(15);
                super.Use(character);
        }
        
        /**
         * return true if the item increase your Hp when eaten
         */
        public boolean isIncreasingHp(){
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
