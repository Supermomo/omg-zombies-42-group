package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;

public class TestZombie {
        Zombie z;
        Field f;

        @Before
        public void setUp() {
                z = new Zombie("Zombie", 10);
                f = new Field(2, 1, new JTextArea());
        }

        @Test
        public void testIsZombie() {
                assertTrue(z.isZombie());
        }

        @Test
        public void testAttack() {

                Human h = new Human("te", 40);
                int hp = h.getHealthPoints();
                z.attack(h, new JTextArea());
                assertTrue(h.getHealthPoints() < hp);
                Vampire v = new Vampire("cd", 100);
                hp = v.getHealthPoints();
                boolean done = z.attack(v, new JTextArea());
                if (done) {
                        assertTrue(hp > v.getHealthPoints());
                } else {
                        assertTrue(hp == v.getHealthPoints());
                }

        }

        @Test
        public void testEndOfTurn() {
                assertFalse(z.isStun());
                z.setStun(true);
                assertTrue(z.isStun());
                z.endOfTurn(f);
                assertFalse(z.isStun());
        }

        @Test
        public void testIsMadZombie() {
                assertFalse(z.isMadZombie());
        }

}
