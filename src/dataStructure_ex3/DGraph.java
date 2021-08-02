package dataStructure_ex3;

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

import dataStructure_ex3.DGraph;
import dataStructure_ex3.edge_data;
import dataStructure_ex3.graph;
import dataStructure_ex3.node_data;

import org.json.*;

import utils.Point3D;


public class DGraph implements graph,Serializable {

	Hashtable<Integer, node_data> nodes = new Hashtable<Integer, node_data>();
	Hashtable<node_data, Hashtable<Integer, edge_data>> edges = new Hashtable<node_data,Hashtable<Integer, edge_data>>();
	int count_ed = 0;
	int count_mc = 0;


	public void init(String check) {

		DGraph grap = new DGraph();
		// System.out.println(robot_json);
		// JSONParser file = new JSONParser();
		JSONStringer file = new JSONStringer();
		try {

			// Object obj = file.parse(new FileReader(check));

			JSONObject jsonObject = new JSONObject(check);
			// JSONArray jsonArray= jsonObject.getJSONArray("Employees");
			JSONArray json_Edges =jsonObject.getJSONArray("Edges");
			JSONArray json_Nodes =jsonObject.getJSONArray("Nodes");
			// JSONArray json_Edges = (JSONArray)jsonObject.get("Edges");
			// JSONArray json_Nodes = (JSONArray)jsonObject.get("Nodes");

			for (int i = 0; i < json_Nodes.length(); i++) {

				JSONObject empObj = new JSONObject();
				empObj = json_Nodes.getJSONObject(i);
				int src = (int) empObj.get("id");
				String pos = (String)empObj.get("pos");
				Point3D loc = new Point3D(pos);
				this.addNode(new Node(src, loc));
			}

			for (int i = 0; i < json_Edges.length(); i++) {


				JSONObject empObj = new JSONObject();
				empObj = json_Edges.getJSONObject(i);

				double src = Double.parseDouble(empObj.get("src").toString());
				double w = (double) empObj.get("w");
				int dest = (int) empObj.get("dest");
				this.connect((int)src, dest, w);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub

		return nodes.get(key);

	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// TODO Auto-generated method stub

		node_data x = null;
		if(nodes.containsKey(src) && edges.get(nodes.get(src)).containsKey(dest)) {
			x = nodes.get(src);	
			return edges.get(x).get(dest);
		}
		else {

			return null;
		}
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		count_mc++;

		nodes.put(n.getKey(), n);
		edges.put(n , new Hashtable<Integer, edge_data>());
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		//src = dest
		if(src==dest) {
			return;
		}
		if(w<0) {
			throw new RuntimeException("whight cant be negative value");
		}
		if(nodes.containsKey(src) && nodes.containsKey(dest)) {
			count_mc++;
			count_ed++;
			node_data k = nodes.get(src);
			edges.get(k).put(dest,  new Edge(src, dest, w));
		}

	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub

		return nodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub

		node_data k = nodes.get(node_id);
		return edges.get(k).values();
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		count_mc++;

		Collection<node_data> col = getV();
		Iterator<node_data> itr = col.iterator();

		while (itr.hasNext()) 
		{
			node_data temp = itr.next();
			if(edges.get(temp).containsKey(key)==true)
			{
				edges.get(temp).remove(key);
				count_ed--;
			}
		}
		node_data k = nodes.get(key);
		count_ed = count_ed - edges.get(k).size();
		edges.remove(k);

		return nodes.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		count_mc++;
		count_ed--;
		node_data k = nodes.get(src);				
		return edges.get(k).remove(dest);
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub

		return nodes.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub

		return count_ed;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return count_mc;
	}

}
