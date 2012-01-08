package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;

public class TestZombie 
{
    Zombie z;
    Field f;
    
    @Before
    public void setUp()
    {
        z = new Zombie("Zombie",10);
        f = new Field(2,1,new JTextArea());
    }

    @Test
    public void testIsZombie() {
        assertTrue(z.isZombie());
    }

    @Test
    public void testEncounterCharacter() {
        
    }

    @Test
    public void testEndOfTurn() {
        assertFalse(z.isStun());
        z.setStun(true);
        assertTrue(z.isStun());
        z.endOfTurn(f);
        assertFalse(z.isStun());
    }

    @Test
    public void testIsMadZombie() {
        assertFalse(z.isMadZombie());
    }

}
