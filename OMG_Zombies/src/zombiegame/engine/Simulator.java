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
                Character h1 = new Human("Human 1", HP_HUMANS);
                Character h2 = new Human("Human 2", HP_HUMANS);
                Character v1 = new Vampire("Vampire 1", HP_VAMPIRES);
                Character v2 = new Vampire("Vampire 2", HP_VAMPIRES);
                Character z1 = new Zombie("Zombie 1", HP_ZOMBIES);
                MadZombie mz1 = new MadZombie("MadZombie 1", HP_ZOMBIES); // uncomment
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
                for (int i = 0; i < field.size(); ++i) {
                        Character c = field.get(i);
                        Character encountered = field.get((i + 1) % (field.size()));
                        if (c.getHealthPoints() > 0) {
                                c.encounterCharacter(encountered);
                                if (c.getHealthPoints() <= 0) {
                                        field.remove(i);
                                }
                                if (encountered.getHealthPoints() <= 0) {
                                        field.remove((i + 1) % (field.size()));
                                }
                        } else {
                                field.remove(i);
                        }
                }
                // Dead characters are removed from the character list
                // ... add your code here (question 6) ...
                // Each vampire (if he is thirsty) bites the first Human in the
                // list
                // who has not been bitten yet
                // ... add your code here (question 7a) ...
                int j = 0;
                boolean bite = false;
                Human h;
                Vampire v;
                Character cha;
                for (int i = 0; i < field.size(); i++) {
                        cha = field.get(i);
                        if (cha.isVampire()) {
                                v = (Vampire) cha;

                                while (j < field.size() && !bite) {
                                        if (cha.isHuman()) {
                                                h = (Human) cha;
                                                if (h.getHasBeenBittenByVamp() == false) {
                                                        bite = true;
                                                        v.bite(h);
                                                        field.set(i, h.turnIntoVampire());
                                                }
                                        }
                                        j++;
                                }
                        }

                }

                // Humans that have been bitten become vampires
                // ... add your code here (question 7b) ...
                // Perform end-of-turn actions for all characters (question 4)
                for (int i = 0; i < field.size(); ++i) {
                        Character c = field.get(i);
                        c.endOfTurn();
                }
        }

        /**
         * @return the number of human characters currently in the game
         */
        public int nbHumansAlive() {
                // Need to iterate through the list of characters
                // and count the number of humans
                int nbHumans = 0;
                for (Character character : field) {
                        if (character.isHuman()) {
                                nbHumans++;
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
