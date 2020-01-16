package gameClient;

import org.json.JSONObject;

import dataStructure.edge_data;
import utils.Point3D;

public class Fruit implements FruitInterface {

	/**
	 * This class represents the set of operations applicable on a fruit.
	 * Fruit sttributes are:
	 * 1.Location
	 * 2.Value (There is no setter for value because there is no need
	 * 3.Type- Apple/Banana.
	 * 4.Edge- which fruit is located on
	 */
	private int type;
	private double value;
	private edge_data edge;
	private Point3D position;
	private Point3D guiPostion;

	// Constructor from point
	public Fruit(double val, Point3D p) 
	{
		this.value = val;
		this.position = new Point3D(p);
	}

	//Default constructor
	public Fruit() {
	}
	
	/**
     * Constructor init fruit attributes from a json string input.
     *
     * @param str
     */
	public Fruit(String str) {
		this();
		try {
			JSONObject fruit = new JSONObject(str);
			fruit = fruit.getJSONObject("Fruit");
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
	/**
     * Return the string that represents a fruit
     *
     * @return
     */
	public String toJSON() {
		String ans = "{\"Fruit\":{\"value\":" + this.value + "," + "\"type\":" + this.type + "," + "\"pos\":\"" + this.position.toString() +"\"e\":\"" + this.edge.toString()+ "\"" + "}" + "}";
        return ans;
	}

	@Override
	
	/**
     * toString- return the string that represents a fruit, using a helper function- toJson
     *
     * @return fruit- as string
     */
	public String toString() 
	{
		return this.toJSON();
		}

	@Override
	/**
     * Getter for type attribute.
     * Type is terminate for each fruit by game server.
     * Type can be - Apple/Banana
     * Apple - indicates that the fruit edge is from source lower key node to higher destination node key.
     * Banana- indicates that the fruit edge is from source higher key node to lower destination node key.
     *
     * @return type
     */
	public int getType() {
		return this.type;
	}

	@Override
	/**
     * Getter for location attribute
     *
     * @return return pos
     */
	public Point3D getLocation() {
		return new Point3D(this.position);
	}

	@Override
	/**
     * Getter for location of the fruit on the gui window
     * This parameter is relevant for fruit drawing in the game.
     *
     * @return guiPos
     */
	public Point3D getLocationOnGui() {
		return new Point3D(this.guiPostion);
	}

	@Override
	/**
     * Setter for location of the fruit on the gui window.
     *
     * @param x  - x point coordinate
     * @param y- y point coordinate
     */

	public void setLocationOnGui(double x, double y) {
		this.guiPostion = new Point3D(x,y);

	}

	@Override
	/**
     * Getter for the value attribute of the fruit.
     *
     * @return value
     */
	public double getValue() {
		return this.value;
	}

	@Override
	/**
     * Setter for the edge that the fruit is on it
     *
     * @param e - edge
     */
	public void setEdge(edge_data e) {
		this.edge = e;

	}

	@Override
	/**
     * Getter for the edge that the fruit is on it
     *
     * @return this.edge
     */
	public edge_data getEdge() {
		return this.edge;
	}

	@Override
	/**
	 * Unnecessary function
	 */
	public void setValue(double val) {
		this.value=val;
	}


}