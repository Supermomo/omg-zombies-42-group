package zombiegame.people;

import zombiegame.engine.Field;

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
         * @param field 
         */
        public MadZombie(String name, int healthPoints, Field field) {
                super(name, healthPoints, field);
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
