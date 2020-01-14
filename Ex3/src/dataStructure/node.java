package dataStructure;

import java.io.Serializable;

import utils.Point3D;

public class node implements node_data, Serializable {
	private int key, tag;
	private Point3D location;
	private double weight;
	private String info;
	private Point3D gui_location;

	public node() {
		key = 0;
		tag = 0;
		weight = 0;
		info = "";
	}

	protected node(node n) {
		key = n.key;
		tag = n.tag;
		weight = n.weight;
		info = n.info;
		location = n.location;
	}

	public node(int key, Point3D location, double weight) {
//		if(weight<=0)
//		{
//			System.err.println("The weight must be positive! . The node hadn't been added successfully..");
//			return;
//		}
		this.key = key;
		this.location = location;
		tag = 0;
		this.weight = weight;
		info = "";
	}

	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = p;

	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}

	@Override
	public void setGuiLocation(double x, double y) {
		this.gui_location = new Point3D(x, y);
	}

	@Override
	public Point3D getGuiLocation() {
		return this.gui_location;
	}

}
