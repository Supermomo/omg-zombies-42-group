package zombiegame.objects.edible;

import zombiegame.people.Character;

public class MinorPotion extends Edible{

        private static final int potionUses=1;
        
        public MinorPotion(int use) {
                super(MinorPotion.potionUses);            
        }

        public void Use(Character character) {
                character.increaseHealthPoints(15);
                super.Use(character);
        }
}
