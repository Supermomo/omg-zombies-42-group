package zombiegame.people;

import java.util.List;

import javax.swing.JTextArea;
import zombiegame.engine.Field;
import zombiegame.engine.Location;

/**
 * Parent Character class
 * 
 * @author pylaffon
 * 
 */
public abstract class Character {

        /** name of the character */
        protected String name;

        /**
         * represents the health (once down to 0, this character will be
         * destroyed)
         */
        protected int healthPoints;

        /** the location of the character */
        protected Location location;

        /** bollean telling if the character has just played */
        protected boolean hasPlayed;

        /**
         * Constructor of Character class.
         * 
         * @param name
         *                name of the character
         * @param healthPoints
         *                initial HP
         */
        public Character(String name, int healthPoints) {
                this.name = name;
                this.healthPoints = healthPoints;
        }

        // Accessors
        public String getName() {
                return name;
        }

        public int getHealthPoints() {
                return healthPoints;
        }

        /**
         * 
         * @return true if the character can play, false if not
         */
        public boolean canPlay() {
                return !hasPlayed;
        }

        /**
         * This character is now able to play
         */
        public void setPlay() {
                hasPlayed = false;
        }

        /**
         * This character won't be able to play until the end of the turn
         */
        public void justPlayed() {
                hasPlayed = true;
        }

        /**
         * wether or not this character is a human
         * 
         * @return
         */
        public boolean isHuman() {
                return false;
        }

        /**
         * wether or not this character is a zombie
         * 
         * @return
         */
        public boolean isZombie() {
                return false;
        }

        /**
         * wether or not this character is a vampire
         * 
         * @return
         */
        public boolean isVampire() {
                return false;
        }

        /**
         * wether or not this character is a werewolf
         * 
         * @return
         */
        public boolean isWerewolf() {
                return false;
        }

        /**
         * wether or not the character is an evil character
         * 
         * @return
         */
        public boolean isEvilCharacter() {
                return false;
        }

        /**
         * wether or not the character is a WerewolfCrew
         * 
         * @return
         */
        public boolean isWerewolfCrew() {
                return false;
        }

        /**
         * Decrease the number of HP by a certain amount. HP cannot go below 0.
         * 
         * @param reduction
         *                number of HP to reduce
         */
        public void reduceHealthPoints(int reduction) {
                healthPoints = healthPoints - reduction;
                if (healthPoints < 0) {
                        healthPoints = 0;
                }
        }

        /**
         * Increase the number of HP by a certain amount.
         * 
         * @param increase
         *                number of HP to add
         */
        public void increaseHealthPoints(int increase) {
                if (increase >= 0) {
                        this.healthPoints += increase;
                } else {
                        System.out.println("Wrong hp values in increaseHp method");
                }
        }

        /**
         * Output a character's saying to the screen
         * 
         * @param str
         *                what the character says
         */
        public void say(String str, JTextArea cons) {
                cons.append(name + " says: " + str+"\r\n");
        }

        /**
         * Method triggered when the character described by the current object
         * meets another character, and does something to him (for example,
         * attack).
         * 
         * @param c
         *                the other character that this character meets
         */
        public void encounterCharacter(Character c, Field field) {
                // Default action: do nothing
                System.out.println(name + " meets " + c.name + " and does not attack!");
        }

        /**
         * Method triggered when the new turn start. Perform the action of this
         * character Clear the encounter character if dead and human, turn it into zombie
         * if human After the action, if human, pick up the object on the
         * ground(if there is any)
         * 
         * Do nothing if the character is stun
         * 
         */
        public void action(Field field, Field fieldObj) {

                boolean stun = false;

                if (this.isEvilCharacter()) {
                        
                        try {
                                if (((EvilCharacter) this).isStun()) {
                                        stun = true;
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Cannot cast into evilCharacter during the action method");
                        }
                }

                if (!stun) {
                        
                        Location loc=bestMove(field);
                        if(loc==null){
                                loc = field.randomAdjacentLocation(location);
                        }
                        
                        this.say("I'm now acting", field.getConsolePanel());

                        if (field.getObjectAt(loc) == null) {
                                Location a = this.location;
                                field.place(this, loc);
                                field.clear(a);
                        } else {
                                try {
                                        Character c = (Character) field.getObjectAt(loc);
                                        encounterCharacter(c, field);
                                        if (c.getHealthPoints() == 0) {
                                                field.clear(loc);
                                                if (c.isHuman()) {
                                                        field.place(((Human) c).turnIntoZombie(),
                                                                        loc);                                                     
                                                }
                                                else if(c.isZombie()) {
                                                        MadZombie mz=((Zombie) c).turnIntoMadZombie();
                                                        mz.setStun(true);
                                                        field.place(mz,loc);   
                                                }

                                        }
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        if (this.isHuman()) {
                                try {
                                        ((Human) this).pickUpObject(fieldObj, loc);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                        System.out
                                                        .println("Cannot cast into human in method action");
                                }
                        }

                }

        }

        public void endOfTurn(Field field) {
                // do nothing
                System.out.println("this is not suposse to be print, check endOfTurn if Character");
        }

        /**
         * attack the character c
         * 
         * @param c
         */
        protected void attack(Character c, JTextArea cons) {
                say(c.getName() + ", I'm gonna kill you!",cons);

        }

        /**
         * Return the rabbit's location.
         * 
         * @return The rabbit's location.
         */
        public Location getLocation() {
                return location;
        }

        /**
         * Place the rabbit at the new location in the given field.
         * 
         * @param newLocation
         *                The rabbit's new location.
         */
        public void setLocation(Location newLocation, JTextArea cons) {

                if (location != null) {
                        say(" goes from " + location.getRow() + " "
                                        + location.getCol(),cons);
                } else {
                        say("start at location ",cons);
                }

                this.location = newLocation;
                say(" to : " + location.getRow() + " " + location.getCol(),cons);
        }
        
        /**
         * Do the best move for the character
         * @param field
         * @return null if no best location have been found
         */
        public Location bestMove(Field field){
                Location dest=null;
                List<Location> loc=field.adjacentLocations(this.location);
                Location human=null;
                
                for(Location l : loc){
                        if(field.getObjectAt(l)!= null &&((Character)field.getObjectAt(l)).isHuman()){
                                human=l;
                        }
                }
                if(human!=null){
                        dest=human;;
                }
                else if(field.getFreeAdjacentLocations(location)!=null && field.getFreeAdjacentLocations(location).size()>0){
                        dest=field.getFreeAdjacentLocations(location).get(0);
                }
                
                return dest;
        }
}
