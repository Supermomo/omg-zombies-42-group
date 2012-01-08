package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;

public class TestEvilCharacter {

        private EvilCharacter ec;
        private int hp=100;
        private String name="kevin";
        
        @Before
        public void setUp() throws Exception {
                ec=new Vampire(name,hp);
        }

        @Test
        public void testIsEvilCharacter() {
                assertTrue(ec.isEvilCharacter());
        }

        @Test
        public void testEndOfTurn() {
                ec.setStun(true);
                ec.endOfTurn(new Field(2,2,new JTextArea()));
                assertFalse(ec.isStun());
        }

        @Test
        public void testTurnBackIntoHumain() {
                assertTrue(ec.turnBackIntoHumain().isHuman());
        }

        @Test
        public void testStun() {
               assertFalse(ec.isStun());
               ec.setStun(true);
               assertTrue(ec.isStun());
        }

}
