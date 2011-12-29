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

public class Helico 
{

    private Item objetEnSoute;
    private Field map;
    
    public Helico(Field map)
    {
        this.map = map;
        this.objetEnSoute = createSoute();
    }
    
    private Item createSoute() 
    {
        Item objet=null;
        
        Random r = new Random();
        int rand = r.nextInt(3);
        
        if(rand == 1)
        {
            rand = r.nextInt(3);
            switch (rand) 
            {
                case 0:
                    objet = new LiquidNitrogen();
                break;

                case 1:
                    objet = new Shotgun();
                break;
                
                case 2:
                    objet = new WoodenStick();
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
                    objet = new Bread();
                break;
                
                case 1:
                    objet = new CureLycan();
                break;
                    
                case 2:
                    objet = new CureVamp();
                break;
                    
                case 3:
                    objet = new CureZombie();
                break;
                    
                case 4:
                    objet = new MajorPotion();
                break;
                    
                case 5:
                    objet = new MinorPotion();
                break;

                default:
                break;
            }
        }
        else {
                objet=new SilverBullet();
        }
        
        return objet;
    }
    
    public void dropSoute()
    {
        Random r = new Random();
        map.place(objetEnSoute,r.nextInt(map.getDepth()),r.nextInt(map.getWidth()));        
    }
    
    
    
}
