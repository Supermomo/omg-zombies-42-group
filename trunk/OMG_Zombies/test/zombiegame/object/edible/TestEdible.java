package zombiegame.object.edible;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.objects.edible.Bread;
import zombiegame.objects.edible.Edible;
import zombiegame.people.Human;

public class TestEdible {

        Edible e;

        @Before
        public void setUp() throws Exception {
                e = new Bread();
        }

        @Test
        public void testIsEdible() {
                assertTrue(e.isEdible());
        }

        @Test
        public void testGetType() {
                assertTrue(e.getType().equals(new Bread().getType()));
        }

        @Test
        public void testUse() {
                int us = e.getUses();
                e.Use(new Human("de", 12), new Field(4, 4, new JTextArea()));
                assertEquals(us - 1, e.getUses());
        }

        @Test
        public void testIsCureVamp() {
                assertFalse(e.isCureVamp());
        }

        @Test
        public void testIsCureZomb() {
                assertFalse(e.isCureZomb());
        }

        @Test
        public void testIsCureLycan() {
                assertFalse(e.isCureLycan());
        }

}
