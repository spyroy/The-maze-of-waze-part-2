package gameClient;

import org.json.JSONObject;

import utils.Point3D;

public class Robot implements RobotInterface {
	private int id;
	private double value;
	private double speed;
	private int source;
	private int destination;

	private Point3D location;
	private Point3D gui_location;

	public Robot() {
	}
	
	public Robot(String str)
    {
        this();
        try {
            JSONObject robot = new JSONObject(str);
            robot=robot.getJSONObject("Robot");
            double val = robot.getDouble("value");
            int src=robot.getInt("src");
            int dst=robot.getInt("dest");
            double speed=robot.getDouble("speed");
            String pos=robot.getString("pos");
            this.value=val;
            this.location=new Point3D(pos);
            this.source=src;
            this.destination=dst;
            this.speed=speed;
            this.setGui_location(0,0);
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
    }

	@Override
	public String toJSON() {
		 String ans = "{\"Robot\":{\"id\":" + this.id + "," + "\"value\":" + this.value + "," + "\"src\":" + this.source + "," + "\"dest\":" + this.destination + "," + "\"speed\":" + this.getSpeed() + "," + "\"pos\":\"" + this.location.toString() + "\"" + "}" + "}";
	        return ans;
	}

	@Override
	public String toJSON_OnGui() {
		String ans = "{\"Robot\":{\"id\":" + this.id + "," + "\"value\":" + this.value + "," + "\"src\":" + this.source + "," + "\"dest\":" + this.destination + "," + "\"speed\":" + this.getSpeed() + "," + "\"pos\":\"" + this.location.toString() +"\"pos_gui\":\"" + this.getGui_location().toString() + "\"" + "}" + "}";
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
		return this.source;
	}

	@Override
	public void setSrc(int src) {
		this.source = src;
	}

	@Override
	public int getDest() {
		return this.destination;
	}

	@Override
	public void setDest(int dest) {
		this.destination = dest;
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
		return this.gui_location;
	}

	@Override
	public void setGui_location(double x, double y) {
		this.gui_location = new Point3D(x,y);

	}

}
