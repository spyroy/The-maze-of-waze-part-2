package gameClient;

import org.json.JSONObject;

import utils.Point3D;

public class Robot implements RobotInterface {

	private int id;
	private double value;
	private double speed;
	private int src;
	private int dest;
	private Point3D location;
	private Point3D GuiLocation;

    //Default constructor
	public Robot() {
	}

	/**
     * Constructor init robot attributes from a json string input.
     *
     * @param str
     */
	public Robot(String str) {
		this();
		try {
			JSONObject robot = new JSONObject(str);
			robot = robot.getJSONObject("Robot");
			double v = robot.getDouble("value");
			int s = robot.getInt("src");
			int d = robot.getInt("dest");
			double speed = robot.getDouble("speed");
			String p = robot.getString("pos");
			this.value = v;
			this.location = new Point3D(p);
			this.src = s;
			this.dest = d;
			this.speed = speed;
			this.setGui_location(0, 0);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public String toJSON() {
		String ans = "{\"Robot\":{\"id\":" + this.id + "," + "\"value\":" + this.value + "," + "\"src\":" + this.src
				+ "," + "\"dest\":" + this.dest + "," + "\"speed\":" + this.getSpeed() + "," + "\"pos\":\""
				+ this.location.toString() + "\"" + "}" + "}";
		return ans;
	}

	@Override
	public String toJSON_OnGui() {
		String ans = "{\"Robot\":{\"id\":" + this.id + "," + "\"value\":" + this.value + "," + "\"src\":" + this.src
				+ "," + "\"dest\":" + this.dest + "," + "\"speed\":" + this.getSpeed() + "," + "\"pos\":\""
				+ this.location.toString() + "\"pos_gui\":\"" + this.getGui_location().toString() + "\"" + "}" + "}";
		return ans;
	}

	@Override
	 /**
     * Getter for the id of the robot
     *
     * @return id
     */
	public int getId() {
		return this.id;
	}

	@Override
	/**
     * Setter for the robot id
     *
     * @param id
     */
	public void setId(int id) {
		this.id = id;

	}

	@Override
	/**
     * Getter for the robot value
     *
     * @return value
     */
	public double getValue() {
		return this.value;
	}

	@Override
	/**
     * Setter for the robot value
     *
     * @param value
     */
	public void setValue(double value) {
		this.value = value;

	}

	@Override
	/**
     * Getter for the robot source node that the robot is on it.
     *
     * @return src
     */
	public int getSrc() {
		return this.src;
	}

	@Override
	/**
     * Setter for the robot source node that the robot is on it.
     *
     * @param src
     */
	public void setSrc(int src) {
		this.src = src;

	}

	@Override
	/**
     * Getter for the robot destination node
     *
     * @return dest
     */
	public int getDest() {
		return this.dest;
	}

	@Override
	/**
     * Setter for the robot destination node
     *
     * @param dest
     */
	public void setDest(int dest) {
		this.dest = dest;

	}

	@Override
	/**
     * Getter for the robot's speed attribute
     *
     * @return speed
     */
	public double getSpeed() {
		return this.speed;
	}

	@Override
	/**
     * Setter for the robot's speed
     *
     * @param speed
     */
	public void setSpeed(double speed) {
		this.speed = speed;

	}

	@Override
	/**
     * Getter for the robot's location
     *
     * @return location
     */
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	/**
     * Setter for the robot's location
     *
     * @param location
     */
	public void setLocation(Point3D location) {
		this.location = location;

	}

	@Override
	/**
     * Getter for the gui location of the robot, 
     * relevant for drawing a robot in the gui.
     *
     * @return GuiLocation
     */ 
	public Point3D getGui_location() {
		return this.GuiLocation;
	}

	@Override
	/**
     * Setter for the gui location of the robot .
     *
     * @param x  - x point coordinate
     * @param y- y point coordinate
     */
	public void setGui_location(double x, double y) {
		this.GuiLocation = new Point3D(x,y);

	}

}