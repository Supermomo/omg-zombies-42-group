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
    ;

    @Override
    public boolean isUsableWith(Weapon item)
    {
        // TODO Auto-generated method stub
        return false;
    }
        

}
