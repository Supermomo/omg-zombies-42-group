package zombiegame.objects;

import zombiegame.objects.Unusable;
import zombiegame.objects.Weapon;

/**
 * The enum enumerating the various Unusable objects available in the game
 * 
 * @author gaubert
 *
 */
public enum Miscellaneous implements Unusable{
        
        SILVERBULLET{
                @Override
                public boolean isUsableWith(Weapon item) {
                        return item.equals(Weapon.SHOTGUN);
                }            
        };

}
