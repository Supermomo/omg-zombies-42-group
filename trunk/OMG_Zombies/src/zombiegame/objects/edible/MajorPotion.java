package zombiegame.objects.edible;

import zombiegame.people.Character;

/**
 * Class for the Major potions
 * @author gaubert
 *
 */
public class MajorPotion extends Edible{

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
        
        public boolean isIncreasingHp(){
                return true;
        }

}
