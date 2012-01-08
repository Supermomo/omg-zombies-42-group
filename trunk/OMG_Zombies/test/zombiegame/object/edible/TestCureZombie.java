package zombiegame.object.edible;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.edible.CureLycan;
import zombiegame.objects.edible.CureVamp;
import zombiegame.objects.edible.CureZombie;

public class TestCureZombie {

        private CureZombie cz;
        
        @Before
        public void setUp() throws Exception {
                cz=new CureZombie();
        }

        @Test
        public void testGetType() {
                assertTrue(cz.getType().equals(new CureZombie().getType()));
                assertFalse(cz.getType().equals(new CureVamp().getType()));
        }

        @Test
        public void testIsCureZomb() {
                assertTrue(cz.isCureZomb());
        }

}
