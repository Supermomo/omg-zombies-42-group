package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

public class TestMadZombie {

        private MadZombie mz;
        private String name="jule";
        private int hp=100;
        
        @Before
        public void setUp() throws Exception {
                mz=new MadZombie(name,hp);
        }
        
        @Test
        public void testAttack() {
                
                Vampire v=new Vampire("e",100);
                int hp=v.getHealthPoints();
                boolean done=mz.attack(v, new JTextArea());
                if(done){
                        assertTrue(hp>v.getHealthPoints());
                }
                else{
                        assertTrue(hp==v.getHealthPoints());
                }
                Human h=new Human("h",50);
                hp=v.getHealthPoints();
                mz.attack(h, new JTextArea());
                assertTrue(hp>v.getHealthPoints());
        }

        @Test
        public void testIsMadZombie() {
                assertTrue(mz.isMadZombie());
        }

}
