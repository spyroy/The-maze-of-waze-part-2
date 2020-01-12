package gameClient;

import dataStructure.edge_data;
import utils.Point3D;

/**
 * Each fruit has to be on an edge, hence it can't be "floating" fruit
 * The fruits can be neither apple or banana.
 * If the robot goes from a lower key of node to a bigger key, and there is an apple, the robot will take the apple and the apple will change the position to a different edge randomly.
 * If the robot goes from a bigger key of node to a lower key, and there is a banana, the robot will take the banana and the banana will change the position to a different edge randomly.
 */

public interface FruitInterface {
	
	
	
	/**
	 * represent the string of a fruit, every fruit has type, location and 
	 * edge that the fruit is on. 
	 */
	public String toString();
	
	/**
	 * convert the string of a fruit to JSON that can be read later 
	 * @return 
	 */
	public String toJSON();
	
	//*************getters and setters*************//
	
	/**
	 * gets the fruit type which can be 
	 * apple if the id is ascending 
	 * or banana if the id is descending. 
	 * @return
	 */
	public int getType();
	
	/**
	 * gets the location of the fruit on the x axis ant y axis.
	 * @return
	 */
	public Point3D getLocation();
	
	/**
	 * gets the location of the fruit on the
	 * graphic window.
	 * @return
	 */
	public Point3D getLocationOnGui();
	
	/**
	 * set the location of a fruit on the graphic window.
	 * @param x
	 * @param y
	 */
	public void setLocationOnGui(double x, double y);
	
	/**
	 * each fruit has a value, aka as points, this function 
	 * returns how much the fruit is worth.
	 * @return
	 */
	public double getValue();
	
	/**
	 * set the edge the fruit is "sitting" on.
	 * @param e
	 */
	public void setEdge(edge_data e);
	
	/**
	 * get the edge the fruit is "sitting" on
	 * @return
	 */
	public edge_data getEdge();
}
