package zombiegame;

public interface Usable {

        /**
         * Use the item on the character
         * @param character on witch the item will be used
         * @return wether or not the item has been used successfully
         */
        public void Use (Character character);
}
