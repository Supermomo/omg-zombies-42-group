package zombiegame.people;

import javax.swing.JTextArea;


/**
 * Mad zombie class, derive from zombie just like a zombie but is even more
 * hungry for brain and does more damage
 * 
 * @author gaubert
 * 
 */
public class MadZombie extends Zombie {

        /**
         * create a mad zombie just like a regular zombie
         * 
         * @param name
         * @param healthPoints
         */
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
        protected boolean attack(Character c, JTextArea cons) {
                int hp = c.getHealthPoints();
                super.attack(c, cons);
                if (hp != c.getHealthPoints()) {
                        c.reduceHealthPoints(20);
                        return true;
                }
                return false;
        }

}
