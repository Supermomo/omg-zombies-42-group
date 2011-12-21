package zombiegame.objects.Edible;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;

public class CureVamp extends Edible{

        private static final int cureUse =1;
        
        public CureVamp() {
                super(CureVamp.cureUse);
        }
        
        public void Use(Character character) {
                if (character.isVampire()) {
                        character = ((EvilCharacter) character).turnBackIntoHumain();
                }
                super.Use(character);
        }

}
