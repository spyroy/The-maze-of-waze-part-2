package gameClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import gameClient.Robot;
import gui.Graph_Gui;
//import javafx.scene.text.Font;
import utils.Point3D;
import utils.StdDraw;

/**
 * this class represents the main window on the game
 * it has 
 * @author spyro
 *
 */
public class MyGameGui extends JFrame implements ActionListener, MouseListener {

	private graph graph;
	private Graph_Algo gr;
	private ArrayList<Fruit> fruits;
	private ArrayList<Robot> robots;
	private static final FruitComperator comp = new FruitComperator();
	static int Udst = -1;
	public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1, 2);
	private Graph_Algo algoGraph;
	final int NODE_WIDTH_HEIGHT = 10;
	private game_service game;
	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 1000;
	static int udst = -1;
	private boolean mode;

	/*
	 * Default constructor
	 */
	public MyGameGui() {
		this.graph = new DGraph();
		gr = new Graph_Algo();
		initGui(graph);
	}

	private void initGui(graph g) {
		this.graph = g;
		this.setSize(1000, 730);
		this.setTitle("The MAZE Of WAZE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("navigate.jpg");
		this.setIconImage(img.getImage());

		JButton manual = new JButton("Manual");
		JButton automatic = new JButton("Automatic");
		manual.addActionListener(this);
		automatic.addActionListener(this);
		this.add(manual);
		this.add(automatic);
		this.setLayout(new FlowLayout());
		this.setVisible(true);

		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);

		Menu file = new Menu("instructions");
		menuBar.add(file);

		Menu alg = new Menu("level info");
		menuBar.add(alg);
		
		Menu score = new Menu("HighScore");
		menuBar.add(score);

		MenuItem item2 = new MenuItem("manual");
		item2.addActionListener(this);
		file.add(item2);

		MenuItem item3 = new MenuItem("automatic");
		item3.addActionListener(this);
		file.add(item3);
		
		MenuItem item29 = new MenuItem("personal data");
		item29.addActionListener(this);
		score.add(item29);
		
		MenuItem item28 = new MenuItem("global data");
		item28.addActionListener(this);
		score.add(item28);

		MenuItem item5 = new MenuItem("level 1");
		item5.addActionListener(this);
		alg.add(item5);

		MenuItem item1 = new MenuItem("level 2");
		item1.addActionListener(this);
		alg.add(item1);

		MenuItem item6 = new MenuItem("level 3");
		item6.addActionListener(this);
		alg.add(item6);

		MenuItem item7 = new MenuItem("level 4");
		item7.addActionListener(this);
		alg.add(item7);

		MenuItem item8 = new MenuItem("level 5");
		item8.addActionListener(this);
		alg.add(item8);

		MenuItem item9 = new MenuItem("level 6");
		item9.addActionListener(this);
		alg.add(item9);

		MenuItem item10 = new MenuItem("level 7");
		item10.addActionListener(this);
		alg.add(item10);

		MenuItem item11 = new MenuItem("level 8");
		item11.addActionListener(this);
		alg.add(item11);

		MenuItem item12 = new MenuItem("level 9");
		item12.addActionListener(this);
		alg.add(item12);

		MenuItem item13 = new MenuItem("level 10");
		item13.addActionListener(this);
		alg.add(item13);

		MenuItem item14 = new MenuItem("level 11");
		item14.addActionListener(this);
		alg.add(item14);

		MenuItem item15 = new MenuItem("level 12");
		item15.addActionListener(this);
		alg.add(item15);

		MenuItem item16 = new MenuItem("level 13");
		item16.addActionListener(this);
		alg.add(item16);

		MenuItem item17 = new MenuItem("level 14");
		item17.addActionListener(this);
		alg.add(item17);

		MenuItem item18 = new MenuItem("level 15");
		item18.addActionListener(this);
		alg.add(item18);

		MenuItem item19 = new MenuItem("level 16");
		item19.addActionListener(this);
		alg.add(item19);

		MenuItem item20 = new MenuItem("level 17");
		item20.addActionListener(this);
		alg.add(item20);

		MenuItem item21 = new MenuItem("level 18");
		item21.addActionListener(this);
		alg.add(item21);

		MenuItem item22 = new MenuItem("level 19");
		item22.addActionListener(this);
		alg.add(item22);

		MenuItem item23 = new MenuItem("level 20");
		item23.addActionListener(this);
		alg.add(item23);

		MenuItem item24 = new MenuItem("level 21");
		item24.addActionListener(this);
		alg.add(item24);

		MenuItem item25 = new MenuItem("level 22");
		item25.addActionListener(this);
		alg.add(item25);

		MenuItem item26 = new MenuItem("level 23");
		item26.addActionListener(this);
		alg.add(item26);

		MenuItem item27 = new MenuItem("level 24");
		item27.addActionListener(this);
		alg.add(item27);

		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String str = JOptionPane.showInputDialog(this,
				"Please insert node number to move, " + "\n" + "only neighbors nodes are allowed: ");
		try {
			int dst = Integer.parseInt(str);
			Udst = dst;
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(this, "Invalid input", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent Command) {
		String str = Command.getActionCommand();

		switch (str) {

		case "manual":
			JOptionPane.showMessageDialog(this,
					"in this mode you will have to choose where to place the robots \n and then manually move them to fruits \n to get the hiest score as posible.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "automatic":
			JOptionPane.showMessageDialog(this,
					"in this mode the robots will move automatically to the fruits \n by the algorithm written to them \n they will try to do the best score that posibble \n you dont have to do anything!",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 1":
			JOptionPane.showMessageDialog(this, "this stage have one robot and one fruit and the map is A0.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 2":
			JOptionPane.showMessageDialog(this, "this stage have one robot and two fruits and the map is A0.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 3":
			JOptionPane.showMessageDialog(this, "this stage have one robot and three fruits and the map is A0.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 4":
			JOptionPane.showMessageDialog(this, "this stage have one robot and four fruits and the map is A0.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 5":
			JOptionPane.showMessageDialog(this, "this stage have one robot and five fruits and the map is A1.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 6":
			JOptionPane.showMessageDialog(this, "this stage have one robot and six fruits and the map is A1.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 7":
			JOptionPane.showMessageDialog(this, "this stage have one robot and one fruit and the map is A1.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 8":
			JOptionPane.showMessageDialog(this, "this stage have one robot and two fruits and the map is A1.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 9":
			JOptionPane.showMessageDialog(this, "this stage have one robot and three fruits and the map is A2.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 10":
			JOptionPane.showMessageDialog(this, "this stage have one robot and four fruits and the map is A2.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 11":
			JOptionPane.showMessageDialog(this, "this stage have one robot and five fruits and the map is A2.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 12":
			JOptionPane.showMessageDialog(this, "this stage have three robots and six fruit and the map is A2.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 13":
			JOptionPane.showMessageDialog(this, "this stage have one robot and one fruit and the map is A3.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 14":
			JOptionPane.showMessageDialog(this, "this stage have two robots and two fruits and the map is A3.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 15":
			JOptionPane.showMessageDialog(this, "this stage have three robots and three fruits and the map is A3.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 16":
			JOptionPane.showMessageDialog(this, "this stage have one robot and three fruits and the map is A3.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 17":
			JOptionPane.showMessageDialog(this, "this stage have two robots and five fruits and the map is A4.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 18":
			JOptionPane.showMessageDialog(this, "this stage have three robots and six fruits and the map is A4.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 19":
			JOptionPane.showMessageDialog(this, "this stage have one robot and one fruit and the map is A4.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 20":
			JOptionPane.showMessageDialog(this, "this stage have two robots and two fruits and the map is A4.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 21":
			JOptionPane.showMessageDialog(this, "this stage have three robots and three fruits and the map is A5.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 22":
			JOptionPane.showMessageDialog(this, "this stage have one robot and four fruits and the map is A5.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 23":
			JOptionPane.showMessageDialog(this, "this stage have two robots and five fruits and the map is A5.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "level 24":
			JOptionPane.showMessageDialog(this, "this stage have three robots and six fruits and the map is A5.",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "Manual":
			String id=JOptionPane.showInputDialog(this,"Enter your id:","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
			String d = JOptionPane.showInputDialog(this, "enter level 1-24:", "INFORMATION",
					JOptionPane.INFORMATION_MESSAGE);
			// MyGameGui mg = new MyGameGui(Integer.parseInt(d + 1));
			try {
				this.setVisible(false);
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							MyGame game = new MyGame(Integer.parseInt(d) - 1,Integer.parseInt(id));
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				});
				t.start();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "Automatic":
			String id2=JOptionPane.showInputDialog(this,"Enter your id:","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
			String m = JOptionPane.showInputDialog(this, "enter level 1-24:", "INFORMATION",
					JOptionPane.INFORMATION_MESSAGE);
			// MyGameGui mg = new MyGameGui(Integer.parseInt(d + 1));
			

			try {
				this.setVisible(false);
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							MyGameAuto gamer = new MyGameAuto(Integer.parseInt(m) - 1,Integer.parseInt(id2));
							gamer.repaint();
							gamer.update(gamer.getGraphics());
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				});
				t.start();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "personal data":
			String r = JOptionPane.showInputDialog(this, "enter id", "INFORMATION",
					JOptionPane.INFORMATION_MESSAGE);
			personal(Integer.parseInt(r));
			break;
		case "global data":
			String l = JOptionPane.showInputDialog(this, "enter stage", "INFORMATION",
					JOptionPane.INFORMATION_MESSAGE);
			highscore(Integer.parseInt(l));

		}

	}
	
    private void highscore(int stage) {
        String[] Names = { "UserID", "LevelID", "score", "moves", "time" };
        JFrame frame = new JFrame("Stage "+stage);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel();
        for (String Name : Names) {
            tableModel.addColumn(Name);
        }
        TreeMap<String, String> tp = MyDB.globalBest(stage);
        for (Map.Entry<String, String> entry : tp.entrySet()) {
            tableModel.addRow(entry.getValue().split(","));
        }

        JTable table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scroll);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    
    private void personal(int id) {
        String[] Names = { "UserID", "LevelID", "score", "moves", "time" };
        JFrame frame = new JFrame("Games Played: " + MyDB.NumOfGames(id));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel();
        for (String Name : Names) {
            tableModel.addColumn(Name);
        }
        TreeMap<Integer, String> tp = MyDB.personalBest(id);
        for (Map.Entry<Integer, String> entry : tp.entrySet()) {
            tableModel.addRow(entry.getValue().split(","));
        }

        JTable table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scroll);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

	
	

	public static void main(String[] args) {
		graph g = new DGraph();
		MyGameGui a = new MyGameGui();
		a.setVisible(true);

	}

}