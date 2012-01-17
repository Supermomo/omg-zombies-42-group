package zombiegame.people;


import java.awt.Image;

import zombiegame.engine.Field;
import zombiegame.engine.Location;
import zombiegame.objects.BackPack;


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


}
