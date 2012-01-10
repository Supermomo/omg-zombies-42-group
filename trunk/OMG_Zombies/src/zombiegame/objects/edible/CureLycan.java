package zombiegame.objects.edible;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;
import zombiegame.people.Human;

/**
 * the class for the potion that cure lycanthropia
 * @author gaubert
 *
 */
public class CureLycan extends Edible{

        private static final String TYPE = "CURE LYCAN";
        
        private static final int cureUses=1;
        
        /**
         * create a new object for the potion<BR>
         * the potion can only be used once, either to cure a avoid to turn into a lycan after being bitten
         * or to cure an already turned lycan
         */
        public CureLycan() {
                super(CureLycan.cureUses);
        }
        
        public void Use(Character character, Field field) {
                if (character.isWerewolf() && ! character.isWerewolfCrew()) {
                        Character c=((EvilCharacter) character).turnBackIntoHumain();
                        Location loc=character.getLocation();
                        field.clear(loc);
                        field.place(c, loc);
                }
                else if(character.isHuman()){
                        ((Human)character).setHasBeenBittenByLycan(false);
                }
                super.Use(character,field);
        }

        
        public boolean isCureLycan(){
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
