package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import zombiegame.engine.Location;
import zombiegame.people.Character;
import org.junit.Before;
import org.junit.Test;

public class TestCharacter {

        private Character c;
        private String name = "toto";
        private int hp = 50;

        @Before
        public void setUp() throws Exception {
                c = new Human(name, 50);
        }

        @Test
        public void testGetName() {
                assertEquals(name, c.getName());
        }

        @Test
        public void testGetHealthPoints() {
                assertEquals(hp, c.getHealthPoints());
        }

        @Test
        public void testPlay() {
                c.justPlayed();
                assertFalse(c.canPlay());
                c.setPlay();
                assertTrue(c.canPlay());
        }

        @Test
        public void testIsHuman() {
                assertTrue(c.isHuman());
                // Don't forget that this character has been created as a Human
        }

        @Test
        public void testIsZombie() {
                assertFalse(c.isZombie());
        }

        @Test
        public void testIsVampire() {
                assertFalse(c.isVampire());
        }

        @Test
        public void testIsWerewolf() {
                assertFalse(c.isWerewolf());
        }

        @Test
        public void testIsEvilCharacter() {
                assertFalse(c.isEvilCharacter());
        }

        @Test
        public void testIsWerewolfCrew() {
                assertFalse(c.isWerewolfCrew());
        }

        @Test
        public void testReduceHealthPoints() {
                int h = c.getHealthPoints();
                c.reduceHealthPoints(5);
                assertEquals(h - 5, c.getHealthPoints());
                c.reduceHealthPoints(c.getHealthPoints() * 2);
                assertEquals(0, c.getHealthPoints());
        }

        @Test
        public void testIncreaseHealthPoints() {
                int h = c.getHealthPoints();
                c.increaseHealthPoints(5);
                assertEquals(h + 5, c.getHealthPoints());
        }

        @Test
        public void testLocation() {
                c.setLocation(new Location(1, 1), new JTextArea());
                assertEquals(1, c.getLocation().getRow());
                assertEquals(1, c.getLocation().getCol());
        }

}
