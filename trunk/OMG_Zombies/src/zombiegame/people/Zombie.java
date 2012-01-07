package zombiegame.people;

import javax.swing.JTextArea;

import zombiegame.engine.Field;
import zombiegame.engine.Simulator;

/**
 * Zombie class, derives from EvilCharacter.
 * 
 * @author pylaffon
 * 
 */
public class Zombie extends EvilCharacter {
        /**
         * Constructor of Zombie class.
         * 
         * @param name
         *                name of the character
         * @param healthPoints
         *                initial HP
         * @param field
         */
        public Zombie(String name, int healthPoints) {
                super(name, healthPoints);
        }

        /**
         * Output a character's saying to the screen
         * 
         * @param str
         *                what the character says
         */
        public void say(String str) {
                System.out.println(name + " says: BRAIIIIIINS! \r\n");
        }

        /**
         * the encounter between this character and c attack c
         */
        public void encounterCharacter(Character c, Field field) {
                attack(c,field.getConsolePanel());
        }

        /**
         * wether or not this character is a zombie
         * 
         * @return
         */
        public boolean isZombie() {
                return true;
        }

        /**
         * wether or not this character is a MadZombie
         * 
         * @return
         */
        public boolean isMadZombie() {
                return false;
        }

        /**
         * attack the character c
         * 
         * @param c
         */
        protected void attack(Character c, JTextArea cons) {
                if (c.isHuman() || c.isVampire() && Simulator.GenerateRandomBoolean()) {
                        super.attack(c,cons);
                        c.reduceHealthPoints(5);
                }
        }

        /**
         * Method triggered on each character at the end of each turn. Unstun
         * the zombie if stun
         */
        public void endOfTurn(Field field) {
                super.setStun(false);
                // Do nothing. Zombies are useless anyway...
        }
}
