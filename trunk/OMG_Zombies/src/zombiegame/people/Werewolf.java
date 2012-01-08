package zombiegame.people;

import java.util.List;

import javax.swing.JTextArea;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.engine.Simulator;

/**
 * Class for a werewolf extends from EvilCharacter
 * 
 * @author gaubert
 * 
 */
public class Werewolf extends EvilCharacter {

        /**
         * Create a new werewolf just like a normal character
         * 
         * @param name
         * @param healthPoints
         */
        public Werewolf(String name, int healthPoints) {
                super(name, healthPoints);
        }

        /**
         * wether or not this character is a werewolf
         * 
         * @return
         */
        public boolean isWerewolf() {
                return true;
        }

        /**
         * Make the encounter with the character c Create a crew if he encounter
         * a werewolf or attack any other race
         */
        public void encounterCharacter(Character c, Field field) {
                if (c.isWerewolf()) {
                        WerewolfCrew meute = new WerewolfCrew("(Crew)" + this.name, 150, 2);
                        field.clear(c.location);
                        field.clear(this.location);
                        field.place(meute, c.location);
                        this.say("We are now a crew !",field.getConsolePanel());
                        if (c.isWerewolfCrew()) {
                                if (((WerewolfCrew) c).getCrewMembers() < 5) {
                                        ((WerewolfCrew) c).addMember(field.getConsolePanel());
                                        field.clear(this.location);
                                }
                        }
                } else {
                        if (c.isHuman() && (c.getHealthPoints() <= 25)) {
                                this.bite((Human) c,field.getConsolePanel());
                        } else {
                                attack(c,field.getConsolePanel());
                                if (c.isHuman() && (c.getHealthPoints() <= 25)) {
                                        this.bite((Human) c,field.getConsolePanel());
                                }
                        }

                }
        }

        /**
         * attack a character
         * 
         * @param c
         */
        protected void attack(Character c,JTextArea cons) {
                super.attack(c,cons);
                c.reduceHealthPoints(15);
        }


        /**
         * Method called when a vampire decides to bite a human
         * 
         * @param h
         *                Human who gets bitten by this vampire
         */
        public void bite(Human h,JTextArea cons) {
                // The human has no way to escape. He gets bitten.
                h.setHasBeenBittenByLycan(true);
                say("I have bitten you, " + h.getName() + "!",cons);
        }
        
        /**
         * Do the best move for the character
         * @param field
         * @return null if no best location have been found
         */
        public Location bestMove(Field field){

                Location dest=null;
                List<Location> loc=field.adjacentLocations(this.location);
                Location vamp=null;
                Location human=null;
                Location lycan=null;
                
                for(Location l : loc){
                        if(field.getObjectAt(l)!= null && ((Character)field.getObjectAt(l)).isVampire()){
                                vamp=l;
                        }
                        else if(field.getObjectAt(l)!= null &&((Character)field.getObjectAt(l)).isHuman()){
                                human=l;
                        }
                        else if(field.getObjectAt(l)!= null &&((Character)field.getObjectAt(l)).isWerewolf()){
                                lycan=l;
                        }
                }
                
                if(vamp!=null ){
                    dest=vamp  ;  
                }
                else if (human!=null){
                        dest=human;
                }
                else if(field.getFreeAdjacentLocations(location)!=null && field.getFreeAdjacentLocations(location).size()>0)
                {
                        dest=field.getFreeAdjacentLocations(location).get(0);
                }
                else if (lycan!=null){
                        dest=lycan;
                }

                
                return dest;
        }
}
