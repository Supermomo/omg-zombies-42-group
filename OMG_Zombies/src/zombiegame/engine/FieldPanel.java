package zombiegame.engine;

import java.awt.Graphics;

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
                System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTEEEEEEEEEEEEEE");
                int heightBox = this.getHeight() / (field.getDepth() + 2);
                int widthBox = this.getWidth() / (field.getWidth() + 2);

                for (int i = 0; i < field.getDepth(); i++) {
                        for (int j = 0; j < field.getWidth(); j++) {
                                if (field.getObjectAt(i, j) != null) {
                                        g.setColor(java.awt.Color.blue);

                                } else {
                                        g.setColor(java.awt.Color.gray);
                                }
                                g.fillRect((j + 1) * (widthBox), (i + 1) * heightBox, widthBox,
                                                heightBox);

                        }
                }

        }

}
