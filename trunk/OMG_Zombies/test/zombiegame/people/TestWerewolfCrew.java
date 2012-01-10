package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.engine.Location;

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
                
                int memb=wc.getCrewMembers();
                
                Werewolf w=new Werewolf("e",100);
                Field f=new Field(23,23,new JTextArea());
                f.place(w, new Location(2,2));
                f.place(wc, new Location(2,3));
                wc.encounterCharacter(w, f);
                assertTrue(memb+1==wc.getCrewMembers());
                
                WerewolfCrew wc2= new WerewolfCrew("h",100,2);
                int hp=wc2.getHealthPoints();
                f.place(wc2, new Location(1,2));
                wc.encounterCharacter(wc2, f);
                assertTrue(hp>wc2.getHealthPoints());
                
                Human h=new Human("c",50);
                wc2.encounterCharacter(h, f);
                assertTrue(h.getHasBeenBittenByLycan());
                
                Vampire v= new Vampire("v",100);
                hp=v.getHealthPoints();
                wc.encounterCharacter(v, f);
                assertTrue(hp>v.getHealthPoints());
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
