package zombiegame.engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import zombiegame.people.*;
import zombiegame.people.Character;

import javax.swing.JPanel;

public class FieldPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private Field field;
        
        private Image imgMap;
        private Image imgHuman;
        private Image imgHumanShotgun;
        private Image imgHumanFlameThrower;
        private Image imgHumanStick;
        private Image imgZombie;
        private Image imgWerewolf;
        private Image imgWerewolfCrew;
        private Image imgVampire;
        
        
        public FieldPanel(Field fiel) {
                super();
                this.setBackground(java.awt.Color.green);
                this.field = fiel;
                
                Toolkit tk=java.awt.Toolkit.getDefaultToolkit();
                
                imgMap=tk.getImage(this.getClass().getResource("/img/map_grass2.jpg"));
                imgHuman=tk.getImage(this.getClass().getResource("/img/Human.png"));
                imgHumanShotgun=tk.getImage(this.getClass().getResource("/img/HumanShotgun.png"));
                imgHumanFlameThrower=tk.getImage(this.getClass().getResource("/img/HumanFlamThrower.png"));
                imgHumanStick=tk.getImage(this.getClass().getResource("/img/HumanWoodenStick.png"));
                imgZombie=tk.getImage(this.getClass().getResource("/img/Zombie.png"));
                imgWerewolf=tk.getImage(this.getClass().getResource("/img/Werewolf.png"));
                imgWerewolfCrew=tk.getImage(this.getClass().getResource("/img/WerewolfCrew.png"));
                imgVampire=tk.getImage(this.getClass().getResource("/img/Vamp.png"));

        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);

                g.drawImage(imgMap, widthBox, heightBox, field.getWidth()*widthBox, field.getDepth()*heightBox, null);
                
                for (int i = 0; i < field.getDepth(); i++) {
                        //dessin ligne
                        g.drawLine(widthBox,(i+1)*heightBox, (field.getWidth()+1)*widthBox, (i+1)*heightBox);
                        for (int j = 0; j < field.getWidth(); j++) {
                                //dessin colonne
                                g.drawLine((j+1)*widthBox,heightBox, (j+1)*widthBox,(field.getDepth()+1)*heightBox);
                                if (field.getObjectAt(i, j) != null) {
                                        Character o=(Character)field.getObjectAt(i,j);
                                        if(o.isHuman()){
                                                Human h=(Human)o;
                                                if(h.isArmed()){
                                                        if(h.getWeapon().isLiquidNitrogen()){
                                                                g.drawImage(imgHumanFlameThrower, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                heightBox, null);
                                                        }
                                                        else if(h.getWeapon().isShotgun()){
                                                                g.drawImage(imgHumanShotgun, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                heightBox, null);
                                                        }
                                                        else if(h.getWeapon().isWoodenStick()){
                                                                g.drawImage(imgHumanStick, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                heightBox, null);
                                                        }
                                                        
                                                }
                                                g.drawImage(imgHuman, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                heightBox, null);
                                        }
                                        else if(o.isWerewolfCrew()){
                                                g.drawImage(imgWerewolfCrew, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                heightBox, null);
                                        }
                                        else if(o.isWerewolf()){
                                                g.drawImage(imgWerewolf, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                heightBox, null);
                                        }
                                        else if(o.isVampire()){
                                                g.drawImage(imgVampire, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                heightBox, null);
                                        }
                                        else if(o.isZombie()){
                                                g.drawImage(imgZombie, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                heightBox, null);
                                        }
                                        
                                } else {
                                        /*g.setColor(java.awt.Color.gray);
                                        g.fillRect((j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                        heightBox);*/
                                }
                        }
                }

        }

}
