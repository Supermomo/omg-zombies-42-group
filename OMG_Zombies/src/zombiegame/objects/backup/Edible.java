package zombiegame.objects.backup;

import zombiegame.people.Character;
import zombiegame.people.EvilCharacter;
import zombiegame.people.Zombie;

/**
 * The enum enumerating all the eatable/drinkable stuffs of the game
 * 
 * @author gaubert
 *
 */
public enum Edible implements Usable {

        FOOD {
                @Override
                public void Use(Character character) {
                        character.increaseHealthPoints(5);
                }
        },
        MINORPOTION {
                @Override
                public void Use(Character character) {
                        character.increaseHealthPoints(15);
                }
        },
        MAJORPOTION {
                @Override
                public void Use(Character character) {
                        character.increaseHealthPoints(25);
                }
        },
        CUREVAMP {
                @Override
                public void Use(Character character) {
                        if (character.isVampire()) {
                                character = ((EvilCharacter) character).turnBackIntoHumain();
                        }
                }
        },
        CURELYCAN {
                @Override
                public void Use(Character character) {
                        if (character.isWerewolf()) {
                                character = ((EvilCharacter) character).turnBackIntoHumain();
                        }
                }
        },
        CUREZOMBIE {
                @Override
                public void Use(Character character) {
                        if (character.isZombie()) {
                                if (((Zombie) character).isMadZombie()) {
                                        character = ((EvilCharacter) character)
                                                        .turnBackIntoHumain();
                                }

                        }
                }
        };

}