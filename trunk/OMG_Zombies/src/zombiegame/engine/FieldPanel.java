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

import zombiegame.people.*;
import zombiegame.people.Character;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class FieldPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private Field field;
        private Field fieldObject;
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
        
        public FieldPanel(FieldFrame ff, Field fiel, Field objField) {
                super();
                fieldObject = objField;
                frame = ff;
                this.setBackground(java.awt.Color.GREEN);
                this.field = fiel;
                
                hpBar=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
                hpBar.setValue(frame.getPlayer().getHealthPoints());
                hpBar.setString("HP : "+hpBar.getValue());
                hpBar.setStringPainted(true);
                hpBar.setOpaque(false);
                hpBar.setForeground(java.awt.Color.red);
                this.setLayout(new GridLayout(field.getDepth()+10,1));
                this.add(hpBar,0);

                try {
                        imgBloodTitle = ImageIO.read(new File("src//img/blood_title.png"));
                        imgChest = ImageIO.read(new File("src//img/chest.png"));
                        imgMap = ImageIO.read(new File("src//img/map_ice2.png"));
                        imgHuman = ImageIO.read(new File("src//img/Human.png"));
                        imgHumanShotgun = ImageIO.read(new File("src//img/HumanShotgun.png"));
                        imgHumanFlameThrower = ImageIO.read(new File("src//img/HumanFlamThrower.png"));
                        imgHumanStick = ImageIO.read(new File("src//img/HumanWoodenStick.png"));
                        imgZombie = ImageIO.read(new File("src//img/Zombie.png"));
                        imgMadZombie = ImageIO.read(new File("src//img/MadZombie.png"));
                        imgWerewolf = ImageIO.read(new File("src//img/Werewolf2.png"));
                        imgWerewolfCrew = ImageIO.read(new File("src//img/WerewolfCrew.png"));
                        imgVampire = ImageIO.read(new File("src//img/Vamp.png"));
                        imgHighLight = ImageIO.read(new File("src//img/highLight.png"));
                        imgDoor = ImageIO.read(new File("src//img/door.png"));
                        imgOver=ImageIO.read(new File("src//img/mouseOver.png"));
                        imgVictory=ImageIO.read(new File("src//img/victory.png"));
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);
                
                if(frame.getPlayer().getHealthPoints()>hpBar.getMaximum()){
                        hpBar.setMaximum(frame.getPlayer().getHealthPoints());
                }
                hpBar.setValue(frame.getPlayer().getHealthPoints());
                hpBar.setString("HP : "+hpBar.getValue());
                
                if (!frame.getGameOver() && !frame.getSucess()) {
                        g.drawImage(imgMap, widthBox, heightBox, field.getWidth() * widthBox, field.getDepth() * heightBox, null);

                        for (int i = 0; i < field.getDepth(); i++) {

                                // dessin ligne
                                g.drawLine(widthBox, (i + 1) * heightBox, (field.getWidth() + 1) * widthBox, (i + 1) * heightBox);
                                for (int j = 0; j < field.getWidth(); j++) {

                                        // dessin colonne

                                        if (field.getOut().getRow() == i && field.getOut().getCol() == j) {
                                                g.drawImage(imgDoor, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                        }

                                        g.drawLine((j + 1) * widthBox, heightBox, (j + 1) * widthBox, (field.getDepth() + 1) * heightBox);
                                        if (field.getObjectAt(i, j) != null) {
                                                Character o = (Character) field.getObjectAt(i, j);
                                                if (o.isPlayer()) {
                                                        g.drawImage(imgHighLight, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                        Player p = (Player) o;
                                                        
                                                        if(p.isVampire()){
                                                                if (p.isArmed()) {
                                                                        if (p.getWeapon().isShotgun()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerShotgunVamp(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        } else if (p.getWeapon().isLiquidNitrogen()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerFlamVamp(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        } else if (p.getWeapon().isWoodenStick()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerStickVamp(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        }
                                                                } else {
                                                                        g.drawImage(frame.getPlayer().getImgPlayerVamp(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox,
                                                                                        null);
                                                                }
                                                        }
                                                        else if(p.isWerewolf()){
                                                                if (p.isArmed()) {
                                                                        if (p.getWeapon().isShotgun()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerShotgunWer(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        } else if (p.getWeapon().isLiquidNitrogen()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerFlamWer(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        } else if (p.getWeapon().isWoodenStick()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerStickWer(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        }
                                                                } else {
                                                                        g.drawImage(frame.getPlayer().getImgPlayerWer(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox,
                                                                                        null);
                                                                }
                                                        }
                                                        else {
                                                                if (p.isArmed()) {
                                                                        if (p.getWeapon().isShotgun()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerShotgun(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        } else if (p.getWeapon().isLiquidNitrogen()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerFlam(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        } else if (p.getWeapon().isWoodenStick()) {
                                                                                g.drawImage(frame.getPlayer().getImgPlayerStick(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                                                                heightBox, null);
                                                                        }
                                                                } else {
                                                                        g.drawImage(frame.getPlayer().getImagePlayer(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox,
                                                                                        null);
                                                                }   
                                                        }
                                                        
                                                } else if (o.isHuman()) {
                                                        Human h = (Human) o;
                                                        if (h.isArmed()) {
                                                                if (h.getWeapon().isLiquidNitrogen()) {
                                                                        g.drawImage(imgHumanFlameThrower, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                                } else if (h.getWeapon().isShotgun()) {
                                                                        g.drawImage(imgHumanShotgun, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                                } else if (h.getWeapon().isWoodenStick()) {
                                                                        g.drawImage(imgHumanStick, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                                }

                                                        } else {
                                                                g.drawImage(imgHuman, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                        }
                                                } else if (o.isWerewolfCrew()) {
                                                        g.drawImage(imgWerewolfCrew, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                } else if (o.isWerewolf()) {
                                                        g.drawImage(imgWerewolf, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                } else if (o.isVampire()) {
                                                        g.drawImage(imgVampire, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                } else if (o.isZombie()) {
                                                        if (((Zombie) o).isMadZombie()) {
                                                                g.drawImage(imgMadZombie, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                        } else {
                                                                g.drawImage(imgZombie, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                        }
                                                }

                                        } else {
                                                /*
                                                 * g.setColor(java.awt.Color.gray
                                                 * ); g.fillRect((j + 1) *
                                                 * (widthBox), (i + 1) *
                                                 * heightBox, widthBox,
                                                 * heightBox);
                                                 */
                                        }

                                        if (fieldObject.getObjectAt(new Location(i, j)) != null) {
                                                g.drawImage(imgChest, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                        }
                                        
                                        if(frame.getMouseOver()!=null && frame.getMouseOver().x==j && frame.getMouseOver().y==i 
                                                        && !(j==frame.getPlayer().getLocation().getCol() && i==frame.getPlayer().getLocation().getRow())){
                                                g.drawImage(imgOver, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                        }

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
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);
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
