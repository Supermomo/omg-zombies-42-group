package zombiegame.objects.weapons;

import zombiegame.people.Character;

/**
 * Class of the weapon Liquid Nitrogen
 * @author gaubert
 *
 */
public class LiquidNitrogen extends Weapon{

        private static final String TYPE="LIQUID NITROGEN";
        
        private static final int liquidNitrogenAmmo=1;
        
        /**
         * Constructor for a liquid nitrogen object
         * is initialized with the default number of ammo
         */
        public LiquidNitrogen() {
                super(LiquidNitrogen.liquidNitrogenAmmo);

        }
        
        /**
         * Use the liquid nitrogen on the character
         */
        public void Use(Character character) {
                if(character.isZombie()){
                        character.reduceHealthPoints(200);
                }
                else {
                        character.reduceHealthPoints(1);
                }
                super.Use(character);
        }
       
        public boolean isLiquidNitrogen(){
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
