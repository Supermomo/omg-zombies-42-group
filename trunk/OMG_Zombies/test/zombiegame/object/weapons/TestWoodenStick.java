package zombiegame.object.weapons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.weapons.WoodenStick;

public class TestWoodenStick {
    
    WoodenStick ws;

    @Before
    public void setUp() throws Exception {
        ws = new WoodenStick();
    }

    @Test
    public void testGetType() {
        assertArrayEquals("WOODEN STICK".toCharArray(),ws.getType().toCharArray());
    }

    @Test
    public void testIsWoodenStick() {
        assertTrue(ws.isWoodenStick());
    }

}
