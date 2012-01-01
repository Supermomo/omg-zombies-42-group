package zombiegame.objects.edible;

import zombiegame.people.Character;

/**
 * Class for the Major potions
 * @author gaubert
 *
 */
public class MajorPotion extends Edible{

        private static final String TYPE="MAJOR POTION";
        
        private static final int potionUses=1;
        
        /**
         * creator for a major potion
         */
        public MajorPotion() {
                super(MajorPotion.potionUses);
        }

        /**
         * Use the potion to use the character
         */
        public void Use(Character character) {
                character.increaseHealthPoints(25);
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
