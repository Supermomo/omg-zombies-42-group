package zombiegame.engine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextArea;

import zombiegame.people.Character;
import zombiegame.people.Helico;
import zombiegame.people.Human;
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
        public static final int SIZE_MAP = 15;
        public static final int NB_HUMANS = 6;
        public static final int NB_VAMPIRES = 4;
        public static final int NB_ZOMBIES = 4;
        public static final int NB_WEREWOLF = 3;

        // List of characters currently in the game
        private Field field;
        private Field fieldObject;
        private FieldFrame ff;

        /**
         * Initialize game.
         */
        public void init() {
                // Create characters
                JTextArea cons = new JTextArea(200, 200);
                cons.setText("New game running");
                field = new Field(SIZE_MAP, SIZE_MAP, cons);
                fieldObject = new Field(SIZE_MAP, SIZE_MAP, cons);
                this.generatePeople(NB_VAMPIRES, NB_WEREWOLF, NB_ZOMBIES, NB_HUMANS);

                ff = new FieldFrame(field, cons);
                // ff.validate();
                // cons.repaint();
        }

        /**
         * Perform all game logic for next turn.
         */
        public void nextTurn() {

                Character c = null;

                for (int i = 0; i < field.getDepth(); i++) {
                        for (int j = 0; j < field.getWidth(); j++) {
                                /*
                                 * try { Thread.sleep(1); } catch
                                 * (InterruptedException e) { // TODO
                                 * Auto-generated catch block
                                 * e.printStackTrace(); }
                                 */

                                try {
                                        c = (Character) field.getObjectAt(i, j);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }

                                if (c != null && c.canPlay()) {
                                        c.action(field, fieldObject);
                                        c.justPlayed();
                                        c.endOfTurn(field);
                                        ff.repaint();
                                }

                        }
                }
                /*
                 * try { Thread.sleep(200); } catch (InterruptedException e) {
                 * // TODO Auto-generated catch block e.printStackTrace(); }
                 */
                Helico helic = new Helico(fieldObject);
                helic.dropItem(ff);

                field.getConsolePanel().append("\r\n FIN DU TOUR \r\n");
        }

        /**
         * @return the number of human characters currently in the game
         */
        public int nbHumansAlive() {
                int nbHumans = 0;
                Character c = null;
                for (int i = 0; i < field.getDepth(); i++) {
                        for (int j = 0; j < field.getWidth(); j++) {
                                try {
                                        c = (Character) field.getObjectAt(i, j);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }

                                if (c != null) {
                                        if (c.isHuman()) {
                                                nbHumans++;
                                        }

                                        c.setPlay();
                                }

                        }
                }
                if (nbHumans == 0) {
                        field.getConsolePanel().append("\r\nAll humans have been eaten!\r\n");
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

        public void generatePeople(int nbVamp, int nbWerewolf, int nbZombie, int nbHuman) {

                if (nbVamp < 0 || nbWerewolf < 0 || nbZombie < 0 || nbHuman < 0) {
                        throw new RuntimeException("negative integer shouldn't be a parameter");
                }

                Random rand = new Random();
                List<Nom> noms = new LinkedList<Nom>();
                for (int i = 0; Nom.values().length > i; i++) {
                        noms.add(Nom.values()[i]);
                }

                for (int i = 0; i < nbVamp; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Vampire(noms.get(0).toString(), HP_VAMPIRES));
                }

                for (int i = 0; i < nbWerewolf; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Werewolf(noms.get(0).toString(), HP_WEREWOLF));
                }

                for (int i = 0; i < nbZombie; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Zombie(noms.get(0).toString(), HP_ZOMBIES));
                }

                for (int i = 0; i < nbHuman; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Human(noms.get(0).toString(), HP_HUMANS));
                }

        }
}
