package zombiegame.engine;


import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import zombiegame.people.Character;
import zombiegame.people.Helico;
import zombiegame.people.Human;
import zombiegame.people.Nom;
import zombiegame.people.Player;
import zombiegame.people.Vampire;
import zombiegame.people.Werewolf;
import zombiegame.people.Zombie;


public class FieldFrame extends JFrame implements ActionListener,ItemListener, MouseListener{

        private static final long serialVersionUID = 8523670543879106864L;

        public static final int HP_HUMANS = 100;
        public static final int HP_VAMPIRES = 150;
        public static final int HP_ZOMBIES = 30;
        public static final int HP_WEREWOLF = 150;
        public static int SIZE_MAP = 15;
        public static int NB_HUMANS = 0;
        public static int NB_VAMPIRES = 0;
        public static int NB_ZOMBIES = 0;
        public static int NB_WEREWOLF = 0;

        // List of characters currently in the game
        private Field field;
        private Field fieldObject;

        private FieldPanel fieldPan;
        private JScrollPane jsp;
        private JTextArea cons;
        
        private JRadioButtonMenuItem dif1;
        private JRadioButtonMenuItem dif2;
        private JRadioButtonMenuItem dif3;
        private JCheckBoxMenuItem consoleDisp;
        private boolean gameOver=true;
        public boolean consoleDisplayState=true;
        
        private String playerName= "defaultPlayer";
        private Player player;
        private JMenuItem stopGame;
        
        private BackPackPanel bpPanel;
        
        public FieldFrame() {
                
                super("OMG Zombie");
                
                //Ajout des menus
                JMenuBar menu=new JMenuBar();
                JMenu run=new JMenu("Run");
                JMenuItem newGame=new JMenuItem("New Game");
                newGame.setActionCommand("new");
                stopGame=new JMenuItem("Stop Game");
                stopGame.setActionCommand("stop");
                //menus de choix de la difficulté
                JMenu diff=new JMenu("Set next game difficulty");
                ButtonGroup group=new ButtonGroup();
                dif1=new JRadioButtonMenuItem("Level 1",true);
                group.add(dif1);
                diff.add(dif1);
                dif2=new JRadioButtonMenuItem("Level 2",false);
                group.add(dif2);
                diff.add(dif2);
                dif3=new JRadioButtonMenuItem("Level 3",false);
                group.add(dif3);
                diff.add(dif3);
                //Ajout des menus Item au menu
                run.add(newGame);
                run.add(stopGame);
                run.add(diff);
                newGame.addActionListener(this);
                stopGame.addActionListener(this);
                menu.add(run);
                //Ajout du menu Window : permettant l'activation de la console
                JMenu window=new JMenu("Window");
                consoleDisp=new JCheckBoxMenuItem("Display console");
                consoleDisp.setSelected(true);
                consoleDisp.addItemListener(this);
                window.add(consoleDisp);
                menu.add(window);
                //ajout de la JMenuBar à la fenêtre
                this.setJMenuBar(menu);
                
                this.setLayout(new GridLayout(1, 3));

                
                this.setBackground(java.awt.Color.LIGHT_GRAY);
                this.setLocation(30, 30);
                this.setSize(1400, 800);
                this.setVisible(true);
                this.validate();
                this.repaint();
        }
        
        private void initField(){
                field = new Field(SIZE_MAP, SIZE_MAP, cons,new Location(0,SIZE_MAP/2),new Location(SIZE_MAP-1,SIZE_MAP/2));
                field.setNextField(new Field(SIZE_MAP, SIZE_MAP, cons,new Location(0,SIZE_MAP/2),new Location(SIZE_MAP-1,SIZE_MAP-1)));
                field.getNextField().setNextField(new Field(SIZE_MAP, SIZE_MAP, cons,new Location(0,SIZE_MAP-1),new Location(SIZE_MAP-1,0)));
                field.getNextField().getNextField().setNextField(new Field(SIZE_MAP, SIZE_MAP, cons,new Location(0,0),new Location(SIZE_MAP-1,SIZE_MAP/2)));
                fieldObject = new Field(SIZE_MAP, SIZE_MAP, cons);
                player=new Player(playerName,HP_HUMANS);
                field.place(player, field.getIn());
                
                Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
                player.setImagePlayer(tk.getImage(this.getClass().getResource("/img/Human2.png")));
                player.setImgPlayerFlam(tk.getImage(this.getClass().getResource("/img/Human2FlamThrower.png")));
                player.setImgPlayerShotgun(tk.getImage(this.getClass().getResource("/img/Human2Shotgun.png")));
                player.setImgPlayerStick(tk.getImage(this.getClass().getResource("/img/Human2WoodenStick.png")));
        }
        
