package zombiegame.object;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import zombiegame.objects.Item;
import zombiegame.objects.weapons.Shotgun;

public class TestItem {

        private Item it;

        @Before
        public void setUp() throws Exception {
                it = new Shotgun();
        }

        @Test
        public void testIsEdible() {
                assertFalse(it.isEdible());
        }

        @Test
        public void testIsMiscellaneous() {
                assertFalse(it.isMiscellaneous());
        }

        @Test
        public void testUses() {
                int us = it.getUses();
                it.loseUses();
                assertEquals(us - 1, it.getUses());
                it.addUses(3);
                assertEquals(us + 2, it.getUses());
        }

}
