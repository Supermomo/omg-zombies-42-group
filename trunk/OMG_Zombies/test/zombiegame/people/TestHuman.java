package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.engine.Simulator;
import zombiegame.objects.weapons.Shotgun;

public class TestHuman 
{
    Human h1;
    Field f;
    
    @Before
    public void setUp() 
    {
        h1 = new Human("Humain1",100);
        h1.setLocation(new Location(0,0),new JTextArea());
        f = new Field(2,1,new JTextArea());
    }
    
    @Test
    public void testIsHuman() 
    {
        assertTrue(h1.isHuman());
    }
    
    @Test
    public void testEncounterCharacter() 
    {
        
    }

    @Test
    public void testEndOfTurn() 
    {
        h1.setHasBeenBittenByLycan(true);
        h1.endOfTurn(f);
        assertTrue(((Werewolf)f.getObjectAt(0,0)).isWerewolf());
    }

    @Test
    public void testHasBeenBittenByVamp() 
    {
        assertFalse(h1.getHasBeenBittenByVamp());
        h1.setHasBeenBittenByVamp(true);
        assertTrue(h1.getHasBeenBittenByVamp());
    }

    @Test
    public void testIsArmed() 
    {
        assertFalse(h1.isArmed());
        f.placeItem(new Shotgun(),0,0);
        h1.pickUpObject(f,new Location(0,0));
        assertTrue(h1.isArmed());
    }

    @Test
    public void testBaby()
    {
        h1.setLocation(new Location(0,0),new JTextArea());
        h1.baby(f);
        assertTrue(((Human) f.getObjectAt(1,0)).isHuman());
        }

    @Test
    public void testTurnIntoVampire() 
    {
        Vampire v = h1.turnIntoVampire();
        String s = "(Vamp)" + h1.getName();
        assertArrayEquals(v.getName().toCharArray(),s.toCharArray());
        assertTrue(v.getHealthPoints() == h1.getHealthPoints());
    }

    @Test
    public void testTurnIntoWerewolf() 
    {
        Werewolf w = h1.turnIntoWerewolf();
        String s = "(Wolf)" + h1.getName();
        assertArrayEquals(w.getName().toCharArray(),s.toCharArray());
        assertTrue(w.getHealthPoints() == h1.getHealthPoints());
    }

    @Test
    public void testTurnIntoZombie() 
    {
        Zombie z = h1.turnIntoZombie();
        String s = "(Zomb)" + h1.getName();
        assertArrayEquals(z.getName().toCharArray(),s.toCharArray());
        assertTrue(z.getHealthPoints() == Simulator.HP_ZOMBIES);
    }

    @Test
    public void testEatFood() 
    {
        h1.endOfTurn(f);
        h1.endOfTurn(f);
        h1.endOfTurn(f);
        h1.endOfTurn(f);
        assertEquals(98,h1.getHealthPoints());
        h1.eatFood();
        h1.endOfTurn(f);
        assertEquals(98,h1.getHealthPoints());
    }

    @Test
    public void testHasBeenBittenByLycan() 
    {
        assertFalse(h1.getHasBeenBittenByLycan());
        h1.setHasBeenBittenByLycan(true);
        assertTrue(h1.getHasBeenBittenByLycan());
    }

    @Test
    public void testGetWeapon() 
    {
        Shotgun s = new Shotgun();
        f.placeItem(s,0,0);
        h1.pickUpObject(f,new Location(0,0));
        assertEquals(s,h1.getWeapon());
    }

}
