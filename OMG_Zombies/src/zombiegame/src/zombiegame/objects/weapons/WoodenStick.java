package zombiegame.objects.weapons;

import zombiegame.people.Character;

public class WoodenStick extends Weapon{
        private static final int woodenStickAmmo=1;
        
        public WoodenStick() {
                super(WoodenStick.woodenStickAmmo);
        }

        public void Use(Character character) {
                if (character.isVampire()) {
                        character.reduceHealthPoints(200);
                } else {
                        character.reduceHealthPoints(1); // Ouch !
                }
        }

}
