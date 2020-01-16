package gameClient;

import utils.Point3D;

/**
 * each robot has to be on a node, there can be 1 robot up to 5, the robots need
 * to take the fruits as fast as possible.
 * 
 * @author MatanGreenberg
 *
 */

public interface RobotInterface {

	/**
	 * convert the string of a robot to JSON that can be read later
	 * 
	 * @return
	 */
	public String toJSON();

	/**
	 * convert the string of a robot to JSON with location on gui, that can be read
	 * later
	 * 
	 * @return
	 */
	public String toJSON_OnGui();

	/**
	 * represent the string of a robot, every robot has id, value, source node,
	 * destination node, speed location and location on the graphic window. edge
	 * that the fruit is on.
	 */
	public String toString();

	/**
	 * gets the id number of a robot each robot has a unique id.
	 * 
	 * @return
	 */
	public int getId();

	/**
	 * set the id of a robot each robot has a unique id.
	 * 
	 * @param id
	 */
	public void setId(int id);

	/**
	 * gets the value aka as the points that each robot collected so far.
	 * 
	 * @return
	 */
	public double getValue();

	/**
	 * set the value aka points to specific robot.
	 * 
	 * @param value
	 */
	public void setValue(double value);

	/**
	 * gets the node that the robot is "sitting" on right now.
	 * 
	 * @return
	 */
	public int getSrc();

	/**
	 * sets the node that the robot is "sitting" on right now
	 * 
	 * @param src
	 */
	public void setSrc(int src);

	/**
	 * gets the node that the robot is going to be next "move" (move is a action
	 * that moves all robots).
	 * 
	 * @return
	 */
	public int getDest();

	/**
	 * sets the node that the robot is going to be next "move" (move is a action
	 * that moves all robots).
	 * 
	 * @param dest
	 */
	public void setDest(int dest);

	/**
	 * gets the speed of the robot, the speed is how quickly the robot moves along
	 * an edge.
	 * 
	 * @return
	 */
	public double getSpeed();

	/**
	 * sets the speed of the robot, the speed is how quickly the robot moves along
	 * an edge.
	 * 
	 * @param speed
	 */
	public void setSpeed(double speed);

	/**
	 * get the current location of a robot.
	 * 
	 * @return
	 */
	public Point3D getLocation();

	/**
	 * set the current location of a robot.
	 * 
	 * @param location
	 */
	public void setLocation(Point3D location);

	/**
	 * get the location of a robot on the graphic window
	 * 
	 * @return
	 */
	public Point3D getGui_location();

	/**
	 * set the location of a robot on the graphic window
	 * 
	 * @return
	 */
	public void setGui_location(double x, double y);

}
