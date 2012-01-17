package zombiegame.objects.edible;

import zombiegame.engine.Field;
import zombiegame.objects.Wearable;
import zombiegame.people.Character;
import zombiegame.people.Player;

/**
 * Class for the Major potions
 * 
 * @author gaubert
 * 
 */
public class MajorPotion extends Edible implements Wearable{

        private static final String TYPE = "MAJOR POTION";

        private static final int potionUses = 1;

        /**
         * creator for a major potion
         */
        public MajorPotion() {
                super(MajorPotion.potionUses);
        }

        /**
         * Use the potion to use the character
         */
        public boolean Use(Character character, Field field) {
                character.increaseHealthPoints(25);
                return super.Use(character, field);
        }

        /**
         * return true if the item increase your Hp when eaten
         */
        public boolean isIncreasingHp() {
                return true;
        }

        /**
         * Get a string representing the type of the object
         * 
         * @return
         */
        @Override
        public String getType() {
                return TYPE;
        }

        @Override
        public void select(Player p) {
                Use(p, null);
        }

}
