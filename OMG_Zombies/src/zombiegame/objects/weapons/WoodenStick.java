package zombiegame.objects.weapons;

import zombiegame.engine.Field;
import zombiegame.people.Character;

/**
 * Class for the wooden sticks objects
 * 
 * @author gaubert
 * 
 */
public class WoodenStick extends Weapon {

        private static final String TYPE = "WOODEN STICK";

        private static final int woodenStickAmmo = 1;

        /**
         * Constructor for a wooden stick object is initialized with the default
         * number of ammo
         */
        public WoodenStick() {
                super(WoodenStick.woodenStickAmmo);
        }

        /**
         * attack with the wooden stick
         */
        public boolean Use(Character character, Field field) {
                super.Use(character, field);
                if (character.isVampire()) {
                        character.reduceHealthPoints(200);
                        return true;
                } else {
                        character.reduceHealthPoints(1); // Ouch !
                        return false;
                }           
        }

        public boolean isWoodenStick() {
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
