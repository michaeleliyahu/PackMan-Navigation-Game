package gameClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dataStructure_ex3.edge_data;
/**
 * This interface represents the set of operations applicable on 
 * GamePar class.
 * @author Tomer, Michael and AVI
 *
 */
public interface Game_par {
	/**
	 * Return the edges
	 * @return
	 */
	public List<edge_data> getedges() ;
	
	/**
	 * Return list of the edges
	 * @return
	 */
	public List<edge_data> fruit_edges();
	/**
	 * Return list of the myFruit
	 * @return
	 */
	
	public List<myFruit> getfruit();
	/**
	 * Return list of the myRobot
	 * @return
	 */
	public List<myRobot> getrobot();

	

}
