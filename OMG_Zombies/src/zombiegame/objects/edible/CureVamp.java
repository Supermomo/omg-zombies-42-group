package zombiegame.objects.edible;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;
import zombiegame.people.Human;

/**
 * the cure potion used to turn a vampire back into a humain
 * @author gaubert
 *
 */
public class CureVamp extends Edible{

        private static final int cureUse =1;
        
        /**
         * create a CureVamp object that can only be use once
         */
        public CureVamp() {
                super(CureVamp.cureUse);
        }
        
        /**
         * Use the potion to cure a vampire or to prevent a human that have been bitten to turn into a vamp
         */
        public void Use(Character character) {
                if (character.isVampire()) {
                        character = ((EvilCharacter) character).turnBackIntoHumain();
                }
                else if(character.isHuman()) {
                        ((Human)character).setHasBeenBittenByVamp(false);
                }
                super.Use(character);
        }
        
        public boolean isCureVamp(){
                return true;
        }
        

}
