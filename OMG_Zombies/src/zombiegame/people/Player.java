package zombiegame.people;


import java.awt.Image;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.objects.BackPack;
import zombiegame.objects.Item;
import zombiegame.objects.Wearable;
import zombiegame.objects.edible.Edible;
import zombiegame.objects.micellaneous.Miscellaneous;
import zombiegame.objects.weapons.Weapon;


/**
 * Class for a playable character
 * possess an inventory
 * can equip items
 * @author gaubert
 *
 */
public class Player extends Human{
        
        private boolean isHuman=true;
        private boolean isWerewolf=false;
        private boolean isVampire=false;
        private Image imgPlayer;
        private Image imgPlayerFlam;
        private Image imgPlayerShotgun;
        private Image imgPlayerStick;
        
        /**The width of the light Box from 0 to half the size of the field, 0 means no lightBox*/
        private int lightBoxWidth;
        
        private BackPack backPack=new BackPack();

        public Player(String name, int healthPoints) {
                super(name, healthPoints);  
                lightBoxWidth=0;
        }
        
        public BackPack getBackPack(){
                return backPack;
        }
        
        public boolean isHuman(){
                return isHuman;
        }
        
        public boolean isVampire(){
                return isVampire;
        }
        
        public boolean isWerewolf(){
                return isWerewolf;
        }
        
        public boolean isPlayer(){
                return true;
        }
        
        public int getLightBoxWidth(){
                return lightBoxWidth;
        }
        
        public void setImagePlayer(Image play){
                imgPlayer=play;
        }
        
        public Image getImagePlayer(){
                return imgPlayer;
        }
        
        @Override
        public void endOfTurn(Field field){
                super.endOfTurn(field);
                this.say("I have "+getHealthPoints()+" hp left", field.getConsolePanel());
        }
        
        
        /**
         * Method triggered when the new turn start. Perform the action of this
         * character Clear the encounter character if dead and human, turn it
         * into zombie if human After the action, if human, pick up the object
         * on the ground(if there is any)
         * 
         * Do nothing if the character is stun
         * 
         */
        public void move(Location dest, Field field, Field fieldObj) {

                boolean stun = false;

                if (!stun) {

                        this.say("I'm now acting", field.getConsolePanel());

                        if (field.getObjectAt(dest) == null) {
                                Location a = this.location;
                                field.place(this, dest);
                                field.clear(a);
                                try {
                                        ((Human) this).pickUpObject(fieldObj, dest);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                        System.out
                                                        .println("Cannot cast into human in method action");
                                }
                        } else {
                                try {
                                        Character c = (Character) field.getObjectAt(dest);
                                        encounterCharacter(c, field);
                                        if (c.getHealthPoints() == 0) {
                                                field.clear(dest);
                                        }
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }
        
        
        /**
         * the encounter between this character and c Greet the encountered
         * character if he is human Use weapon if he has one and if the
         * encountered character is an enemy Eat something if he need to
         */
        public boolean encounterCharacter(Character c, Field field) {

                boolean result = false;
                if (edible != null) {
                        if (c.isVampire() && edible.isCureVamp()) {
                                edible.Use(c, field);
                                result = true;
                        } else if (c.isWerewolf() && edible.isCureLycan()) {
                                edible.Use(c, field);
                                result = true;
                        } else if (c.isZombie() && edible.isCureZomb()) {
                                edible.Use(c, field);
                                result = true;
                        } else if (edible.isIncreasingHp() && super.getHealthPoints() < 30) {
                                edible.Use(this, field);
                        }

                        if (edible.getUses() <= 0) {
                                this.backPack.remove(edible);
                                edible = null;                    
                        }

                }
                if (this.item != null) {
                        if (this.item.getUses() <= 0) {
                                this.backPack.remove(item);
                                item = null;
                        }
                }

                if (isArmed() && !c.isHuman() && !c.defend(this,field)) {
                        result = weapon.Use(c, field);
                        if (weapon.getUses() <= 0) {
                                this.backPack.remove(weapon);
                                weapon = null;
                        }
                        this.say("humm...may be i shoulden't have done that...", field.getConsolePanel());
                } else if (c.isHuman()) {
                        this.say("Hi " + c.getName() + "...what's up ?", field.getConsolePanel());
                        baby(field);

                } else {
                        //result=true;
                        this.say("Go away " + c.getName() + " before i start to...humm...beg you to leave me alive ???", field.getConsolePanel());
                }
                return result;
        }

        /**
         * @param imgPlayerFlam the imgPlayerFlam to set
         */
        public void setImgPlayerFlam(Image imgPlayerFlam) {
                this.imgPlayerFlam = imgPlayerFlam;
        }

        /**
         * @return the imgPlayerFlam
         */
        public Image getImgPlayerFlam() {
                return imgPlayerFlam;
        }

        /**
         * @param imgPlayerShotgun the imgPlayerShotgun to set
         */
        public void setImgPlayerShotgun(Image imgPlayerShotgun) {
                this.imgPlayerShotgun = imgPlayerShotgun;
        }

        /**
         * @return the imgPlayerShotgun
         */
        public Image getImgPlayerShotgun() {
                return imgPlayerShotgun;
        }

        /**
         * @param imgPlayerStick the imgPlayerStick to set
         */
        public void setImgPlayerStick(Image imgPlayerStick) {
                this.imgPlayerStick = imgPlayerStick;
        }

        /**
         * @return the imgPlayerStick
         */
        public Image getImgPlayerStick() {
                return imgPlayerStick;
        }


        /**
         * pick up an object Replace the current object by the new one associate
         * the objects if possible if the object find is already in the
         * inventory, add the found one to the possessed one
         */
        @Override
        public void pickUpObject(Field fieldObj, Location loc) {

                if (fieldObj.getObjectAt(loc) != null) {
                        Item it = null;
                        try {
                                it = (Item) fieldObj.getObjectAt(loc);

                                if (this.edible != null && it.getType().equals(this.edible.getType())) {
                                        this.edible.addUses(it.getUses());
                                } else if (this.item != null && it.getType().equals(this.item.getType())) {
                                        this.item.addUses(it.getUses());
                                } else if (this.weapon != null && it.getType().equals(this.weapon.getType())) {
                                        this.weapon.addUses(it.getUses());
                                } else if (it.isEdible()) {
                                        fieldObj.clear(loc);
                                        fieldObj.placeItem(this.edible, loc.getRow(), loc.getCol());
                                        this.edible = (Edible) it;
                                } else if (it.isMiscellaneous()) {
                                        fieldObj.clear(loc);
                                        fieldObj.placeItem(this.item, loc.getRow(), loc.getCol());
                                        this.item = (Miscellaneous) it;
                                } else if (it.isWeapon()) {
                                        fieldObj.clear(loc);
                                        fieldObj.placeItem(this.weapon, loc.getRow(), loc.getCol());
                                        this.weapon = (Weapon) it;
                                }

                                if (weapon != null && item != null) {
                                        if (item.isUsableWith(weapon)) {
                                                weapon.useWith(item);
                                        }
                                }
                                
                                try {
                                        backPack.addItem((Wearable)it);
                                        System.out.println(it.getType()+" has been add to the backPack, bp size "+backPack.getItemList().size());
                                } catch (Exception e) {
                                        System.out.println(it.getType()+" is not a wearable object : cannot addd it to the backpack");
                                }

                                say("I just picked up a "+it.getType(), fieldObj.getConsolePanel());

                        } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Fail to pick up the object");
                        }

                }
        }
        
}
