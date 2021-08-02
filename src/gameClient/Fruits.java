package gameClient;

import java.util.List;

import org.json.JSONObject;

import dataStructure_ex3.edge_data;
import dataStructure_ex3.graph;
import utils.Point3D;
/**
 * This interface represents the set of operations applicable on a 
 * fruits.
 * @author Tomer, Michael and AVI
 *
 */
public interface Fruits  {

	public void setedges(List<edge_data> coledg,Point3D fruit,graph grap);
	
	public edge_data getsrc();
	/**
	 * Return the Value of the fruit.
	 * @return
	 */
	public double getValue();
	/**
	 * Return the Location of the fruit.
	 * @return
	 */
	public Point3D getLocation();
	/**
	 * Return the type of the fruit.
	 * @return
	 */
	public int getType();
	
	public String toString();

}
