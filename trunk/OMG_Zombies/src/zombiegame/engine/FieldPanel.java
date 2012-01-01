package zombiegame.engine;

import java.awt.Graphics;

import javax.swing.JPanel;

public class FieldPanel extends JPanel{

        private static final long serialVersionUID = 1L;

        private Field field;
        
        public FieldPanel(Field fiel){
                super();
                this.field=fiel;
                
        }
        
        public void paintComponents(Graphics g){
                
                int heightBox=this.getHeight()/(field.getDepth()+2);
                int widthBox=this.getWidth()/(field.getWidth()+2);
                
                for(int i=0;i<field.getDepth();i++){
                        for(int j=0;j<field.getWidth();j++){
                                g.setColor(java.awt.Color.blue);
                               g.fillRect((i+1)*(widthBox), (i+1)*heightBox, widthBox , heightBox);
                        }
                }
                
                super.paintComponents(g);
        }
}
