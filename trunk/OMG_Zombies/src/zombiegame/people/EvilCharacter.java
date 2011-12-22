package zombiegame.people;

import zombiegame.engine.Field;

/**
 * The class of en EvilCharacter, derive from character<BR>
 * Basically like a character but in more...mean
 * 
 * @author gaubert
 * 
 */
public abstract class EvilCharacter extends Character {

        private boolean isStun = false;

        /**
         * create an EvilCharacter just like a normal character
         * 
         * @param name
         * @param healthPoints
         * @param field 
         */
        public EvilCharacter(String name, int healthPoints, Field field) {
                super(name, healthPoints,field);
        }

        /**
         * turn an evil character, whatever he is, into a regular human Keep the
         * same name and the same healthPoints
         * 
         * @return the Human that have been created
         */
        public Human turnBackIntoHumain() {
                return new Human(name, healthPoints,super.field);
        }

        /**
         * @param stun
         *                the stun to set
         */
        public void setStun(boolean stun) {
                this.isStun = stun;
        }

        /**
         * @return the stun
         */
        public boolean isStun() {
                return isStun;
        }

        /**
         * wether or not the character is an evil character
         * 
         * @return
         */
        public boolean isEvilCharacter() {
                return true;
        }
}
