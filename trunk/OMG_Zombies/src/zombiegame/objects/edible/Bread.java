package zombiegame.objects.edible;

import zombiegame.people.Character;
import zombiegame.people.Human;

/**
 * The bread, the most basic food in the world
 * 
 * @author gaubert
 * 
 */
public class Bread extends Edible {

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
        public void Use(Character character) {
                super.Use(character);

                character.increaseHealthPoints(5);
                try {
                        ((Human) character).eatFood();
                } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("the character can't eat because he is not human");
                }

        }
}
