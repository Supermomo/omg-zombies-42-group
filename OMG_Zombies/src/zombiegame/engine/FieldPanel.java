package zombiegame.engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import zombiegame.people.*;
import zombiegame.people.Character;

import javax.swing.JPanel;

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

        public FieldPanel(FieldFrame ff, Field fiel, Field objField) {
                super();
                fieldObject=objField;
                frame = ff;
                this.setBackground(java.awt.Color.GRAY);
                this.field = fiel;
             
                Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
                imgBloodTitle=tk.getImage(this.getClass().getResource("/img/blood_title.png"));
                imgChest=tk.getImage(this.getClass().getResource("/img/chest.png"));
                imgMap = tk.getImage(this.getClass().getResource("/img/map_ice2.png"));
                imgHuman = tk.getImage(this.getClass().getResource("/img/Human.png"));
                imgHumanShotgun = tk.getImage(this.getClass().getResource("/img/HumanShotgun.png"));
                imgHumanFlameThrower = tk.getImage(this.getClass().getResource("/img/HumanFlamThrower.png"));
                imgHumanStick = tk.getImage(this.getClass().getResource("/img/HumanWoodenStick.png"));
                imgZombie = tk.getImage(this.getClass().getResource("/img/Zombie.png"));
                imgMadZombie = tk.getImage(this.getClass().getResource("/img/MadZombie.png"));
                imgWerewolf = tk.getImage(this.getClass().getResource("/img/Werewolf2.png"));
                imgWerewolfCrew = tk.getImage(this.getClass().getResource("/img/WerewolfCrew.png"));
                imgVampire = tk.getImage(this.getClass().getResource("/img/Vamp.png"));
                imgHighLight= tk.getImage(this.getClass().getResource("/img/highLight.png"));

        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);

                if (!frame.getGameOver()) {
                        g.drawImage(imgMap, widthBox, heightBox, field.getWidth() * widthBox, field.getDepth() * heightBox, null);

                        for (int i = 0; i < field.getDepth(); i++) {
                                // dessin ligne
                                g.drawLine(widthBox, (i + 1) * heightBox, (field.getWidth() + 1) * widthBox, (i + 1) * heightBox);
                                for (int j = 0; j < field.getWidth(); j++) {
                                        // dessin colonne
                                        g.drawLine((j + 1) * widthBox, heightBox, (j + 1) * widthBox, (field.getDepth() + 1) * heightBox);
                                        if (field.getObjectAt(i, j) != null) {
                                                Character o = (Character) field.getObjectAt(i, j);
                                                if(o.isPlayer()){
                                                        g.drawImage(imgHighLight, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                        Player p=(Player)o;
                                                        if(p.isArmed()){
                                                                if(p.getWeapon().isShotgun()){
                                                                        g.drawImage(frame.getPlayer().getImgPlayerShotgun(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                                }
                                                                else if(p.getWeapon().isLiquidNitrogen()){
                                                                        g.drawImage(frame.getPlayer().getImgPlayerFlam(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                                }
                                                                else if(p.getWeapon().isWoodenStick()){
                                                                        g.drawImage(frame.getPlayer().getImgPlayerStick(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                                }
                                                        }
                                                        else {
                                                                g.drawImage(frame.getPlayer().getImagePlayer(), (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                                        }
                                                }
                                                else if (o.isHuman()) {
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
                                        
                                        if(fieldObject.getObjectAt(new Location(i,j))!=null){
                                                g.drawImage(imgChest, (j + 1) * (widthBox), (i + 1) * heightBox, widthBox, heightBox, null);
                                        }
                                }
                        }

                        g.drawString("Human : " + field.getNbHuman() + " Vampire : " + field.getNbVampire() + " Zombie " + field.getNbZombie() + " Werewolf "
                                        + field.getNbWerewolf(), widthBox, this.getHeight() - (heightBox / 2));
                }
                else {
                        g.drawImage(imgBloodTitle, 0, 0, this.getWidth(), this.getHeight(), null);
                }

        }

        public Point validDestination(Location player, int x, int y) {

                Point p = new Point(-1, -1);
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);
                int i, j;
                j = (x - widthBox) / widthBox;
                i = (y - heightBox) / heightBox;
                boolean xb = false;
                boolean yb = false;

                xb = (j == player.getCol() - 1 || j == player.getCol() || j == player.getCol() + 1 || j == player.getCol() + 2) && j >= 0 && j < field.getWidth() && x >= widthBox;
                yb = (i == player.getRow() - 1 || i == player.getRow() || i == player.getRow() + 1 || i == player.getRow() + 2) && i >= 0 && i < field.getDepth() && y >= heightBox;

                System.out.println("i : " + i + " j : " + j + " y " + y + " x " + x + " result : " + (xb && yb && !(i != player.getCol() && j != player.getRow())));
                System.out.println("xb " + xb);
                System.out.println("yb " + yb);

                if (xb && yb && (j != player.getCol() || i != player.getRow())) {
                        p.x = j;
                        p.y = i;
                }
                return p;
        }





}
