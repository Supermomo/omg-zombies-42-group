package zombiegame.people;

import zombiegame.engine.Simulator;

/**
 * Vampire class, derives from EvilCharacter.
 * 
 * @author pylaffon
 * 
 */
public class Vampire extends EvilCharacter {

        private boolean isThirsty;

        /**
         * Create new vampire, which is not thirsty at that point
         * 
         * @param name
         * @param healthPoints
         */
        public Vampire(String name, int healthPoints) {
                super(name, healthPoints);
                this.isThirsty = false;
        }

        public boolean getIsThirsty() {
                return isThirsty;
        }

        public void setIsThirsty(boolean isThirsty) {
                this.isThirsty = isThirsty;
        }

        /**
         * wether or not this character is a vamp
         * 
         * @return
         */
        public boolean isVampire() {
                return true;
        }

        /**
         * the encounter between this character and c attack c
         */
        public void encounterCharacter(Character c) 
        {
            if(c.isVampire())
            {
                if(getHealthPoints() < 150)
                {
                    this.increaseHealthPoints(5);
                }
            }
            else
            {
                if(c.isHuman() && this.getIsThirsty())
                {
                    this.bite((Human)c);
                }
                else
                {
                    attack(c);
                }
                    
            }
        }

        /**
         * attack a character
         * 
         * @param c
         */
        protected void attack(Character c) {
                super.attack(c);
                c.reduceHealthPoints(10);
        }

        /**
         * Method triggered on each character at the end of each turn.
         */
        public void endOfTurn() {
                // The vampire has 50% chance of becoming thirsty, if he is not
                // already
                if (isThirsty || Simulator.GenerateRandomBoolean()) {
                        isThirsty = true;
                        say("I am thirsty now!!");
                }
        }

        /**
         * Method called when a vampire decides to bite a human
         * 
         * @param h
         *                Human who gets bitten by this vampire
         */
        public void bite(Human h) {
                // The human has no way to escape. He gets bitten.
                h.setHasBeenBittenByVamp(true);
                say("I have bitten you, " + h.getName() + "!");
                // Vampire is not thirsty anymore
                isThirsty = false;
        }
}
