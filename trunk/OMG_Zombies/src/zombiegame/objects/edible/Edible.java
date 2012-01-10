package zombiegame.objects.edible;

import zombiegame.engine.Field;
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

        private static final String TYPE = "EDIBLE";

        /**
         * creator for an edible object
         * 
         * @param use
         */
        public Edible(int use) {
                super(use);
        }

        /**
         * Use the object, remaining uses is decreased
         */
        public void Use(Character character, Field field) {
                super.loseUses();

        }

        public boolean isEdible() {
                return true;
        }

        public boolean isCureVamp() {
                return false;
        }

        public boolean isCureZomb() {
                return false;
        }

        public boolean isCureLycan() {
                return false;
        }

        public boolean isIncreasingHp() {
                return false;
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
}
