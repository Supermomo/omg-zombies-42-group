package zombiegame.objects;

import java.util.ArrayList;
import java.util.List;
import zombiegame.objects.Item;
import zombiegame.people.Player;

public class BackPack {

        private List<Wearable> inventory;
        private Wearable  equiped;

        public BackPack() {
                inventory = new ArrayList<Wearable>();
                equiped = null;
        }

        public List<Wearable> getItemList() {
                return inventory;
        }

        public boolean equip(Wearable item, Player p) {
                if (isInBackPack(item)) {
                        System.out.println("item used");
                        System.out.println(((Item)item).getType()+" uses "+((Item)item).getUses());
                        item.select(p);
                        if(((Item)item).getUses()<=0){
                                System.out.println("Item removed");
                                inventory.remove(item);
                        }
                        else if( !((Item)item).isWeapon() && !((Item)item).isEdible()){
                                equiped=item;
                        }
                        System.out.println("inventiry size :"+inventory.size());
                        return true;
                }
                return false;
        }

        public boolean isInBackPack(Wearable item) {
                for (Wearable i : inventory) {
                        System.out.println(((Item)i).getType());
                        if (i.getClass().equals(item.getClass())) {
                                return true;
                        }
                }
                return false;
        }

        public boolean addItem(Wearable item) {
                for(Wearable w : inventory){
                        if(((Item)w).getUses()<=0){
                                inventory.remove(w);
                        }
                }
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
