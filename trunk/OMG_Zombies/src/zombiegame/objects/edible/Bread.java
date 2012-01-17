package zombiegame.objects.edible;

import zombiegame.engine.Field;
import zombiegame.objects.Wearable;
import zombiegame.people.Character;
import zombiegame.people.Human;
import zombiegame.people.Player;

/**
 * The bread, the most basic food in the world
 * 
 * @author gaubert
 * 
 */
public class Bread extends Edible implements Wearable{

        private static final String TYPE = "BREAD";

        private static final int breadUses = 4;

        /**
         * create a bread object that can be eat in four parts
         */
        public Bread() {
                super(Bread.breadUses);
        }

        /**
         * HUMAN : Use the bread to regain some healthPoints and stop being
         * hungry <BR>
         * OTHER : lose some healthPoints, the bread is not edible for evil
         * character
         */
        public boolean Use(Character character, Field field) {
                
                character.increaseHealthPoints(5);
                try {
                        ((Human) character).eatFood();
                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("the character can't eat because he is not human");
                }
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
