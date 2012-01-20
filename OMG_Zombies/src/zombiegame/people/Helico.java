package zombiegame.people;


import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import zombiegame.engine.Field;
import zombiegame.engine.FieldFrame;
import zombiegame.engine.Location;
import zombiegame.objects.Item;
import zombiegame.objects.edible.Bread;
import zombiegame.objects.edible.CureLycan;
import zombiegame.objects.edible.CureVamp;
import zombiegame.objects.edible.CureZombie;
import zombiegame.objects.edible.MajorPotion;
import zombiegame.objects.edible.MinorPotion;
import zombiegame.objects.micellaneous.SilverBullet;
import zombiegame.objects.micellaneous.VampireCape;
import zombiegame.objects.micellaneous.WerewolfHide;
import zombiegame.objects.weapons.LiquidNitrogen;
import zombiegame.objects.weapons.Shotgun;
import zombiegame.objects.weapons.WoodenStick;

/**
 * Class of the helicopter dropint the items on the map
 * 
 * @author gaubert
 * 
 */
public class Helico {

        private Item itemInStorage;
        private Field map;

        /**
         * Constructor creating an helicopter object
         * 
         * @param map
         */
        public Helico(Field map) {
                this.map = map;
                this.itemInStorage = initializeStorage();
        }

        /**
         * Initialize what is in the storage PS : put in package mode for tests
         * 
         * @return
         */
        Item initializeStorage() {
                Item object = null;

                Random r = new Random();
                int rand = r.nextInt(7);

                if (rand >4) {
                        rand = r.nextInt(3);
                        switch (rand) {
                        case 0:
                                object = new LiquidNitrogen();
                                break;

                        case 1:
                                object = new Shotgun();
                                break;

                        case 2:
                                object = new WoodenStick();
                                break;

                        default:
                                break;
                        }
                } else if (rand >1) {
                        rand = r.nextInt(6);
                        switch (rand) {
                        case 0:
                                object = new Bread();
                                break;

                        case 1:
                                object = new CureLycan();
                                break;

                        case 2:
                                object = new CureVamp();
                                break;

                        case 3:
                                object = new CureZombie();
                                break;

                        case 4:
                                object = new MajorPotion();
                                break;

                        case 5:
                                object = new MinorPotion();
                                break;

                        default:
                                break;
                        }
                } else {
                        object = new SilverBullet();                       
                }

                return object;
        }

        /**
         * Drop the content of the storage on a random spot of the map
         * Aim around the area where the player stand
         */
        public void dropItem(FieldFrame frame,Location loc) {

                Toolkit tk = java.awt.Toolkit.getDefaultToolkit();

                int heightBox = frame.getPanel().getHeight() / (map.getDepth() + 2);
                int widthBox = frame.getPanel().getWidth() / (map.getWidth() + 2);

                Image imgHelico = tk.getImage(this.getClass().getResource("/img/helico.png"));
                Image imgHelicoRed = tk.getImage(this.getClass().getResource("/img/helicoRed.png"));
                Random r = new Random();
                int rx=loc.getCol()+r.nextInt(7)-3;
                int ry=loc.getRow()+r.nextInt(7)-3;
                int y = Math.max(0, ry);
                int x = Math.max(0, rx);
                if(x>=map.getWidth()){
                        x=map.getWidth()-1;
                }
                if(y>=map.getDepth()){
                        y=map.getDepth()-1;
                }
                map.placeItem(itemInStorage, y, x);

                for (int j = 0; j < map.getWidth(); j++) {
                        frame.getPanel().getGraphics().drawImage(imgHelico, (j + 1) * widthBox,
                                        (y + 1) * heightBox, widthBox, heightBox, null);
                        if (j == x) {
                                frame.getPanel().getGraphics().drawImage(imgHelicoRed,
                                                (j + 1) * widthBox, (y + 1) * heightBox, widthBox,
                                                heightBox, null);
                                try {
                                        Thread.sleep(500);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                        frame.repaint();
                }
        }

}
