package zombiegame;

public class EvilCharacter extends Character{

        private boolean stun=false;
        
        public EvilCharacter(String name, int healthPoints) {
                super(name, healthPoints);
        }
        
        /**
         * turn an evil character, whatever he is, into a regular human
         * Keep the same name and the same healthPoints
         * @return the Human that have been created
         */
        public Human turnBackIntoHumain(){
                return new Human(name, healthPoints);
        }

        /**
         * @param stun the stun to set
         */
        public void setStun(boolean stun) {
                this.stun = stun;
        }

        /**
         * @return the stun
         */
        public boolean isStun() {
                return stun;
        }

        /**
         * wether or not the character is an evil character
         * @return
         */
        public boolean isEvilCharacter(){
                return true;
        }
}
