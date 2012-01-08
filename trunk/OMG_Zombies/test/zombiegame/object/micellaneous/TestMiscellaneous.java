package zombiegame.object.micellaneous;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.Weapon;
import zombiegame.objects.weapons.WoodenStick;

public class TestMiscellaneous {
    
    SilverBullet sb;
    @Before
    public void setUp()
    {
        sb = new SilverBullet();
    }

    @Test
    public void testGetType() {
        assertArrayEquals("SILVER BULLET".toCharArray(),sb.getType().toCharArray());
    }

    @Test
    public void testIsUsableWith() {
        Weapon w1,w2;
        w1 = new Shotgun();
        w2 = new WoodenStick();
        
        assertFalse(sb.isUsableWith(w2));
        assertTrue(sb.isUsableWith(w1));
    }

    @Test
    public void testIsMicellaneous() {
        assertTrue(sb.isMicellaneous());
    }

}
