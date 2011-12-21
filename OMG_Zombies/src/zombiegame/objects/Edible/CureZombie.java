package zombiegame.objects.Edible;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;
import zombiegame.people.Zombie;

public class CureZombie extends Edible{

        private static final int cureUses =1;
        
        public CureZombie() {
                super(CureZombie.cureUses);
        }
        
        public void Use(Character character) {
                if (character.isZombie()) {
                        if (((Zombie) character).isMadZombie()) {
                                character = ((EvilCharacter) character)
                                                .turnBackIntoHumain();
                        }

                }
                super.Use(character);
        }
        
                

}
