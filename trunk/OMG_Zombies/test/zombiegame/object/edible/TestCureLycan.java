package zombiegame.object.edible;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import zombiegame.objects.edible.CureLycan;
import zombiegame.objects.edible.CureVamp;

public class TestCureLycan {

        CureLycan cl;

        @Before
        public void setUp() throws Exception {
                cl = new CureLycan();
        }

        @Test
        public void testGetType() {
                assertTrue(cl.getType().equals(new CureLycan().getType()));
                assertFalse(cl.getType().equals(new CureVamp().getType()));
        }

        @Test
        public void testIsCureLycan() {
                assertTrue(cl.isCureLycan());
        }

}
