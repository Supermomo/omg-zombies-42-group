package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.WoodenStick;

public class TestPlayer {

        private Player p;

        @Before
        public void setUp() throws Exception {
                p=new Player("jean",100);
        }

        @After
        public void tearDown() throws Exception {
        }

        @Test
        public void testIsHuman() {
                assertTrue(p.isHuman());
        }

        @Test
        public void testIsVampire() {
                assertFalse(p.isVampire());
        }

        @Test
        public void testIsWerewolf() {
                assertFalse(p.isWerewolf());
        }

        @Test
        public void testIsPlayer() {
                assertTrue(p.isPlayer());
        }

        @Test
        public void testEncounterCharacter() {
                //Same as Human method for now
        }

        @Test
        public void testPickUpObject() {
                Shotgun s=new Shotgun();
                Field f=new Field(5,5,new JTextArea());
                f.placeItem(s, 1, 1);
                p.pickUpObject(f, new Location(1,1));
                assertTrue(p.getBackPack().isInBackPack(s));
                SilverBullet sb=new SilverBullet();
                f.placeItem(sb, 2, 2);
                int size =p.getBackPack().getItemList().size();
                p.pickUpObject(f, new Location(2,2));
                assertEquals(size,p.getBackPack().getItemList().size());
        }

        @Test
        public void testGetBackPack() {
                assertFalse(p.getBackPack()==null);
        }

        @Test
        public void testGetLightBoxWidth() {
                //Not implented yet
        }

        @Test
        public void testMove() {
                Shotgun s=new Shotgun();
                p.setLocation(new Location(2,2), new JTextArea());
                Field fo=new Field(5,5,new JTextArea());
                fo.placeItem(s, 1, 1);
                Field f=new Field(5,5,new JTextArea());
                p.move(new Location(1,1), f, fo);
                assertTrue(p.getBackPack().isInBackPack(s));
                assertTrue(p.getLocation().getCol()==1 && p.getLocation().getRow()==1);
                
                
        }
        
        @Test
        public void testDefend(){
                Field f = new Field(2, 1, new JTextArea());
                Field f1 = new Field(2, 1, new JTextArea());
                WoodenStick w=new WoodenStick();
                f1.placeItem(w, 0, 0);
                p.pickUpObject(f1, new Location(0,0));
                f.place(p, new Location(0,0));
                Vampire v=new Vampire("jean",100);
                int hph=p.getHealthPoints();
                int hp=v.getHealthPoints();
                f.place(v, new Location(1,0));
                v.encounterCharacter(p, f);
                assertTrue(v.getHealthPoints()<hp);
                assertTrue(hph==p.getHealthPoints());
        }

}
