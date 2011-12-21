package zombiegame.objects.weapons;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;

public class Shotgun extends Weapon {
        
        private static final int shotgunAmmo=8;
        
        public Shotgun() {
                super(Shotgun.shotgunAmmo);
        }
        
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
        

}
