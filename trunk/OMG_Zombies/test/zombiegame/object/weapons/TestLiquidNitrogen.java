package zombiegame.object.weapons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.weapons.LiquidNitrogen;

public class TestLiquidNitrogen {

    LiquidNitrogen ln;
    
    @Before
    public void setUp() throws Exception {
        ln = new LiquidNitrogen();
    }

    @Test
    public void testGetType() {
        assertArrayEquals("LIQUID NITROGEN".toCharArray(),ln.getType().toCharArray());
    }

    @Test
    public void testIsLiquidNitrogen() {
        assertTrue(ln.isLiquidNitrogen());
    }

}
