package zombiegame.objects.weapons;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;

/**
 * Class of the Shotgun weapon
 * @author gaubert
 *
 */
public class Shotgun extends Weapon {
        
        private static final int shotgunAmmo=8;
        
        /**
         * Constructor for a shotgun object
         * is initialized with the default number of ammo
         */
        public Shotgun() {
                super(Shotgun.shotgunAmmo);
        }
        
        /**
         * attack with the shotgun
         */
        public void Use(Character character) {
                if(character.isZombie()){
                        ((EvilCharacter)character).setStun(true);
                        character.reduceHealthPoints(5);
                }
                else{
                        character.reduceHealthPoints(1);
                }
                super.Use(character);
        }
        
        public boolean isShotgun(){
                return true;
        }    

}
