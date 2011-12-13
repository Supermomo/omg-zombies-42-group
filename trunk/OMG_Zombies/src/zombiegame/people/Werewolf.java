package zombiegame.people;

/**
 * Class for a werewolf
 * derive from EvilCharacter
 * 
 * @author gaubert
 *
 */
public class Werewolf extends EvilCharacter{

        /**
         * Create a new werewolf
         * just like a normal character
         * @param name
         * @param healthPoints
         */
        public Werewolf(String name, int healthPoints) 
        {
                super(name, healthPoints);
        }

        /**
         * wether or not this character is a werewolf
         * 
         * @return
         */
        public boolean isWerewolf() {
                return true;
        }
}
