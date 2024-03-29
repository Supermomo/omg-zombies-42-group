package zombiegame.engine;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import zombiegame.objects.Item;
import zombiegame.people.*;
import zombiegame.people.Character;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class FieldPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private Field field;
        private FieldFrame frame;

        private Image imgMap;
        private Image imgHuman;
        private Image imgHumanShotgun;
        private Image imgHumanFlameThrower;
        private Image imgHumanStick;
        private Image imgZombie;
        private Image imgMadZombie;
        private Image imgWerewolf;
        private Image imgWerewolfCrew;
        private Image imgVampire;
        private Image imgHighLight;
        private Image imgChest;
        private Image imgBloodTitle;
        private Image imgDoor;
        private Image imgOver;
        private Image imgVictory;
        
        private JProgressBar hpBar;
        
        public FieldPanel(FieldFrame ff, Field fiel) {
                super();
                frame = ff;
                this.setBackground(java.awt.Color.GREEN);
                this.field = fiel;
                
                hpBar=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
                hpBar.setValue(frame.getPlayer().getHealthPoints());
                hpBar.setString("HP : "+hpBar.getValue());
                hpBar.setStringPainted(true);
                hpBar.setOpaque(false);
                hpBar.setForeground(java.awt.Color.red);
                this.setLayout(new GridLayout(40,1));
                this.add(hpBar,0);

                try {
                        imgBloodTitle = ImageIO.read(getClass().getResourceAsStream("/img/blood_title.png"));
                        imgChest = ImageIO.read(getClass().getResourceAsStream("/img/chest.png"));
                        imgMap = ImageIO.read(getClass().getResourceAsStream("/img/map_ice2.png"));
                        imgHuman = ImageIO.read(getClass().getResourceAsStream("/img/Human.png"));
                        imgHumanShotgun = ImageIO.read(getClass().getResourceAsStream("/img/HumanShotgun.png"));
                        imgHumanFlameThrower = ImageIO.read(getClass().getResourceAsStream("/img/HumanFlamThrower.png"));
                        imgHumanStick = ImageIO.read(getClass().getResourceAsStream("/img/HumanWoodenStick.png"));
                        imgZombie = ImageIO.read(getClass().getResourceAsStream("/img/Zombie.png"));
                        imgMadZombie = ImageIO.read(getClass().getResourceAsStream("/img/MadZombie.png"));
                        imgWerewolf = ImageIO.read(getClass().getResourceAsStream("/img/Werewolf2.png"));
                        imgWerewolfCrew = ImageIO.read(getClass().getResourceAsStream("/img/WerewolfCrew.png"));
                        imgVampire = ImageIO.read(getClass().getResourceAsStream("/img/Vamp.png"));
                        imgHighLight = ImageIO.read(getClass().getResourceAsStream("/img/highLight.png"));
                        imgDoor = ImageIO.read(getClass().getResourceAsStream("/img/door.png"));
                        imgOver=ImageIO.read(getClass().getResourceAsStream("/img/mouseOver.png"));
                        imgVictory=ImageIO.read(getClass().getResourceAsStream("/img/victory.png"));
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int heightBox =  10;
                int widthBox =  10;
                int xoffset=10;
                int yoffset=60;
                
                Image img = null;
                
                if(frame.getPlayer().getHealthPoints()>hpBar.getMaximum()){
                        hpBar.setMaximum(frame.getPlayer().getHealthPoints());
                }
                hpBar.setValue(frame.getPlayer().getHealthPoints());
                hpBar.setString("HP : "+hpBar.getValue());
                
                if (!frame.getGameOver() && !frame.getSucess()) {
                        g.drawImage(imgMap, xoffset, yoffset, field.getWidth(), field.getDepth(), null);
                        
                        g.drawImage(imgDoor, xoffset+field.getOut().getCol()-widthBox , yoffset+field.getOut().getRow()-heightBox , 2*widthBox, 2*heightBox, null);
                        
                        for(Character ch :field.getListChar()){
                                
                                int i=ch.getLocation().getRow();
                                int j=ch.getLocation().getCol();              
                               
                                if (field.getCharacterAt(i, j) != null) {
                                        Character o = (Character) field.getCharacterAt(i, j);
                                        if (o.isPlayer()) {
                                                g.drawImage(imgHighLight,xoffset+j-widthBox , yoffset+i-heightBox , 2*widthBox, 2*heightBox, null);
                                                Player p = (Player) o;
                                                
                                                if(p.isVampire()){
                                                        if (p.isArmed()) {
                                                                if (p.getWeapon().isShotgun()) {
                                                                        img=frame.getPlayer().getImgPlayerShotgunVamp();
                                                                } else if (p.getWeapon().isLiquidNitrogen()) {
                                                                        img=frame.getPlayer().getImgPlayerFlamVamp();
                                                                } else if (p.getWeapon().isWoodenStick()) {
                                                                        img=frame.getPlayer().getImgPlayerStickVamp();
                                                                }
                                                        } else {
                                                                img=frame.getPlayer().getImgPlayerVamp();
                                                        }
                                                }
                                                else if(p.isWerewolf()){
                                                        if (p.isArmed()) {
                                                                if (p.getWeapon().isShotgun()) {
                                                                        img=frame.getPlayer().getImgPlayerShotgunWer();
                                                                } else if (p.getWeapon().isLiquidNitrogen()) {
                                                                       img=frame.getPlayer().getImgPlayerFlamWer();
                                                                } else if (p.getWeapon().isWoodenStick()) {
                                                                        img=frame.getPlayer().getImgPlayerStickWer();
                                                                }
                                                        } else {
                                                                img=frame.getPlayer().getImgPlayerWer();
                                                        }
                                                }
                                                else {
                                                        if (p.isArmed()) {
                                                                if (p.getWeapon().isShotgun()) {
                                                                        img=frame.getPlayer().getImgPlayerShotgun();
                                                                } else if (p.getWeapon().isLiquidNitrogen()) {
                                                                        img=frame.getPlayer().getImgPlayerFlam();
                                                                } else if (p.getWeapon().isWoodenStick()) {
                                                                        img=frame.getPlayer().getImgPlayerStick();
                                                                }
                                                        } else {
                                                                img=frame.getPlayer().getImagePlayer();
                                                        }   
                                                }
                                                
                                        } else if (o.isHuman()) {
                                                Human h = (Human) o;
                                                if (h.isArmed()) {
                                                        if (h.getWeapon().isLiquidNitrogen()) {
                                                               img=imgHumanFlameThrower;
                                                        } else if (h.getWeapon().isShotgun()) {
                                                               img=imgHumanShotgun;
                                                        } else if (h.getWeapon().isWoodenStick()) {
                                                                img=imgHumanStick;
                                                        }
                
                                                } else {
                                                        img=imgHuman;
                                                }
                                        } else if (o.isWerewolfCrew()) {
                                                img=imgWerewolfCrew;
                                        } else if (o.isWerewolf()) {
                                                img=imgWerewolf;
                                        } else if (o.isVampire()) {
                                                img=imgVampire;
                                        } else if (o.isZombie()) {
                                                if (((Zombie) o).isMadZombie()) {
                                                        img=imgMadZombie;
                                                } else {
                                                       img=imgZombie;
                                                }
                                        }
                                        g.drawImage(img,xoffset+j-widthBox , yoffset+i-heightBox , 2*widthBox, 2*heightBox, null);
                                } else {
                                        /*
                                         * g.setColor(java.awt.Color.gray
                                         * ); g.fillRect((j + 1) *
                                         * (widthBox), (i + 1) *
                                         * heightBox, widthBox,
                                         * heightBox);
                                         */
                                }
                                
                                if(frame.getMouseOver()!=null && frame.getMouseOver().x==j && frame.getMouseOver().y==i 
                                                && !(j==frame.getPlayer().getLocation().getCol() && i==frame.getPlayer().getLocation().getRow())){
                                        g.drawImage(imgOver,j-xoffset , i-yoffset , 2*widthBox, 2*heightBox, null);
                                }

                        }
                        
                        for(Item it:field.getListItem()){
                                int i=it.getLocation().getRow();
                                int j=it.getLocation().getCol();
                                
                                if (field.getCharactertAt(new Location(i, j)) != null) {
                                        g.drawImage(imgChest,xoffset+j-widthBox , yoffset+i-heightBox , 2*widthBox, 2*heightBox, null);
                                }
                        }
        

                        
                        g.drawString("Human : " + field.getNbHuman() + " Vampire : " + field.getNbVampire() + " Zombie " + field.getNbZombie() + " Werewolf "
                                        + field.getNbWerewolf(), widthBox, this.getHeight() - (heightBox / 2));
                }
                else if(frame.getSucess()){
                        g.drawImage(imgVictory, 0, 0, this.getWidth(), this.getHeight(), null);
                }
                else {
                        g.drawImage(imgBloodTitle, 0, 0, this.getWidth(), this.getHeight(), null);
                }

        }
        
        /**
         * Retrun the map position on the Frame coordinate
         * @param player
         * @param x
         * @param y
         * @return -1,-1 si the destination is not valid, the x,y coordinate either
         */
        public Point validDestination(Location player, int x, int y) {

                Point p = new Point(-1, -1);
                int heightBox = 10;
                int widthBox = 10;
                int i, j;
                j = (x - widthBox) / widthBox;
                i = (y - heightBox) / heightBox;
                boolean xb = false;
                boolean yb = false;

                xb = (j == player.getCol() - 1 || j == player.getCol() || j == player.getCol() + 1) && j >= 0 && j < field.getWidth() && x >= widthBox;
                yb = (i == player.getRow() - 1 || i == player.getRow() || i == player.getRow() + 1) && i >= 0 && i < field.getDepth() && y >= heightBox;

                if (xb && yb && (j != player.getCol() || i != player.getRow())) {
                        p.x = j;
                        p.y = i;
                }
                return p;
        }

}
