package zombiegame.engine;

import java.awt.Graphics;
import zombiegame.people.*;
import zombiegame.people.Character;

import javax.swing.JPanel;

public class FieldPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private Field field;

        public FieldPanel(Field fiel) {
                super();
                this.setBackground(java.awt.Color.green);
                this.field = fiel;

        }

        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);

                for (int i = 0; i < field.getDepth(); i++) {
                        for (int j = 0; j < field.getWidth(); j++) {
                                if (field.getObjectAt(i, j) != null) {
                                        Character o=(Character)field.getObjectAt(i,j);
                                        if(o.isHuman()){
                                                g.setColor(java.awt.Color.blue);
                                        }
                                        else if(o.isWerewolfCrew()){
                                                g.setColor(java.awt.Color.red);
                                        }
                                        else if(o.isWerewolf()){
                                                g.setColor(java.awt.Color.pink);
                                        }
                                        else if(o.isVampire()){
                                                g.setColor(java.awt.Color.black);
                                        }
                                        else if(o.isZombie()){
                                                g.setColor(java.awt.Color.magenta);
                                        }
                                        
                                } else {
                                        g.setColor(java.awt.Color.gray);
                                }
                                g.fillRect((j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                heightBox);

                        }
                }

        }

}
