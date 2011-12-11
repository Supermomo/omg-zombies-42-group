package zombiegame;

public class Werewolf extends EvilCharacter{

        public Werewolf(String name, int healthPoints) {
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
