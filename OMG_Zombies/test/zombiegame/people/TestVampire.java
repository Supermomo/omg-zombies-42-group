package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.WoodenStick;

public class TestVampire {

        private Vampire v;
        private int hp = 100;
        private String name = "marco";

        @Before
        public void setUp() throws Exception {
                v = new Vampire(name, hp);
        }

        @After
        public void tearDown() {
                v = null;
        }

        @Test
        public void testIsVampire() {
                assertTrue(v.isVampire());
        }

        @Test
        public void testEncounterCharacter() {
                Human h = new Human("toto", 50);
                Vampire v2 = new Vampire("titi", 100);
                v.setIsThirsty(false);
                v.encounterCharacter(h, new Field(3, 3, new JTextArea()));
                assertFalse(h.getHasBeenBittenByVamp());
                v.setIsThirsty(true);
                v.encounterCharacter(h, new Field(3, 3, new JTextArea()));
                assertTrue(h.getHasBeenBittenByVamp());
                int hp = v.getHealthPoints();
                v.encounterCharacter(v2, new Field(3, 3, new JTextArea()));
                assertEquals(hp + 5, v.getHealthPoints());
        }

        @Test
        public void testAttack() {
                Human h = new Human("toto", 50);
                int hp = h.getHealthPoints();
                v.attack(h, new JTextArea());
                assertEquals(hp - 10, h.getHealthPoints());
        }

        @Test
        public void testThirsty() {
                assertFalse(v.getIsThirsty());
                v.setIsThirsty(true);
                assertTrue(v.getIsThirsty());
        }

        @Test
        public void testBite() {
                Human h = new Human("toto", 50);
                assertFalse(h.getHasBeenBittenByVamp());
                v.bite(h, new JTextArea());
                assertTrue(h.getHasBeenBittenByVamp());
        }

        @Test
        public void testDefend() {
                Shotgun s = new Shotgun();
                Field f = new Field(3, 3, new JTextArea());
                Field f1 = new Field(3, 3, new JTextArea());
                f1.placeItem(s, 0, 0);
                Human h1 = new Human("marc", 50);
                h1.pickUpObject(f1, new Location(0, 0));
                f.place(h1, new Location(0, 0));
                Vampire v = new Vampire("jean", 100);
                int hph = h1.getHealthPoints();
                int hp = v.getHealthPoints();
                f.place(v, new Location(1, 0));

                if (v.defend(h1, f)) {
                        assertTrue(v.getHealthPoints() > hp);
                        assertTrue(hph > h1.getHealthPoints());
                } else {
                        assertTrue(v.getHealthPoints() == hp);
                        assertTrue(hph == h1.getHealthPoints());
                }

        }

}
