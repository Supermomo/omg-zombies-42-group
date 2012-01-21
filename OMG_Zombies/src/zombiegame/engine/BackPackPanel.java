package zombiegame.engine;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import zombiegame.objects.Item;
import zombiegame.objects.Wearable;

public class BackPackPanel extends JPanel implements ActionListener {

        private static final long serialVersionUID = 1822112021548305065L;
        private FieldFrame frame;
        private List<JButton> listButton;

        public BackPackPanel(FieldFrame fram) {
                setBackground(java.awt.Color.blue);
                frame = fram;
                listButton = new ArrayList<JButton>();
        }

        @Override
        public void paintComponent(Graphics g) {
                super.paintComponent(g);

                Wearable equipped = frame.getPlayer().getBackPack().getEquiped();
                List<Wearable> list = frame.getPlayer().getBackPack().getItemList();
                for (JButton b : listButton) {
                        this.remove(b);
                }
                listButton.clear();
                this.validate();

                this.setLayout(new GridLayout(list.size() + 2, 1));

                for (int i = 0; i < list.size(); i++) {                       
                        JButton temp = new JButton(((Item) list.get(i)).getType());
                        temp.addActionListener(this);
                        listButton.add(temp);
                        this.add(temp, i);
                        if (list.get(i) == equipped || (frame.getPlayer().getWeapon() != null && ((Item) list.get(i)).getType().equals(frame.getPlayer().getWeapon().getType()))) {
                                temp.setEnabled(false);
                        }
                }
                if (frame.getPlayer().getEdible() != null) {
                        JButton temp = new JButton("Edible : " + frame.getPlayer().getEdible().getType());
                        temp.setEnabled(false);
                        listButton.add(temp);
                        this.add(temp, list.size());
                } else {
                        JButton temp = new JButton("Edible : Empty");
                        temp.setEnabled(false);
                        listButton.add(temp);
                        this.add(temp, list.size());
                }

                if (frame.getPlayer().getItem() != null) {
                        JButton temp = new JButton("Miscellaneous : " + frame.getPlayer().getItem().getType());
                        temp.setEnabled(false);
                        listButton.add(temp);
                        this.add(temp, list.size() + 1);
                } else {
                        JButton temp = new JButton("Miscellaneous : Empty");
                        temp.setEnabled(false);
                        listButton.add(temp);
                        this.add(temp, list.size() + 1);
                }
                this.validate();
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {

                try {
                        Item it = Item.getItem(arg0.getActionCommand());
                        frame.getPlayer().getBackPack().equip((Wearable) it, frame.getPlayer());
                } catch (Exception e) {
                        System.out.println("Impossible to get the item for the string : null");
                        e.printStackTrace();
                }
                frame.repaint();
        }
}
