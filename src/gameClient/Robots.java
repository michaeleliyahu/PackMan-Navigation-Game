package gameClient;

import utils.Point3D;
/**
 * This interface represents the set of operations applicable on a 
 * robots.
 * @author Tomer, Michael and AVI
 *
 */
public interface Robots {
	
	
	/**
	 * Return the Value of the robot.
	 * @return
	 */
	public double getValue();
	/**
	 * Return the Location of the robot.
	 * @return
	 */
	public Point3D getLocation(); 
	/**
	 * Return the src of the robot.
	 * @return
	 */
	public int getSrc();
	/**
	 * Return the id of the robot.
	 * @return
	 */
	public int getId();
	/**
	 * Return the dest of the robot.
	 * @return
	 */
	public int getDest();
	/**
	 * Return the speed of the robot.
	 * @return
	 */
	public double getSpeed();
	
	public String toString();
	/**
	 * set the dest of the robot.
	 * @return
	 */
	public void setDest(int dest);
	/**
	 * set the src of the robot.
	 * @return
	 */
	public void setSrc(int src);
	/**
	 * set the Location of the robot.
	 * @return
	 */
	public void setLocation(Point3D loc);
	

}
