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
        private Image imgVampire;
        
        
        public FieldPanel(Field fiel) {
                super();
                this.setBackground(java.awt.Color.green);
                this.field = fiel;
                
                Toolkit tk=java.awt.Toolkit.getDefaultToolkit();
                
                imgMap=tk.getImage(this.getClass().getResource("/img/map_grass2.jpg"));
                imgHuman=tk.getImage(this.getClass().getResource("/img/Human.png"));
                imgHumanShotgun=tk.getImage(this.getClass().getResource("/img/HumanShotgun.png"));
                imgHumanFlameThrower=tk.getImage(this.getClass().getResource("/img/HumanWoodenStick.png"));
                imgHumanStick=tk.getImage(this.getClass().getResource("/img/HumanFlamThrower.png"));
                imgZombie=tk.getImage(this.getClass().getResource("/img/Zombie.png"));
                imgWerewolf=tk.getImage(this.getClass().getResource("/img/Werewolf.png"));
                imgVampire=tk.getImage(this.getClass().getResource("/img/Vamp.png"));
        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);
                
                g.drawImage(imgMap, widthBox, heightBox, field.getWidth()*widthBox, field.getDepth()*heightBox, null);
                
                for (int i = 0; i < field.getDepth(); i++) {
                        for (int j = 0; j < field.getWidth(); j++) {
                                if (field.getObjectAt(i, j) != null) {
                                        Character o=(Character)field.getObjectAt(i,j);
                                        if(o.isHuman()){
                                                g.drawImage(imgHuman, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                heightBox, null);
                                        }
                                        else if(o.isWerewolfCrew()){
                                                g.drawImage(imgWerewolf, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
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
