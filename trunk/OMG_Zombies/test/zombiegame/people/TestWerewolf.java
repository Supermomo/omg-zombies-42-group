package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

public class TestWerewolf {

        private Werewolf w;
        private int hp=100;
        private String name="louis";
        
        @Before
        public void setUp() throws Exception {
                w=new Werewolf(name,hp);
        }

        @Test
        public void testIsWerewolf() {
                assertTrue(w.isWerewolf());
        }

        @Test
        public void testEncounterCharacter() {
                
        }

        @Test
        public void testEndOfTurn() {

        }

        @Test
        public void testAttack() {
                Human h=new Human("gros",50);
                int hph=h.getHealthPoints();
                w.attack(h, new JTextArea());
                assertEquals(hph-15,h.getHealthPoints());
        }

        @Test
        public void testBestMove() {
                fail("Not yet implemented");
        }

        @Test
        public void testWerewolf() {
                fail("Not yet implemented");
        }

        @Test
        public void testBite() {
                fail("Not yet implemented");
        }

}
