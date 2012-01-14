package zombiegame.objects.weapons;

import zombiegame.engine.Field;
import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;

/**
 * Class of the Shotgun weapon
 * 
 * @author gaubert
 * 
 */
public class Shotgun extends Weapon {

        private static final String TYPE = "SHOTGUN";

        private static final int shotgunAmmo = 8;

        /**
         * Constructor for a shotgun object is initialized with the default
         * number of ammo
         */
        public Shotgun() {
                super(Shotgun.shotgunAmmo);
        }

        /**
         * attack with the shotgun
         */
        public boolean Use(Character character, Field field) {
                
                super.Use(character, field);
                
                if (character.isZombie()) {
                        ((EvilCharacter) character).setStun(true);
                        character.reduceHealthPoints(10);
                        return true;
                } else {
                        if (character.isWerewolf() && (this.usedWith(new SilverBullet()))) {
                                character.reduceHealthPoints(300);
                                return true;
                        } else {
                                character.reduceHealthPoints(1);
                                return false;
                        }
                        
                }
                
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

        public boolean isShotgun() {
                return true;
        }

}
