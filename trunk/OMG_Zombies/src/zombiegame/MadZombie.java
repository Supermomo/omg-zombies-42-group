package zombiegame;

public class MadZombie extends Zombie {

        public MadZombie(String name, int healthPoints) {
                super(name, healthPoints);
        }

        /**
         * wether or not this character is a MadZombie
         * 
         * @return
         */
        public boolean isMadZombie() {
                return true;
        }

        /**
         * attack as a madZombie the character c
         */
        protected void attack(Character c) {
                int hp = c.getHealthPoints();
                super.attack(c);
                if (hp != c.getHealthPoints()) {
                        c.reduceHealthPoints(20);
                }
        }

}
