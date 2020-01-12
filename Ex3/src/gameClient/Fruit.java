package gameClient;

import org.json.JSONObject;

import dataStructure.edge_data;
import utils.Point3D;

public class Fruit implements FruitInterface {

	private int type;
	private double value;
	private edge_data edge;
	private Point3D position;
	private Point3D guiPostion;

	// constructor from point
	public Fruit(double val, Point3D p) {
		this.value = val;
		this.position = new Point3D(p);
	}

	public Fruit() {
	}
	
	// constructor from JSON
	public Fruit(String str) {
		this();
		try {
			JSONObject fruit = new JSONObject(str);
			fruit = fruit.getJSONObject("fruit");
			double v = fruit.getDouble("value");
			this.value = v;
			String p = fruit.getString("pos");
			this.position = new Point3D(p);
			int t = fruit.getInt("type");
			this.type = t;
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public String toJSON() {
		String ans = "{\"Fruit\":{\"value\":" + this.value + "," + "\"type\":" + this.type + "," + "\"pos\":\"" + this.position.toString() +"\"e\":\"" + this.edge.toString()+ "\"" + "}" + "}";
        return ans;
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public Point3D getLocation() {
		return new Point3D(this.position);
	}

	@Override
	public Point3D getLocationOnGui() {
		return new Point3D(this.guiPostion);
	}

	@Override
	public void setLocationOnGui(double x, double y) {
		this.guiPostion = new Point3D(x,y);

	}

	@Override
	public double getValue() {
		return this.value;
	}

	@Override
	public void setEdge(edge_data e) {
		this.edge = e;

	}

	@Override
	public edge_data getEdge() {
		return this.edge;
	}

}
