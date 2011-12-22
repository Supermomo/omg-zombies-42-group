package zombiegame.engine;

import java.util.ArrayList;
import java.util.Random;
import zombiegame.people.Character;
import zombiegame.people.Human;
import zombiegame.people.MadZombie;
import zombiegame.people.Vampire;
import zombiegame.people.Zombie;

/**
 * Simulator for Midterm Zombiegame.
 * 
 * @author pylaffon
 * 
 */
public class Simulator {
        // Default health points for our creatures
        private static final int HP_HUMANS = 100;
        private static final int HP_VAMPIRES = 150;
        private static final int HP_ZOMBIES = 30;
        private static final int HP_WEREWOLF = 150;

        // List of characters currently in the game
        private Field field;


        /**
         * Initialize game.
         */
        public void init() {
                // Create characters
                Character h1 = new Human("Human 1", HP_HUMANS,field);
                Character h2 = new Human("Human 2", HP_HUMANS,field);
                Character v1 = new Vampire("Vampire 1", HP_VAMPIRES,field);
                Character v2 = new Vampire("Vampire 2", HP_VAMPIRES,field);
                Character z1 = new Zombie("Zombie 1", HP_ZOMBIES,field);
                MadZombie mz1 = new MadZombie("MadZombie 1", HP_ZOMBIES,field); // uncomment
                // in
                // question
                // 5b
                // Add characters to the list
                field = new Field(50,50);
                field.placeRandomly(h1);
                field.placeRandomly(h2);
                field.placeRandomly(v1);
                field.placeRandomly(v2);
                field.placeRandomly(z1);
                field.placeRandomly(mz1);
                // uncomment in question 5b
        }

        /**
         * Perform all game logic for next turn.
         */
        public void nextTurn() {
                // All characters encounter the next character in the list
                // (question 5)
                Character c = null;
                for(int i=0; i <field.getDepth();i++ )
                {
                    for(int j=0; j<field.getWidth();j++)
                    {   
                        try 
                        {
                            c = (Character)field.getObjectAt(i,j);
                        } 
                        catch (Exception e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                        if(c != null)
                        {
                            c.action();
                        }
                    }
                }
        }

        /**
         * @return the number of human characters currently in the game
         */
        public int nbHumansAlive()
        {
            int nbHumans = 0;
            Character c = null;
            for(int i=0; i <field.getDepth();i++ )
            {
                for(int j=0; j<field.getWidth();j++)
                {   
                    try 
                    {
                        c = (Character)field.getObjectAt(i,j);
                    } 
                    catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    if(c != null && c.isHuman())
                    {
                        nbHumans++;
                    }
                }
            }
            return nbHumans;
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
                // Game initialization
                Simulator sim = new Simulator();
                sim.init();
                System.out.println("Game starts with " + sim.nbHumansAlive() + " humans!");
                // Iterate until no alive human remains
                while (sim.nbHumansAlive() > 0) {
                        sim.nextTurn();
                }
                System.out.println("All humans have been eaten!");
        }

        /**
         * Generate a pseudo-random boolean.
         * 
         * @return pseudo-random boolean
         */
        public static boolean GenerateRandomBoolean() {
                Random random = new Random();
                return random.nextBoolean();
        }
}
