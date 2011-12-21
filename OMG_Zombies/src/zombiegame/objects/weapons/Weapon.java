package zombiegame.objects.weapons;

import zombiegame.objects.Unusable;
import zombiegame.objects.Usable;
import zombiegame.objects.Micellaneous.Miscellaneous;
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
        
    private int ammunition;

    private Miscellaneous usedWith=null;
    
    public Weapon(int ammo){
            this.ammunition=ammo;
    }
    
    @Override
    public void Use(Character character)
    {
            if(usedWith==null){
                    this.loseAmmo();  
            }
            else {
                    usedWith.loseUses();
                    if(usedWith.getUses()==0){
                            usedWith=null;
                    }
            }

    }
    
    /**
     * @param ammunition the ammunition to set
     */
    public void findAmmo(int am) {
            this.ammunition+=am;
    }
    
    /**
     * @param ammunition the ammunition to set
     */
    private void loseAmmo() {
            this.ammunition--;
    }

    /**
     * @return the ammunition
     */
    public int getAmmunition() {
            return ammunition;
    }
    
    public void useWith(Miscellaneous item){
            this.usedWith=item;
    }

        
}
