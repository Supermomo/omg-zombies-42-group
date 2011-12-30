package zombiegame.objects;
/**
 * Class for an item <BR>
 * Abstract superclass of every kind of item
 * @author gaubert
 *
 */
public abstract class Item {

        public boolean isWeapon() {
                return false;
        }

        public boolean isEdible() {
                return false;
        }

        public boolean isMiscellaneous() {
                return false;
        }

}
