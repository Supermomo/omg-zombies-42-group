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
        
        private Item getInstanceInBackPack(Item it){
                for (Wearable i : inventory) {
                       
                        if (((Item)i).getType().equals(((Item)it).getType())) {
                                return (Item) i;
                        }
                }
                return null;
        }
        public boolean equip(Wearable item, Player p) {
                if (isInBackPack(item)) {
                        Wearable i=(Wearable)getInstanceInBackPack((Item)item);
                        System.out.println(((Item)i).getType());
                          
                        i.select(p);
                        System.out.println("item used");
                        System.out.println(((Item)item).getType()+" uses "+((Item)i).getUses());
                        
                        if(((Item)i).getUses()<=0){
                                System.out.println("Item removed");
                                inventory.remove(i);
                        }
                        else if( !((Item)item).isWeapon() && !((Item)item).isEdible()){
                                equiped=i;
                        }
                        System.out.println("inventiry size :"+inventory.size());
                        return true;                                  
                }
                return false;
        }

        public boolean isInBackPack(Wearable item) {
                for (Wearable i : inventory) {
                        if (((Item)i).getType().equals(((Item)item).getType())) {
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
                else{
                        for (Wearable i : inventory) {                             
                                if (((Item)i).getType().equals(((Item)item).getType())) {
                                        i=item;
                                        return true;
                                }
                        }  
                }
                return false;
        }
        
        public boolean remove(Item item){
                for (Wearable i : inventory) {
                        if (i.getClass().equals(item.getClass())) {
                                inventory.remove(i);
                                return true;
                        }
                }
                return false;
        }

        public Wearable getEquiped() {
                return equiped;
        }
}