        private void initPeople(){
                if(dif2.isSelected()){
                        NB_HUMANS = 3;
                        NB_VAMPIRES = 5;
                        NB_ZOMBIES = 6;
                        NB_WEREWOLF = 3;
                }
                else if(dif3.isSelected()){
                        NB_HUMANS = 2;
                        NB_VAMPIRES = 8;
                        NB_ZOMBIES = 9;
                        NB_WEREWOLF = 5;
                }
                else
                {
                        NB_HUMANS = 4;
                        NB_VAMPIRES = 2;
                        NB_ZOMBIES = 4;
                        NB_WEREWOLF = 1;
                }
                this.generatePeople(NB_VAMPIRES, NB_WEREWOLF, NB_ZOMBIES, NB_HUMANS);
                this.gameOver=false;
        }
        
        /**
         * Initialize game.
         */
        public void init() {
                initField();
                initPeople();
        }
        
        public boolean getGameOver(){
                return gameOver;
        }
        
        public void setGameOver(boolean over){
                gameOver=over;;
        }
        
        public void generatePeople(int nbVamp, int nbWerewolf, int nbZombie, int nbHuman) {

                if (nbVamp < 0 || nbWerewolf < 0 || nbZombie < 0 || nbHuman < 0) {
                        throw new RuntimeException("negative integer shouldn't be a parameter");
                }

                Random rand = new Random();
                List<Nom> noms = new LinkedList<Nom>();
                for (int i = 0; Nom.values().length > i; i++) {
                        noms.add(Nom.values()[i]);
                }

                for (int i = 0; i < nbVamp; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Vampire(noms.get(0).toString(), HP_VAMPIRES));
                }

