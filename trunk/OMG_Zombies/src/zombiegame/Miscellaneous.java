package zombiegame;

public enum Miscellaneous implements Unusable{
        
        SILVERBULLET{
                @Override
                public boolean isUsableWith(Weapon item) {
                        return item.equals(Weapon.SHOTGUN);
                }            
        };

}
