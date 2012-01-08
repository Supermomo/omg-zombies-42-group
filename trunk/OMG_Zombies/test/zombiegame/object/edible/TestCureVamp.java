package zombiegame.object.edible;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.edible.CureLycan;
import zombiegame.objects.edible.CureVamp;

public class TestCureVamp {

        CureVamp cv;

        @Before
        public void setUp() throws Exception {
                cv = new CureVamp();
        }

        @Test
        public void testGetType() {
                assertFalse(cv.getType().equals(new CureLycan().getType()));
                assertTrue(cv.getType().equals(new CureVamp().getType()));
        }

        @Test
        public void testUse() {

        }

        @Test
        public void testIsCureVamp() {
                assertTrue(cv.isCureVamp());
        }

}
