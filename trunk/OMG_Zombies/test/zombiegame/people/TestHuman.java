package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.engine.Simulator;
import zombiegame.objects.edible.CureLycan;
import zombiegame.objects.edible.CureVamp;
import zombiegame.objects.edible.CureZombie;
import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.objects.weapons.LiquidNitrogen;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.WoodenStick;

public class TestHuman 
{
    Human h1;
    Field f;
    Field f1;
    
    @Before
    public void setUp() 
    {
        h1 = new Human("Humain1",100);
        h1.setLocation(new Location(0,0),new JTextArea());
        f = new Field(2,1,new JTextArea());
        f1 = new Field(2,1,new JTextArea());
    }
    
    @Test
    public void testIsHuman() 
    {
        assertTrue(h1.isHuman());
    }
    
    @Test
    public void testEncounterCharacter() 
    {   
        //test de l'utilisation des objets
        Werewolf w = new Werewolf("a",150);
        f.place(w,new Location(1,0));
        f1.placeItem(new Shotgun(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        //test : ramasser l'objet l'a bien supprimé
        assertTrue(f1.getObjectAt(new Location(0,0))==null);
        f1.placeItem(new SilverBullet(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(w,f);
        //Verifier si le loup-garou est bien mort
        assertEquals(0,((Character)f.getObjectAt(1,0)).getHealthPoints());
        
        // r�p�tition des tests sur les autres "classes"
        Vampire v = new Vampire("b",150);
        f.place(w,new Location(1,0));
        f1.placeItem(new WoodenStick(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(w,f);
        assertEquals(0,((Character)f.getObjectAt(1,0)).getHealthPoints());
        f.clear(new Location(1,0));
        
        Zombie z = new Zombie("c",30);
        f.place(w,new Location(1,0));
        f1.placeItem(new LiquidNitrogen(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(w,f);
        assertEquals(0,((Character)f.getObjectAt(1,0)).getHealthPoints());
        f.clear(new Location(1,0));
        
        MadZombie mz = new MadZombie("d",30);
        f.place(w,new Location(1,0));
        f1.placeItem(new LiquidNitrogen(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(w,f);
        assertEquals(0,((Character)f.getObjectAt(1,0)).getHealthPoints());
        f.clear(new Location(1,0));
        
        WerewolfCrew wc = new WerewolfCrew("e",150,2);
        f.place(wc,new Location(1,0));
        f1.placeItem(new Shotgun(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(wc,f);
        assertEquals(0,((Character)f.getObjectAt(1,0)).getHealthPoints());
        f.clear(new Location(1,0));
        
        //Test de l'utilisation des edible ( debut de encounter character
        w = new Werewolf("a",150);
        f.place(w,new Location(1,0));
        f1.placeItem(new CureLycan(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(w,f);
        assertEquals(h1.getClass(),f.getObjectAt(1,0).getClass());
        
        v = new Vampire("b",150);
        f.place(v,new Location(1,0));
        f1.placeItem(new CureVamp(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(v,f);
        assertEquals(h1.getClass(),f.getObjectAt(1,0).getClass());
        
        z = new Zombie("c",150);
        f.place(z,new Location(1,0));
        f1.placeItem(new CureZombie(),0,0);
        h1.pickUpObject(f1,new Location(0,0));
        h1.encounterCharacter(z,f);
        assertEquals(h1.getClass(),f.getObjectAt(1,0).getClass());
        //Test de la suppression de l'inventaire d'un miscellaneous qui n'a plus d'utilisation
        assertTrue(h1.getItem() == null);
        
        //Test que sinon, pas d'attaque effectuée
        z = new Zombie("c",150);
        f.place(w,new Location(1,0));
        h1.encounterCharacter(w,f);
        assertTrue(z.getHealthPoints() != 0);
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
        boolean res=h1.baby(f);
        if(res){
                assertTrue(((Human) f.getObjectAt(1,0)).isHuman());  
        }
        else {
                assertTrue(f.getObjectAt(1,0)==null);
        }
        
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
