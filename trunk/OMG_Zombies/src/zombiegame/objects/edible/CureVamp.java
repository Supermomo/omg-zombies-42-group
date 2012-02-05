package zombiegame.objects.edible;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;
import zombiegame.people.Human;

/**
 * the cure potion used to turn a vampire back into a humain
 * 
 * @author gaubert
 * 
 */
public class CureVamp extends Edible {
        private static final String TYPE = "CURE VAMP";

        private static final int cureUse = 1;

        /**
         * create a CureVamp object that can only be use once
         */
        public CureVamp() {
                super(CureVamp.cureUse);
        }

        /**
         * Use the potion to cure a vampire or to prevent a human that have been
         * bitten to turn into a vamp
         */
        @Override
        public boolean Use(Character character, Field field) {
                if (character.isVampire()) {
                        Character c = ((EvilCharacter) character).turnBackIntoHumain();
                        Location loc = character.getLocation();
                        field.clearCharacter(character);
                        field.place(c, loc);

                } else if (character.isHuman()) {
                        ((Human) character).setHasBeenBittenByVamp(false,field.getConsolePanel());
                }
                return super.Use(character, field);
        }

        public boolean isCureVamp() {
                return true;
        }

        /**
         * Get a string representing the type of the object
         * 
         * @return
         */
        @Override
        public String getType() {
                return TYPE;
        }

}
