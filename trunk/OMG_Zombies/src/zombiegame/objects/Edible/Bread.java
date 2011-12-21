package zombiegame.objects.Edible;

import zombiegame.people.Character;

public class Bread extends Edible{

        private static final int breadUses = 4;
        
        public Bread() {
                super(Bread.breadUses);
        }

        public void Use(Character character) {
                character.increaseHealthPoints(5);
                super.Use(character);
        }
}
