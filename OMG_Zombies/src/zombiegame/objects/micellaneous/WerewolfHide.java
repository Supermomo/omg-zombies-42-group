package zombiegame.objects.micellaneous;

import zombiegame.objects.Wearable;
import zombiegame.people.Player;

public class WerewolfHide extends Miscellaneous implements Wearable{
        
        private static final String TYPE = "WEREWOLF HIDE";

        public WerewolfHide() {
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
