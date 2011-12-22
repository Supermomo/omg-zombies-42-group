package zombiegame.objects.micellaneous;

import zombiegame.objects.weapons.Weapon;

public class SilverBullet extends Miscellaneous{
        
        public SilverBullet(int number){
                super(number);     
        }

        public boolean isUsableWith(Weapon item) {
                return false;
        }  
}
