package zombiegame.object.edible;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.objects.edible.MajorPotion;
import zombiegame.objects.edible.MinorPotion;
import zombiegame.people.Human;

public class TestMinorPotion {

        private MinorPotion minp;
        
        @Before
        public void setUp() throws Exception {
                minp=new MinorPotion();
        }

        @Test
        public void testGetType() {
                assertTrue(minp.getType().equals(new MinorPotion().getType()));
                assertFalse(minp.getType().equals(new MajorPotion().getType()));
        }

        @Test
        public void testUse() {
                Human h=new Human("de",50);
                int hp=h.getHealthPoints();
                minp.Use(h,new Field(4,4, new JTextArea()));
                assertEquals(hp+15,h.getHealthPoints());
        }

        @Test
        public void testIsIncreasingHp() {
                assertTrue(minp.isIncreasingHp());
        }
}
