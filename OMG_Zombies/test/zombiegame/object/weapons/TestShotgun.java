package zombiegame.object.weapons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.weapons.Shotgun;

public class TestShotgun {

        Shotgun s;

        @Before
        public void setUp() throws Exception {
                s = new Shotgun();
        }

        @Test
        public void testGetType() {
                assertArrayEquals("SHOTGUN".toCharArray(), s.getType().toCharArray());
        }

        @Test
        public void testIsLiquidNitrogen() {
                assertTrue(s.isShotgun());
        }

}
