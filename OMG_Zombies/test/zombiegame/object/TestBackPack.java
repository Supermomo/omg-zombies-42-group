package zombiegame.object;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombiegame.objects.BackPack;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.WoodenStick;
import zombiegame.people.Player;

public class TestBackPack {

        private BackPack bp;
        
        @Before
        public void setUp() throws Exception {
                bp=new BackPack();
        }

        @After
        public void tearDown() throws Exception {
        }

        @Test
        public void testGetItemList() {
               Shotgun s=new Shotgun();
               WoodenStick ws=new WoodenStick();
               bp.addItem(s);
               bp.addItem(ws);
               assertTrue(bp.getItemList().get(0)==s);
               assertTrue(bp.getItemList().get(1)==ws);
        }

        @Test
        public void testEquip() {
                Player p=new Player("jean",100);
                bp=p.getBackPack();
                Shotgun s=new Shotgun();
                bp.addItem(s);
                bp.equip(s, p);
                assertEquals(p.getWeapon().getType(),s.getType());
        }

        @Test
        public void testIsInBackPack() {
                Shotgun s=new Shotgun();
                bp.addItem(s);
                assertTrue(bp.isInBackPack(s));
        }

        @Test
        public void testAddItem() {
                Shotgun s=new Shotgun();
                bp.addItem(s);
                int size=bp.getItemList().size();
                assertTrue(bp.isInBackPack(s));
                Shotgun s2=new Shotgun();
                bp.addItem(s2);
                assertEquals(size,bp.getItemList().size());
        }

        @Test
        public void testRemove() {
                Shotgun s=new Shotgun();
                bp.addItem(s);
                assertTrue(bp.isInBackPack(s));
                bp.remove(s);
                assertFalse(bp.isInBackPack(s));
        }

        @Test
        public void testGetEquiped() {
                assertEquals(null,bp.getEquiped());
        }

}
