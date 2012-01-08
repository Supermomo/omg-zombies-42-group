package zombiegame.engine;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class FieldFrame extends JFrame{

        private static final long serialVersionUID = 8523670543879106864L;
        
        private Field field;
        
        private FieldPanel fieldPan;
        
        public FieldFrame(Field fielde, JTextArea cons){
                super("OMG Zombie");
                this.field=fielde;
                
                cons.setEditable(false);
                cons.setLineWrap(true);
                cons.setForeground(java.awt.Color.green);
                cons.setBackground(java.awt.Color.black);               
                
                fieldPan=new FieldPanel(field);
                
                this.setLayout(new GridLayout(0,2));

                this.setBackground(java.awt.Color.LIGHT_GRAY);
                this.setLocation(30, 30);
                this.setSize(1400,800);
                this.setVisible(true);
                
                this.add(fieldPan,0);
                fieldPan.setVisible(true);
                JScrollPane jsp=new JScrollPane(cons);
                
                jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
                        public void adjustmentValueChanged(AdjustmentEvent e) {  
                                e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
                            }
                        });

                this.add(jsp,-1);


                cons.setVisible(true);
                cons.setBounds(this.getWidth()-200, 0, 200, getHeight());

                
        }
        
        public FieldPanel getPanel(){
                return this.fieldPan;
        }
}
