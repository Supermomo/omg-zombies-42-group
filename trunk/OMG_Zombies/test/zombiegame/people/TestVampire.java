package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.engine.Location;

public class TestVampire {

        private Vampire v;
        private int hp=100;
        private String name="marco";
        
        @Before
        public void setUp() throws Exception {
                v=new Vampire(name,hp);
        }

        @Test
        public void testIsVampire() {
                assertTrue(v.isVampire());
        }

        @Test
        public void testEncounterCharacter() {
                Human h=new Human("toto",50);
                Vampire v2=new Vampire("titi",100);
                v.setIsThirsty(false);
                v.encounterCharacter(h, new Field(3,3,new JTextArea()));
                assertFalse(h.getHasBeenBittenByVamp());
                v.setIsThirsty(true);
                v.encounterCharacter(h, new Field(3,3,new JTextArea()));
                assertTrue(h.getHasBeenBittenByVamp());
                int hp=v.getHealthPoints();
                v.encounterCharacter(v2, new Field(3,3,new JTextArea()));
                assertEquals(hp+5,v.getHealthPoints());
        }

        @Test
        public void testAttack() {
                Human h=new Human("toto",50);
                int hp=h.getHealthPoints();
                v.attack(h, new JTextArea());
                assertEquals(hp-10,h.getHealthPoints());
        }

        @Test
        public void testThirsty() {
                assertFalse(v.getIsThirsty());
                v.setIsThirsty(true);
                assertTrue(v.getIsThirsty());
        }

        @Test
        public void testBite() {
                Human h=new Human("toto",50);
                assertFalse(h.getHasBeenBittenByVamp());
                v.bite(h, new JTextArea());
                assertTrue(h.getHasBeenBittenByVamp());
        }

}
