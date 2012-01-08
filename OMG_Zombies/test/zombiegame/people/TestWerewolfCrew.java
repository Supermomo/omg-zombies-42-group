package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

public class TestWerewolfCrew {

        
        private WerewolfCrew wc;
        private String name="steeve";
        private int hp=100;
        
        @Before
        public void setUp() throws Exception {
                wc=new WerewolfCrew(name,hp,1);
        }

        @Test
        public void testIsWerewolfCrew() {
                assertTrue(wc.isWerewolfCrew());
        }

        @Test
        public void testEncounterCharacter() {

        }

        @Test
        public void testAttack() {
                Human h=new Human("dd",100);
                int hph=h.getHealthPoints();
                wc.attack(h, new JTextArea());
                assertEquals(hph-(wc.getCrewMembers()*15),h.getHealthPoints());
        }

        @Test
        public void testMember() {
               int member=wc.getCrewMembers();
               wc.addMember(new JTextArea());
               assertEquals(member+1,wc.getCrewMembers());
        }


}
