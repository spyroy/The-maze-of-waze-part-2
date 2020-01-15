package gameClient;

import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class MyGame extends JFrame implements ActionListener, MouseListener {

	public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1, 2);
	private static final FruitComperator comp = new FruitComperator();
	private graph graph;
	private Graph_Algo algoGraph;
	private ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	final int NODE_WIDTH_HEIGHT = 10;
	private game_service game;
	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 1000;
	static int user_Dst = -1;
	private boolean mode;

	public MyGame(int num_scenario) throws InterruptedException {
		game_service game = Game_Server.getServer(num_scenario);
		graph = new DGraph(game.getGraph());
		algoGraph = new Graph_Algo(graph);
		turnToGuiLocation(FRAME_WIDTH, FRAME_HEIGHT);
		for (String f : game.getFruits()) {
			Fruit ftmp = new Fruit(f);
			placeFruit(ftmp);
			fruits.add(ftmp);
		}
		int size = this.fruits.size();
		this.fruits.sort(comp);
		try {
			JSONObject robots = new JSONObject(game.toString());
			robots = robots.getJSONObject("GameServer");
			int num_robots = robots.getInt("robots");
			for (int i = 0; i < num_robots; i++) {
				if (i < this.fruits.size()) {
					int src = this.fruits.get(i).getEdge().getSrc();
					node_data node_src = this.graph.getNode(src);
					Point3D src_p = node_src.getLocation();
					game.addRobot(src);
				} else {
					game.addRobot(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String robot : game.getRobots()) {
			Robot robot_tmp = new Robot(robot);
			robots.add(robot_tmp);
		}

		JLabel time = new JLabel();
		JTextField text = new JTextField();
		text.setFont(new Font("deafult", Font.BOLD, 15));
		time.setFont(new Font("deafult", Font.BOLD, 21));
		this.setBounds(200, 0, FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("maze of waze");
		this.add(time, BorderLayout.NORTH);
		addMouseListener(this);
		this.setVisible(true);
		game.startGame();
		while (game.isRunning()) {
			time.setText("Time: " + game.timeToEnd() / 1000);
			Thread.sleep(150);
			moveRobots(game, graph);
			repaint();
		}
		String results = game.toString();
		JOptionPane.showMessageDialog(this, "Game over: " + results, "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	}

	private void turnToGuiLocation(int width, int height) {
		double[] xScale = find_min_max_axis();
		double[] yScale = find_min_max_ayis();
		for (node_data node : this.graph.getV()) {
			double x_gui = scale(node.getLocation().x(), xScale[0], xScale[1], 5, width - 50);
			double y_gui = scale(node.getLocation().y(), yScale[0], yScale[1], 70, height - 70);
			node.setGuiLocation(x_gui, y_gui);
		}

	}

	private void placeFruit(Fruit f) {
		for (node_data n : this.graph.getV()) {
			for (edge_data e : this.graph.getE(n.getKey())) {
				node_data dest = this.graph.getNode(e.getDest());
				double nd = n.getLocation().distance3D(f.getLocation());
				double nf = f.getLocation().distance3D(dest.getLocation());
				double dist = n.getLocation().distance3D(dest.getLocation());
				double tmp = dist - (nd + nf);
				int type;
				if (n.getKey() > dest.getKey()) {
					type = 1; // apple
				} else {
					type = -1; // banana
				}

				if ((Math.abs(tmp) <= EPS2) && (f.getType() == type)) {
					f.setEdge(e);
					return;
				}
			}
		}
	}

	private void moveRobots(game_service game, graph graph) { 
		List<String> path = game.move();
		if (path != null) {
			long t = game.timeToEnd();
			for (int i = 0; i < path.size(); i++) {
				String robot_json = path.get(i);
				try {
					JSONObject line = new JSONObject(robot_json);
					JSONObject rob = line.getJSONObject("Robot");
					int robid = rob.getInt("id");
					int src = rob.getInt("src");
					int dest = rob.getInt("dest");
					String pos = rob.getString("pos");
					int dst = user_Dst;

					if (dest == -1) {

						dest = nextNode(graph, src, dst);
						game.chooseNextEdge(robid, dest);
					}
					this.robots.get(robid).setLocation(new Point3D(pos));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			fruits.clear();
			for (String fruit : game.getFruits()) {
				Fruit fruit_tmp = new Fruit(fruit);
				placeFruit(fruit_tmp);
				fruits.add(fruit_tmp);
			}
			fruits.sort(comp);
		}
	}

	private int nextNode(graph graph, int src, int dst) {
		node_data n_src = this.graph.getNode(src);
		for (edge_data e : this.graph.getE(src)) {
			if (e.getDest() == dst)
				return dst;
		}
		return -1;
	}

	// private void initGui(int width, int height) {
	//
	// }
	public void paint(Graphics graphics) {
		super.paint(graphics);
		BufferedImage buffer = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = buffer.createGraphics();
		Graphics2D g2dComponent = (Graphics2D) graphics;
		g2dComponent.drawImage(buffer, null, 0, 0);
		
		double[] x_toScale = find_min_max_axis();
		double[] y_toScale = find_min_max_ayis();
		Graphics2D g = (Graphics2D) graphics;
		for (node_data node : graph.getV()) {
			g.setColor(Color.BLUE);
			Shape circle = new Arc2D.Double(node.getGuiLocation().x() - 3, node.getGuiLocation().y() - 3, 15, 15, 0,
					360, Arc2D.CHORD);
			g.fill(circle);
			String id = node.getKey() + "";
			g.setFont(new Font("deafult", Font.BOLD, 14));
			g.drawString(id, node.getGuiLocation().ix() + 3, node.getGuiLocation().iy());
		}

		for (Fruit fruit : this.fruits) {
			if (fruit.getType() == 1) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.ORANGE);
			}
			double x_gui = scale(fruit.getLocation().x(), x_toScale[0], x_toScale[1], 5, this.getWidth() - 50);
			double y_gui = scale(fruit.getLocation().y(), y_toScale[0], y_toScale[1], 70, this.getHeight() - 70);
			fruit.setLocationOnGui(x_gui, y_gui);
			Shape circle = new Arc2D.Double(fruit.getLocationOnGui().x() - 10, fruit.getLocationOnGui().y() - 10, 30,
					30, 0, 360, Arc2D.CHORD);
			g.fill(circle);
			String id = fruit.getValue() + "";
			g.setFont(new Font("deafult", Font.BOLD, 14));
			g.setColor(Color.BLUE);
			g.drawString(id, fruit.getLocationOnGui().ix() + 3, fruit.getLocationOnGui().iy());
		}

		for (node_data node : graph.getV()) {
			if (graph.getE(node.getKey()) != null) {
				for (edge_data edge : graph.getE(node.getKey())) {
					g.setColor(Color.BLACK);
					g.setFont(new Font("deafult", Font.BOLD, 14));
					String weight = edge.getWeight() + "";
					node_data dst = graph.getNode(edge.getDest());
					g.drawLine(node.getGuiLocation().ix(), node.getGuiLocation().iy(), dst.getGuiLocation().ix(),
							dst.getGuiLocation().iy());
					g.setColor(Color.RED);

					// calculate the direction oval location
					int mid_x = ((node.getGuiLocation().ix() + dst.getGuiLocation().ix()) / 2);
					int mid_y = ((node.getGuiLocation().iy() + dst.getGuiLocation().iy()) / 2);
					int d_x = (((((mid_x + dst.getGuiLocation().ix()) / 2) + dst.getGuiLocation().ix()) / 2)
							+ dst.getGuiLocation().ix()) / 2;
					int d_y = (((((mid_y + dst.getGuiLocation().iy()) / 2) + dst.getGuiLocation().iy()) / 2)
							+ dst.getGuiLocation().iy()) / 2;

					g.fillOval(d_x - 3, d_y - 3, NODE_WIDTH_HEIGHT, NODE_WIDTH_HEIGHT);
				}
			}
		}
		for (Robot robot : this.robots) {
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke((float) 3.0));
			double x_gui = scale(robot.getLocation().x(), x_toScale[0], x_toScale[1], 5, this.getWidth() - 50);
			double y_gui = scale(robot.getLocation().y(), y_toScale[0], y_toScale[1], 70, this.getHeight() - 70);
			robot.setGui_location(x_gui, y_gui);
			Shape elipse = new Ellipse2D.Double(robot.getGui_location().x() - 7, robot.getGui_location().y() - 7, 20,
					20);
			g.draw(elipse);
		}
	}

	private double[] find_min_max_axis() {
		double[] x_scale = new double[2];
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;

		for (node_data node : this.graph.getV()) {

			if (node.getLocation().x() < min)
				min = node.getLocation().x();
			else {
				if (node.getLocation().x() > max) {
					max = node.getLocation().x();
				}
			}
		}

		x_scale[0] = min;
		x_scale[1] = max;

		return x_scale;

	}

	private double[] find_min_max_ayis() {
		double[] y_scale = new double[2];
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;

		for (node_data node : this.graph.getV()) {

			if (node.getLocation().y() < min)
				min = node.getLocation().y();
			else {
				if (node.getLocation().y() > max) {
					max = node.getLocation().y();
				}
			}
		}

		y_scale[0] = min;
		y_scale[1] = max;

		return y_scale;

	}

	private double scale(double data, double r_min, double r_max, double t_min, double t_max) {
		double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
		return res;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String dst_str = JOptionPane.showInputDialog(this,
				"Please insert node number to move, " + "\n" + "only neighbors nodes are allowed: ");
		try {
			int dst = Integer.parseInt(dst_str);
			user_Dst = dst;
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(this, "Invalid input", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public static void main(String[] a) throws InterruptedException {

		// game_service game = Game_Server.getServer(2); // you have [0,23]
		// games
		// System.out.println(game.getGraph());
		MyGame m = new MyGame(3);

		// System.out.println(m.g);
	}

}