                for (int i = 0; i < nbWerewolf; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Werewolf(noms.get(0).toString(), HP_WEREWOLF));
                }

                for (int i = 0; i < nbZombie; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Zombie(noms.get(0).toString(), HP_ZOMBIES));
                }

                for (int i = 0; i < nbHuman; i++) {
                        Collections.shuffle(noms, rand);
                        this.field.placeRandomly(new Human(noms.get(0).toString(), HP_HUMANS));
                }

        }
        
        /**
         * @return the number of human characters currently in the game
         */
        public int nbHumansAlive() {
                int nbHumans = 0;
                Character c = null;
                for (int i = 0; i < field.getDepth(); i++) {
                        for (int j = 0; j < field.getWidth(); j++) {
                                try {
                                        c = (Character) field.getObjectAt(i, j);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }

                                if (c != null) {
                                        if (c.isHuman()) {
                                                nbHumans++;
                                        }

                                        c.setPlay();
                                }

                        }
                }
                if (nbHumans == 0) {
                        field.getConsolePanel().append("\r\nAll humans have been eaten!\r\n");
                }

                return nbHumans;
        }

        /**
         * Perform all game logic for next turn.
         */
        public void nextTurn() {

                Character c = null;

                for (int i = 0; i < field.getDepth(); i++) {
                        for (int j = 0; j < field.getWidth(); j++) {

                                try {
                                        c = (Character) field.getObjectAt(i, j);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }

                                if (c != null && c.canPlay() && !c.isPlayer()) {
                                        c.action(field, fieldObject);
                                        c.justPlayed();
                                        c.endOfTurn(field);
                                        this.repaint();
                                }

                        }
                }

                Helico helic = new Helico(fieldObject);
                helic.dropItem(this,player.getLocation());

                field.getConsolePanel().append("\r\n FIN DU TOUR \r\n");
                this.repaint();
        }
        
        public FieldPanel getPanel(){
                return this.fieldPan;
        }
        
        public Field getObjectField(){
                return this.fieldObject;
        }
        
        public Player getPlayer() {
                return this.player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

                if(e.getActionCommand().equals("stop")){
                        try {
                                this.gameOver=true;
                                this.remove(jsp);
                                this.remove(fieldPan);
                                this.remove(bpPanel);
                                this.validate();
                                this.repaint();
                        } catch (Exception e1) {
                                System.out.println("No game to stop");
                        }

                }
                else if(e.getActionCommand().equals("new") && gameOver==true){
                        System.out.println("new");
                        this.gameOver=false;
                        
                        cons = new JTextArea(200, 200);
                        cons.setText("New game running\r\n");
                        cons.setEditable(false);
                        cons.setLineWrap(true);
                        cons.setForeground(java.awt.Color.green);
                        cons.setBackground(java.awt.Color.black);
                        
                        init();

                        fieldPan = new FieldPanel(this, field,fieldObject);
                        
                        fieldPan.addMouseListener(this);
                        
                        this.add(fieldPan, 0);
                        fieldPan.setVisible(true);
                        
                        bpPanel=new BackPackPanel(this);
                        this.add(bpPanel,1);
                        
                        jsp = new JScrollPane(cons);

                        /*jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                                public void adjustmentValueChanged(AdjustmentEvent e) {
                                        e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                                }
                        });*/

                        this.add(jsp, -1);
                        this.consoleDisp.setSelected(true);
                        cons.setVisible(true);
                        cons.setBounds(this.getWidth() - 200, 0, 200, getHeight());
                        fieldPan.validate();
                        fieldPan.repaint();
                }

                this.validate();
                this.repaint();
        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
        }

        @Override
        public void mouseReleased(MouseEvent e) {
                if (!this.getGameOver()) {
                        System.out.println("row "+field.getOut().getRow() + " col "+field.getOut().getCol());
                        System.out.println("x  " + this.getPlayer().getLocation().getCol() + "  y  " + this.getPlayer().getLocation().getRow());
                        Point p = fieldPan.validDestination(this.getPlayer().getLocation(), e.getX(), e.getY());
                        if (p.x != -1) {
                                this.getPlayer().move(new Location(p.y, p.x), field, this.getObjectField());
                                this.getPlayer().endOfTurn(field);
                                if(getPlayer().isArmed()){
                                        this.repaint();
                                }
                                this.repaint();
                                this.nextTurn();
                                this.nbHumansAlive();
                                this.validate();
                        }
                        if(player.getLocation().getRow()==field.getOut().getRow() && player.getLocation().getCol()==field.getOut().getCol()){
                                System.out.println("nextField");
                                if(field.getNextField()!=null){
                                        Location in=field.getNextField().getIn();
                                        field=field.getNextField();
                                        field.place(player, in);
                                        fieldObject=new Field(SIZE_MAP,SIZE_MAP,cons);
                                        NB_HUMANS += 1;
                                        NB_VAMPIRES += 1;
                                        NB_ZOMBIES += 2;
                                        NB_WEREWOLF += 1;
                                        this.generatePeople(NB_VAMPIRES, NB_WEREWOLF, NB_ZOMBIES, NB_HUMANS);  
                                        this.remove(fieldPan);
                                        fieldPan=new FieldPanel(this,field, fieldObject);
                                        this.add(fieldPan,0);
                                        fieldPan.addMouseListener(this);
                                        this.validate();
                                        this.repaint();
                                }
                                else {
                                        System.out.println("VICTORY");
                                        try {
                                                player.say("I WIN !!!!!!!!!!!", cons);
                                                this.gameOver=true;
                                                this.repaint();
                                                Thread.sleep(3000);
                                                this.remove(jsp);
                                                this.remove(fieldPan);
                                                this.remove(bpPanel);
                                                this.validate();
                                        } catch (Exception e1) {
                                                System.out.println("No game to stop");
                                        }
                                }                               
                        }
                        this.repaint();
                        if (field.getObjectAt(player.getLocation())==null || !((Character)field.getObjectAt(player.getLocation())).isPlayer() || this.getPlayer().getHealthPoints() <= 0
                                        || field.getNbHuman() == 0) {
                                System.out.println("OVER");
                                try {
                                        player.say("I'm dead...too bad", cons);
                                        this.gameOver=true;
                                        this.repaint();
                                        fieldPan.validate();
                                        fieldPan.repaint();
                                        /*Thread.sleep(3000);
                                        this.remove(jsp);
                                        this.remove(fieldPan);
                                        this.remove(bpPanel);
                                        this.validate();*/
                                } catch (Exception e1) {
                                        System.out.println("No game to stop");
                                }

                        }
                }
                this.repaint();
        }

        @Override
        public void itemStateChanged(ItemEvent arg0) {
                
                Object source=arg0.getItemSelectable();
                if(source==this.consoleDisp){
                        if(consoleDisp.isSelected() && !gameOver){
                                consoleDisplayState=true;
                                try {
                                        this.remove(fieldPan);
                                        this.remove(jsp);
                                        this.remove(bpPanel);
                                        this.setLayout(new GridLayout(1, 2));
                                        this.add(fieldPan,0);
                                        this.add(bpPanel,1);
                                        this.add(jsp,-1);
                                        this.validate();
                                } catch (Exception e) {
                                }
                        }
                        else if(!gameOver){
                                consoleDisplayState=false;
                                try {
                                        this.remove(jsp);
                                        this.remove(fieldPan);
                                        this.remove(bpPanel);
                                        this.setLayout(new GridLayout(1, 1));
                                        this.add(fieldPan,0);
                                        this.add(bpPanel,1);
                                        this.validate();
                                } catch (Exception e) {
                                }
                        }
                }
                this.repaint();
        }


}
