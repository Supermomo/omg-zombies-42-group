package zombiegame.people;


import java.util.Random;

import zombiegame.engine.Field;
import zombiegame.objects.Item;
import zombiegame.objects.edible.Bread;
import zombiegame.objects.edible.CureLycan;
import zombiegame.objects.edible.CureVamp;
import zombiegame.objects.edible.CureZombie;
import zombiegame.objects.edible.MajorPotion;
import zombiegame.objects.edible.MinorPotion;
import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.objects.weapons.LiquidNitrogen;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.WoodenStick;

/**
 * Class of the helicopter dropint the items on the map
 * @author gaubert
 *
 */
public class Helico 
{

    private Item itemInStorage;
    private Field map;
    
    /**
     * Constructor creating an helicopter object
     * @param map
     */
    public Helico(Field map)
    {
        this.map = map;
        this.itemInStorage = initializeStorage();
    }
    
    /**
     * Initialize what is in the storage
     * @return
     */
    private Item initializeStorage() 
    {
        Item object=null;
        
        Random r = new Random();
        int rand = r.nextInt(3);
        
        if(rand == 1)
        {
            rand = r.nextInt(3);
            switch (rand) 
            {
                case 0:
                    object = new LiquidNitrogen();
                break;

                case 1:
                    object = new Shotgun();
                break;
                
                case 2:
                    object = new WoodenStick();
                break;
                
                default:
                break;
            }
        }
        else if(rand==2)
        {
            rand = r.nextInt(6);
            switch (rand)
            {
                case 0:
                    object = new Bread();
                break;
                
                case 1:
                    object = new CureLycan();
                break;
                    
                case 2:
                    object = new CureVamp();
                break;
                    
                case 3:
                    object = new CureZombie();
                break;
                    
                case 4:
                    object = new MajorPotion();
                break;
                    
                case 5:
                    object = new MinorPotion();
                break;

                default:
                break;
            }
        }
        else {
                object=new SilverBullet();
        }
        
        return object;
    }
    
    /**
     * Drop the content of the storage on a random spot of the map
     */
    public void dropItem()
    {
        Random r = new Random();
        map.placeItem(itemInStorage,r.nextInt(map.getDepth()),r.nextInt(map.getWidth()));        
    }
    
    
    
}
