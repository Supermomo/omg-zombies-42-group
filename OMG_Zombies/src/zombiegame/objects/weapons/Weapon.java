package zombiegame.objects.weapons;

import zombiegame.objects.Usable;
import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;

/**
 * The Class enumerating all the weapon available
 * in the game
 * 
 * @author gaubert
 *
 */

public abstract class Weapon implements Usable {
        
    public int ammunition;

    public Weapon(int ammo){
            this.ammunition=ammo;
    }
    
    @Override
    public void Use(Character character)
    {
            
    }

        
}
