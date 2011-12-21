package zombiegame.objects.Edible;

import zombiegame.people.Character;

public class MajorPotion extends Edible{

        private static final int potionUses=1;
        
        public MajorPotion() {
                super(MajorPotion.potionUses);
             
        }

        public void Use(Character character) {
                character.increaseHealthPoints(25);
                super.Use(character);
        }
}
