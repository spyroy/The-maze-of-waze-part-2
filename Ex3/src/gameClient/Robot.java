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

	public Robot() {
	}

	// constructor from JSON
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
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	@Override
	public double getValue() {
		return this.value;
	}

	@Override
	public void setValue(double value) {
		this.value = value;

	}

	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public void setSrc(int src) {
		this.src = src;

	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public void setDest(int dest) {
		this.dest = dest;

	}

	@Override
	public double getSpeed() {
		return this.speed;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;

	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D location) {
		this.location = location;

	}

	@Override
	public Point3D getGui_location() {
		return this.GuiLocation;
	}

	@Override
	public void setGui_location(double x, double y) {
		this.GuiLocation = new Point3D(x,y);

	}

}
