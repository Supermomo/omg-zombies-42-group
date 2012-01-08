package zombiegame.people;

import static org.junit.Assert.*;

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
        public void testIsMadZombie() {
                assertTrue(mz.isMadZombie());
        }

}
