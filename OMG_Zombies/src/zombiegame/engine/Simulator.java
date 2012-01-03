package zombiegame.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import zombiegame.people.Character;
import zombiegame.people.Helico;
import zombiegame.people.Human;
import zombiegame.people.MadZombie;
import zombiegame.people.Nom;
import zombiegame.people.Vampire;
import zombiegame.people.Werewolf;
import zombiegame.people.Zombie;

/**
 * Simulator for Midterm Zombiegame.
 * 
 * @author pylaffon
 * 
 */
public class Simulator {
        // Default health points for our creatures
        public static final int HP_HUMANS = 100;
        public static final int HP_VAMPIRES = 150;
        public static final int HP_ZOMBIES = 30;
        public static final int HP_WEREWOLF = 150;

        // List of characters currently in the game
        private Field field;
        private Field fieldObject;
        private FieldFrame ff;

        /**
         * Initialize game.
         */
        public void init() {
                // Create characters

                field = new Field(25,25);
                fieldObject=new Field(25,25);

                /*Character h1 = new Human("Human 1", HP_HUMANS);
                Character h2 = new Human("Human 2", HP_HUMANS);
                Character h3 = new Human("Human 3", HP_HUMANS);
                Character h4 = new Human("Human 4", HP_HUMANS);
                Character h5 = new Human("Human 5", HP_HUMANS);
                Character h6 = new Human("Human 6", HP_HUMANS);
                Character h7 = new Human("Human 7", HP_HUMANS);
                Character h8 = new Human("Human 8", HP_HUMANS);
                Character v1 = new Vampire("Vampire 1", HP_VAMPIRES);
                Character v2 = new Vampire("Vampire 2", HP_VAMPIRES);
                Character z1 = new Zombie("Zombie 1", HP_ZOMBIES);
                MadZombie mz1 = new MadZombie("MadZombie 1", HP_ZOMBIES); // uncomment
                Werewolf w1 = new Werewolf("Wolf 1",HP_WEREWOLF);
                Werewolf w2 = new Werewolf("Wolf 2",HP_WEREWOLF);*/
                // in
                // question
                // 5b
                // Add characters to the list

               /* field.placeRandomly(h1);
                field.placeRandomly(h2);
                field.placeRandomly(h3);
                field.placeRandomly(h4);
                field.placeRandomly(h5);
                field.placeRandomly(h6);
                field.placeRandomly(h7);
                field.placeRandomly(h8);
                field.placeRandomly(v1);
                field.placeRandomly(v2);
                field.placeRandomly(z1);
                field.placeRandomly(mz1);
                field.placeRandomly(w1);
                field.placeRandomly(w2);*/
                // uncomment in question 5b
                
                this.generatePeople(10, 30, 1, 10);
                
                ff=new FieldFrame(field);
        }

        /**
         * Perform all game logic for next turn.
         */
        public void nextTurn() {
                // All characters encounter the next character in the list
                // (question 5)
                int heightBox = ff.getHeight() / (field.getDepth() + 2);
                int widthBox = ff.getWidth() / (field.getWidth() + 2);
                
                Character c = null;
               
                for(int i=0; i <field.getDepth();i++ )
                {
                    for(int j=0; j<field.getWidth();j++)
                    {   
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        ff.repaint();
                        
                        try 
                        {
                            c = (Character)field.getObjectAt(i,j);
                        } 
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        
                        if(c != null && c.canPlay())
                        {
                            c.action(field, fieldObject);
                            c.justPlayed();
                            c.endOfTurn(field);
                            ff.repaint();
                        }

                    }
                }
                Helico helic = new Helico(fieldObject);
                helic.dropItem(ff,widthBox,heightBox);
                
                System.out.println("\n FIN DU TOUR \n");
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
                        e.printStackTrace();
                    }
                    
                    if(c != null)
                    {
                        if(c.isHuman())
                        {
                            nbHumans++;
                        }
                        
                        c.setPlay();
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
        
        public void generatePeople(int nbVamp, int nbWerewolf, int nbZombie, int nbHuman){
                
                Random rand = new Random();
                List<Nom> noms = new LinkedList<Nom>();
                for(int i =0;Nom.values().length > i;i++)
                {
                    noms.add(Nom.values()[i]);
                }
                
                for(int i=0;i<nbVamp;i++){
                        Collections.shuffle(noms,rand);
                        this.field.placeRandomly(new Vampire(noms.get(0).toString(),HP_VAMPIRES));
                }
                
                for(int i=0;i<nbWerewolf;i++){
                        Collections.shuffle(noms,rand);
                        this.field.placeRandomly(new Werewolf(noms.get(0).toString(),HP_WEREWOLF));
                }
                
                for(int i=0;i<nbZombie;i++){
                        Collections.shuffle(noms,rand);
                        this.field.placeRandomly(new Zombie(noms.get(0).toString(),HP_ZOMBIES));
                }
                
                for(int i=0;i<nbHuman;i++){
                        Collections.shuffle(noms,rand);
                        this.field.placeRandomly(new Human(noms.get(0).toString(),HP_HUMANS));
                }
                
                
        }
}
