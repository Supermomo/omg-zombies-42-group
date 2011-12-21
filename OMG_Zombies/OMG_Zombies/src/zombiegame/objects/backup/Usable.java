package zombiegame.objects.backup;

import zombiegame.people.Character;

/**
 * Interface for an object that can be use by the character
 * 
 * @author gaubert
 * 
 */
public interface Usable {
        
        public int remainingUses=0;

        /**
         * Use the item on the character
         * 
         * @param character
         *                on witch the item will be used
         * @return wether or not the item has been used successfully
         */
        public void Use(Character character);
}
