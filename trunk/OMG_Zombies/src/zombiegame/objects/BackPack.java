package zombiegame.objects;

import java.util.ArrayList;
import java.util.List;
import zombiegame.objects.Item;
import zombiegame.people.Player;

public class BackPack {

        private List<Wearable> inventory;
        private Wearable equiped;

        public BackPack() {
                inventory = new ArrayList<Wearable>();
                equiped = null;
        }

        public List<Wearable> getItemList() {
                return inventory;
        }

        public boolean equip(Wearable item, Player p) {
                if (isInBackPack(item)) {
                        item.select(p);
                        equiped=item;
                        return true;
                }
                return false;
        }

        public boolean isInBackPack(Wearable item) {
                for (Wearable i : inventory) {
                        if (i.getClass().equals(item.getClass())) {
                                return true;
                        }
                }
                return false;
        }

        public boolean addItem(Wearable item) {
                if(!isInBackPack(item)){
                        inventory.add(item);
                        return true;
                }
                return false;
        }

        public Wearable getEquiped() {
                return equiped;
        }
}
