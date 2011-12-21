package zombiegame.objects.weapons;

import zombiegame.people.Character;

public class LiquidNitrogen extends Weapon{

        private static final int liquidNitrogenAmmo=1;
        
        public LiquidNitrogen() {
                super(LiquidNitrogen.liquidNitrogenAmmo);

        }
        
        public void Use(Character character) {
                if(character.isZombie()){
                        character.reduceHealthPoints(200);
                }
                else {
                        character.reduceHealthPoints(1);
                }
        }
        

}
