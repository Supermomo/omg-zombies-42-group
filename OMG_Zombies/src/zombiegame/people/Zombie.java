package zombiegame.people;

import javax.swing.JTextArea;

import zombiegame.engine.Field;
import zombiegame.engine.FieldFrame;
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
        public boolean encounterCharacter(Character c, Field field) {

                if(!c.defend(this, field))
                {
                        attack(c, field.getConsolePanel());
                }
                return false;
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
         * @return
         */
        protected boolean attack(Character c, JTextArea cons) {
                boolean attack = false;
                if (c.isHuman() || c.isVampire() && Simulator.GenerateRandomBoolean()) {
                        super.attack(c, cons);
                        c.reduceHealthPoints(5);
                        attack = true;
                }
                return attack;
        }

        public MadZombie turnIntoMadZombie() {
                String name = null;
                name = super.name;
                if (!name.startsWith("(Mad)")) {
                        name = "(Mad)" + super.name;
                }
                MadZombie mz = new MadZombie(name, FieldFrame.HP_ZOMBIES + 20);
                mz.justPlayed();
                return mz;
        }

        /**
         * Has one out of two chance to perform the usual action method
         * 
         */
        public void action(Field field, Field fieldObj) {
                if (Simulator.GenerateRandomBoolean()) {
                        super.action(field);
                }
        }

}
