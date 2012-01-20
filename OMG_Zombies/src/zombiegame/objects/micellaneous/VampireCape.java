package zombiegame.objects.micellaneous;

import zombiegame.objects.Wearable;
import zombiegame.people.Player;

public class VampireCape extends Miscellaneous implements Wearable{

        private static final String TYPE = "VAMPIRE CAPE";
        
        public VampireCape() {
                super(1);
        }

        @Override
        public void select(Player p) {
        }
        
        @Override
        public String getType(){
                return TYPE;
        }

}
