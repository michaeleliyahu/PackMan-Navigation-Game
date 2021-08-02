package gameClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import Server.Fruit;
import Server.Game_Server;
import Server.fruits;
import Server.game_service;
import Server.robot;
import oop_dataStructure.oop_node_data;
import dataStructure_ex3.DGraph;
import dataStructure_ex3.Node;
import dataStructure_ex3.edge_data;
import dataStructure_ex3.graph;
import gui.MyGameGUI;
import utils.Point3D;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;


/*
 * Game par - is our main class for gathering all the information from the server and sends them
 *  throughout all over the code
 *  Game par - will save in every given moment all the fruits,robots , current game and current graph
 */
public class GamePar implements Game_par {
	List<myFruit>fr = new ArrayList<myFruit>();
	List<myRobot> rb = new ArrayList<myRobot>();
	int scene;
	game_service game;
	graph grap;
	public GamePar(int scene,DGraph grap) {
		this.scene=scene;
		game = Game_Server.getServer(this.scene);
		this.grap=grap;
	}
	/*
	 * This function will Get json string from game server , 
	 * and sends it myFruti class to init,
	 * in the end the function will have list of all the current game fruits
	 * and save them as local variable list of myFruits
	 */
	public void initFruit(List<String> fruit_info) {	
		try {
			for (int i = 0; i < fruit_info.size(); i++) {

				myFruit temp = new myFruit(fruit_info.get(i),getedges(),grap);

				fr.add(temp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
	@Override
//return list of all edges in the graph
	public List<edge_data> getedges() {
		List<edge_data> edges = new ArrayList<edge_data>();
		Collection<dataStructure_ex3.node_data> nodes = this.grap.getV();
		for (dataStructure_ex3.node_data nd : nodes) {
			Collection<dataStructure_ex3.edge_data> checksize = this.grap.getE(nd.getKey());
			if (checksize.size()!=0) {
				for (edge_data putedges : checksize) {
					edges.add(putedges);
				}
			}
		}
		return edges;
	}
	//add to our current local myRobot List the info about each robot 
	public void initRobot(List<String> robot_info)  {
		try {
			for (int i = 0; i < robot_info.size(); i++) 
			{
				myRobot temp = new myRobot(robot_info.get(i));
				rb.add(temp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//return the number robot in the game
	public int numRobot(String info)
	{
		int num = 0;
		try {

			String temp = info.substring(1);
			int y = temp.indexOf("{");
			info = info.substring(0, y)+":[{"+info.substring(y+2, info.length()-2)+"}]}";
			JSONObject jsonObject = new JSONObject(info);
			JSONArray sum_rob =jsonObject.getJSONArray("GameServer");
			JSONObject empObj = new JSONObject();
			empObj = sum_rob.getJSONObject(0);
			num = (int) empObj.get("robots");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	
	//return list of edges with fruit on top
	@Override
	public List<edge_data> fruit_edges(){

		List<edge_data> edgefruit = new ArrayList<edge_data>();
		for (myFruit nowfruit : fr)
		{
			edgefruit.add(nowfruit.getsrc());
		}
		return edgefruit;
	}

	@Override
	public List<myFruit> getfruit() {
		return fr;
	}
	@Override

	public List<myRobot> getrobot() {
		return rb;
	}

}
