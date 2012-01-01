package zombiegame.objects.edible;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;
import zombiegame.people.Zombie;

/**
 * the class for the potion that cure zombie from their ills
 * @author gaubert
 *
 */
public class CureZombie extends Edible{

        private static final String TYPE="CURE ZOMBIE POTION";
        
        private static final int cureUses =1;
        
        /**
         * create the object that can cure zombyism
         */
        public CureZombie() {
                super(CureZombie.cureUses);
        }
        
        /**
         * Cure an already turned zombie or prevent a human to turn into a brainless zombie
         * a mad zombie can't be cured
         */
        public void Use(Character character) {
                if (character.isZombie()) {
                        if (!((Zombie) character).isMadZombie()) {
                                character = ((EvilCharacter) character)
                                                .turnBackIntoHumain();
                        }

                }
                super.Use(character);
        }
        
        
        public boolean isCureZomb(){
                return true;
        }
        
        /**
         * Get a string representing the type of the object
         * @return
         */
        @Override
        public String getType(){
                return TYPE;
        }

}
