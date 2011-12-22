package zombiegame.people;

import zombiegame.engine.Simulator;

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
        
        public void encounterCharacter(Character c) 
        {
            if(c.isWerewolf())
            {
               
            }
            else
            {
                if(c.isHuman() && (c.getHealthPoints() <= 25))
                {
                    this.bite((Human)c);
                }
                else
                {
                    attack(c);
                    if(c.isHuman() && (c.getHealthPoints() <= 25))
                    {
                        this.bite((Human)c);
                    }
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
                c.reduceHealthPoints(15);
        }

        /**
         * Method triggered on each character at the end of each turn.
         */
        public void endOfTurn() {
               
        }

        /**
         * Method called when a vampire decides to bite a human
         * 
         * @param h
         *                Human who gets bitten by this vampire
         */
        public void bite(Human h) {
                // The human has no way to escape. He gets bitten.
                h.setHasBeenBittenByLycan(true);
                say("I have bitten you, " + h.getName() + "!");
        }
}
