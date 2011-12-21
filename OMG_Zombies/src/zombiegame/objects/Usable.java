package zombiegame.objects;

import zombiegame.people.Character;

/**
 * Interface for an object that can be use by the character
 * 
 * @author gaubert
 * 
 */
public interface Usable {

        /**
         * Use the item on the character
         * 
         * @param character
         *                on witch the item will be used
         * @return wether or not the item has been used successfully
         */
        public void Use(Character character);
}