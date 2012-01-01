package zombiegame.objects;
/**
 * Class for an item <BR>
 * Abstract superclass of every kind of item
 * @author gaubert
 *
 */
public abstract class Item {

        private int numberOfUses;
        
        public Item(int uses)
        {
                if(uses<=0){
                        RuntimeException e=new RuntimeException("Number of uses in Item constructor should be >0");
                        e.printStackTrace();
                        throw e;
                }
                this.numberOfUses=uses;
        }
        public boolean isWeapon() {
                return false;
        }

        public boolean isEdible() {
                return false;
        }

        public boolean isMiscellaneous() {
                return false;
        }

        public void loseUses(){
                this.numberOfUses--;
        }
        
        public int getUses(){
                return this.numberOfUses;
        }
        
        public void addUses(int uses){
                if(uses<=0){
                        RuntimeException e=new RuntimeException("Number of uses to add should be >0");
                        e.printStackTrace();
                        throw e;
                }
                this.numberOfUses+=uses;
        }
}
