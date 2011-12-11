package zombiegame;

public enum Weapon implements Usable {
        
        WOODENSTICK {
                @Override
                public void Use(Character character) {
                        if(character.isVampire()){
                                character.reduceHealthPoints(200);
                        }
                        else{
                                character.reduceHealthPoints(1); //Ouch !
                        }
                }
        },
        LIQUIDNITROGEN {
                @Override
                public void Use(Character character) {
                        if(character.isZombie()){
                                character.reduceHealthPoints(200);
                        }
                        else {
                                character.reduceHealthPoints(1);
                        }
                }
        },
        SHOTGUN {
                @Override
                public void Use(Character character) {
                        if(character.isZombie()){
                                ((EvilCharacter)character).setStun(true);
                                character.reduceHealthPoints(5);
                        }
                        else{
                                character.reduceHealthPoints(1);
                        }
                }
        };
}
