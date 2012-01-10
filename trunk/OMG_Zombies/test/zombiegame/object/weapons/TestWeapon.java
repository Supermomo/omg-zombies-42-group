package zombiegame.object.weapons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.weapons.*;

public class TestWeapon {

        Weapon wp;

        @Before
        public void setUp() {
                wp = new Shotgun();
        }

        @Test
        public void testIsWeapon() {
                assertTrue(wp.isWeapon());
        }

        @Test
        public void testGetType() {
                assertArrayEquals("SHOTGUN".toCharArray(), wp.getType().toCharArray());
        }

        @Test
        public void testIsShotgun() {
                assertTrue(wp.isShotgun());
        }

        @Test
        public void testIsWoodenStick() {
                assertFalse(wp.isWoodenStick());
        }

        @Test
        public void testIsLiquidNitrogen() {
                assertFalse(wp.isLiquidNitrogen());
        }

}
