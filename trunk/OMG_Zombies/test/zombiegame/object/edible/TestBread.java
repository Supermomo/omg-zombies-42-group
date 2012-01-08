package zombiegame.object.edible;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.edible.Bread;
import zombiegame.people.Human;
import zombiegame.people.Vampire;

public class TestBread {

        private Bread b;
        
        @Before
        public void setUp() throws Exception {
                b=new Bread();
        }

        @Test
        public void testGetType() {
                assertTrue(b.getType().equals(new Bread().getType()));
        }

        @Test
        public void testUse() {
                int hp=100;
                Human h=new Human("phil",hp);
                b.Use(h);
                assertEquals(hp+5,h.getHealthPoints());
        }

        @Test
        public void testIsIncreasingHp() {
                assertTrue(b.isIncreasingHp());
        }

}
