package zombiegame.objects.weapons;

import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;

/**
 * Class of the Shotgun weapon
 * @author gaubert
 *
 */
public class Shotgun extends Weapon {
        
        
        private static final String TYPE="SHOTGUN";
        
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
            
            if(character.isZombie())
            {
                ((EvilCharacter)character).setStun(true);
                        character.reduceHealthPoints(5);
            }
            else
            {
                if(character.isWerewolf() && (this.usedWith(new SilverBullet())))
                {
                    System.out.println("bla");
                       character.reduceHealthPoints(300);     
                }
                else
                {
                    character.reduceHealthPoints(1);
                }
                    
            }
            super.Use(character);
        }
        
        /**
         * Get a string representing the type of the object
         * @return
         */
        @Override
        public String getType(){
                return TYPE;
        }
        
        public boolean isShotgun(){
                return true;
        }    

}
