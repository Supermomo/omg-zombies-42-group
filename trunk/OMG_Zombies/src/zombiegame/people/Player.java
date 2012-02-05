package zombiegame.people;


import java.awt.Image;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.objects.BackPack;
import zombiegame.objects.Item;
import zombiegame.objects.Wearable;
import zombiegame.objects.edible.Edible;
import zombiegame.objects.micellaneous.Miscellaneous;
import zombiegame.objects.micellaneous.VampireCape;
import zombiegame.objects.micellaneous.WerewolfHide;
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
        private Image imgPlayerVamp;
        private Image imgPlayerFlamVamp;
        private Image imgPlayerShotgunVamp;
        private Image imgPlayerStickVamp;
        private Image imgPlayerWer;
        private Image imgPlayerFlamWer;
        private Image imgPlayerShotgunWer;
        private Image imgPlayerStickWer;
        
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
                isHuman=!isVampire() && !isWerewolf();
                return isHuman;
        }
        
        public boolean isVampire(){
                if(backPack.getEquiped()!=null){
                        isVampire=((Item)backPack.getEquiped()).getType().equals(new VampireCape().getType());
                }
                else {
                        isVampire=false;
                }

                return isVampire;
        }
        
        public boolean isWerewolf(){
                if(backPack.getEquiped()!=null){
                        isWerewolf=((Item)backPack.getEquiped()).getType().equals(new WerewolfHide().getType());
                }
                else {
                        isWerewolf=false;
                }
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
                                try {
                                        ((Human) this).pickUpObject(fieldObj, a);
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
                                        endCharacter(c, field);
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
                        say("I use "+weapon.getType()+" on "+c.getName(), field.getConsolePanel());
                        if (weapon.getUses() <= 0) {
                                this.backPack.remove(weapon);
                                weapon = null;
                        }
                        this.say("humm...may be i shoulden't have done that...", field.getConsolePanel());
                } else if (c.isHuman()) {
                        this.say("Hi " + c.getName() + "...what's up ? Do you have any bandages ?", field.getConsolePanel());
                        this.increaseHealthPoints(10);

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

        public void superSetWeapon(Weapon w){
                super.setWeapon(w);
        }
        
        public void setWeapon(Weapon w){
                backPack.equip(w, this);
        }
        
        public void setName(String name){
                super.name=name;
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
                                        fieldObj.clearItem(it);
                                        try {
                                                Wearable w=(Wearable)it;
                                        } catch (Exception e) {
                                                fieldObj.placeItem(this.edible, loc.getRow(), loc.getCol());
                                                this.edible = (Edible) it;
                                        }
                                } else if (it.isMiscellaneous()) {
                                        fieldObj.clearItem(it);
                                        try {
                                                Wearable w=(Wearable)it;
                                        } catch (Exception e) {
                                                fieldObj.placeItem(this.item, loc.getRow(), loc.getCol());
                                                this.item = (Miscellaneous) it;
                                        }
                                } else if (it.isWeapon()) {
                                        fieldObj.clearItem(it);
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

        /**
         * @param imgPlayerVamp the imgPlayerVamp to set
         */
        public void setImgPlayerVamp(Image imgPlayerVamp) {
                this.imgPlayerVamp = imgPlayerVamp;
        }

        /**
         * @return the imgPlayerVamp
         */
        public Image getImgPlayerVamp() {
                return imgPlayerVamp;
        }

        /**
         * @param imgPlayerFlamVamp the imgPlayerFlamVamp to set
         */
        public void setImgPlayerFlamVamp(Image imgPlayerFlamVamp) {
                this.imgPlayerFlamVamp = imgPlayerFlamVamp;
        }

        /**
         * @return the imgPlayerFlamVamp
         */
        public Image getImgPlayerFlamVamp() {
                return imgPlayerFlamVamp;
        }

        /**
         * @param imgPlayerShotgunVamp the imgPlayerShotgunVamp to set
         */
        public void setImgPlayerShotgunVamp(Image imgPlayerShotgunVamp) {
                this.imgPlayerShotgunVamp = imgPlayerShotgunVamp;
        }

        /**
         * @return the imgPlayerShotgunVamp
         */
        public Image getImgPlayerShotgunVamp() {
                return imgPlayerShotgunVamp;
        }

        /**
         * @param imgPlayerStickVamp the imgPlayerStickVamp to set
         */
        public void setImgPlayerStickVamp(Image imgPlayerStickVamp) {
                this.imgPlayerStickVamp = imgPlayerStickVamp;
        }

        /**
         * @return the imgPlayerStickVamp
         */
        public Image getImgPlayerStickVamp() {
                return imgPlayerStickVamp;
        }

        /**
         * @param imgPlayerWer the imgPlayerWer to set
         */
        public void setImgPlayerWer(Image imgPlayerWer) {
                this.imgPlayerWer = imgPlayerWer;
        }

        /**
         * @return the imgPlayerWer
         */
        public Image getImgPlayerWer() {
                return imgPlayerWer;
        }

        /**
         * @param imgPlayerFlamWer the imgPlayerFlamWer to set
         */
        public void setImgPlayerFlamWer(Image imgPlayerFlamWer) {
                this.imgPlayerFlamWer = imgPlayerFlamWer;
        }

        /**
         * @return the imgPlayerFlamWer
         */
        public Image getImgPlayerFlamWer() {
                return imgPlayerFlamWer;
        }

        /**
         * @param imgPlayerShotgunWer the imgPlayerShotgunWer to set
         */
        public void setImgPlayerShotgunWer(Image imgPlayerShotgunWer) {
                this.imgPlayerShotgunWer = imgPlayerShotgunWer;
        }

        /**
         * @return the imgPlayerShotgunWer
         */
        public Image getImgPlayerShotgunWer() {
                return imgPlayerShotgunWer;
        }

        /**
         * @param imgPlayerStickWer the imgPlayerStickWer to set
         */
        public void setImgPlayerStickWer(Image imgPlayerStickWer) {
                this.imgPlayerStickWer = imgPlayerStickWer;
        }

        /**
         * @return the imgPlayerStickWer
         */
        public Image getImgPlayerStickWer() {
                return imgPlayerStickWer;
        }
        
}
