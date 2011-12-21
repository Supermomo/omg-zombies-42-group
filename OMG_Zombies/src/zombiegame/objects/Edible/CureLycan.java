package zombiegame.objects.Edible;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;

public class CureLycan extends Edible{

        private static final int cureUses=1;
        
        public CureLycan() {
                super(CureLycan.cureUses);
        }
        
        public void Use(Character character) {
                if (character.isWerewolf()) {
                        character = ((EvilCharacter) character).turnBackIntoHumain();
                }
                super.Use(character);
        }

}
