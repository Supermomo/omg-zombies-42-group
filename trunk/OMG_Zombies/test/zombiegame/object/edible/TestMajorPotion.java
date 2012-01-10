package zombiegame.object.edible;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.objects.edible.MajorPotion;
import zombiegame.objects.edible.MinorPotion;
import zombiegame.people.Human;

public class TestMajorPotion {

        private MajorPotion majp;
        
        @Before
        public void setUp() throws Exception {
                majp=new MajorPotion();
        }

        @Test
        public void testGetType() {
                assertFalse(majp.getType().equals(new MinorPotion().getType()));
                assertTrue(majp.getType().equals(new MajorPotion().getType()));
        }

        @Test
        public void testUse() {
                Human h=new Human("de",50);
                int hp=h.getHealthPoints();
                majp.Use(h,new Field(4,4, new JTextArea()));
                assertEquals(hp+25,h.getHealthPoints());
        }

        @Test
        public void testIsIncreasingHp() {
                assertTrue(majp.isIncreasingHp());
        }

}
