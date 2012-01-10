package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.engine.FieldFrame;
import zombiegame.objects.Item;

public class TestHelico {

        private Helico hel;
        private Field f;
        @Before
        public void setUp() throws Exception {
                f=new Field(23,23,new JTextArea());
                hel=new Helico(f);
        }
        
        @Test
        public void testInitalizeStorage(){
                Item it=hel.initializeStorage();
                assertTrue(it.isEdible() || it.isWeapon() || it.isMiscellaneous());
        }

        @Test
        public void testDropItem() {
                assertTrue(hel.dropItem(new FieldFrame(f, new JTextArea())));
        }

}
