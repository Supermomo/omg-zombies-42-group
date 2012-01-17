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

        /**
         * @param args
         */
        public static void main(String[] args) {
                // Game initialization
                FieldFrame f = new FieldFrame();
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
