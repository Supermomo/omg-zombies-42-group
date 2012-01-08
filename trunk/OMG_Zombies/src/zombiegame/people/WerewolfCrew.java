package zombiegame.people;

import javax.swing.JTextArea;

import zombiegame.engine.Field;

/**
 * Class for the werewolf crews
 * 
 * @author gaubert
 * 
 */
public class WerewolfCrew extends Werewolf {

        /** number of members in the crew */
        private int CrewMembers;

        /**
         * Constructor for a werewolf crew object
         * 
         * @param name
         * @param healthPoints
         * @param members
         */
        public WerewolfCrew(String name, int healthPoints, int members) {
                super(name, healthPoints);
                this.CrewMembers = members;
        }

        /**
         * Add a member to the crew
         */
        public void addMember(JTextArea cons) {
                super.say("We are " + getCrewMembers(),cons);
                this.CrewMembers++;
        }

        /**
         * The crew attack the character
         */
        protected void attack(Character c,JTextArea cons) {
                super.attack(c,cons);
                c.reduceHealthPoints(15 * (CrewMembers - 1));
        }

        public boolean isWerewolfCrew() {
                return true;
        }

        /**
         * get the number of member of the crew
         * 
         * @return the number of member
         */
        public int getCrewMembers() {
                return CrewMembers;
        }

        /**
         * Make the encounter with the chraracter<BR>
         * if encounter a werewolf, add it to the crew if there is room for it <BR>
         * if encounter a werewolf crew, attack it, the crew don't like being challenged<BR>
         * if encounter a human, bite him if he is wounded<BR>
         * otherwise attack
         */
        public void encounterCharacter(Character c, Field field) {

                if (c.isWerewolf()) {
                        if (this.getCrewMembers() < 5) {
                                this.addMember(field.getConsolePanel());
                                field.clear(c.location);
                        }
                        if (c.isWerewolfCrew()) {
                                attack(c,field.getConsolePanel());
                        }
                } else {
                        if (c.isHuman()) {
                                this.bite((Human) c,field.getConsolePanel());
                        }
                        else 
                        {
                            attack(c,field.getConsolePanel());
                        }

                }
        }
}
