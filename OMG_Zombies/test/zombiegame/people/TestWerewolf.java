package zombiegame.people;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import zombiegame.engine.Field;
import zombiegame.engine.Location;

public class TestWerewolf {

        private Werewolf w;
        private int hp=100;
        private String name="louis";
        
        @Before
        public void setUp() throws Exception {
                w=new Werewolf(name,hp);
        }

        @Test
        public void testIsWerewolf() {
                assertTrue(w.isWerewolf());
        }

        @Test
        public void testEncounterCharacter() {
                
                //if encounter a werewolf
                Werewolf w2=new Werewolf("we",100);
                Field f=new Field(23,23,new JTextArea());
                f.place(w, new Location(2,2));
                f.place(w2, new Location(3,2));
                w.encounterCharacter(w2, f);
                assertTrue(((Character)f.getObjectAt(w2.getLocation())).isWerewolfCrew());
                
                //if a wounded human is encounter
                Human h=new Human("te",20);
                w.encounterCharacter(h, f);
                assertTrue(h.getHasBeenBittenByLycan());
                
                h.increaseHealthPoints(30);
                int hp=h.getHealthPoints();
                w.encounterCharacter(h, f);
                assertTrue(h.getHealthPoints()<hp);
                // the behavior for non wounded human is the same that the one for every other races
                
                //if a werewolfCrew is encountered
                int crNumber=2;
                WerewolfCrew wc=new WerewolfCrew("JE",100,crNumber);
                f.place(wc, new Location(2,3));
                w.encounterCharacter(wc, f);
                assertTrue(crNumber+1==((WerewolfCrew)f.getObjectAt(wc.getLocation())).getCrewMembers());
                
        }

        @Test
        public void testAttack() {
                Human h=new Human("gros",50);
                int hph=h.getHealthPoints();
                w.attack(h, new JTextArea());
                assertEquals(hph-15,h.getHealthPoints());
        }

        @Test
        public void testBite() {
                Human h=new Human("gros",50);
                assertFalse(h.getHasBeenBittenByLycan());
                w.bite(h, new JTextArea());
                assertTrue(h.getHasBeenBittenByLycan());
        }

}
