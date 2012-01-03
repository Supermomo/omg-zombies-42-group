package zombiegame.engine;

import javax.swing.JFrame;

public class FieldFrame extends JFrame{

        private static final long serialVersionUID = 8523670543879106864L;
        
        private Field field;
        
        private FieldPanel fieldPan;
        
        public FieldFrame(Field fielde){
                super("OMG Zombie");
                this.field=fielde;
                
                
                fieldPan=new FieldPanel(field);
                
                this.add(fieldPan);
                fieldPan.setVisible(true);
                
                this.setBackground(java.awt.Color.LIGHT_GRAY);
                this.setLocation(200, 100);
                this.setSize(800,800);
                this.setVisible(true);
                
        }
        
        public FieldPanel getPanel(){
                return this.fieldPan;
        }
}
