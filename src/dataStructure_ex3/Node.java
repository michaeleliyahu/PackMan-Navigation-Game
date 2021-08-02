package dataStructure_ex3;

import java.io.Serializable;

import dataStructure_ex3.node_data;
import utils.Point3D;

public class Node implements node_data , Serializable {

	int key;
	Point3D Location;
	double weight;
	String info ;
	int tag;

	public Node (int key,Point3D Location) {
		this.key = key;
		this.setLocation(Location);		
//		this.info="to be implented";
		this.setTag(0);
	}
	
	public Node (int key , Point3D Location , double weight , String info , int tag ) {
		this.key = key;
		this.setLocation(Location);		
		this.weight = weight;
		this.info = info;
		this.setTag(tag);
	}

	@Override
	public int getKey() {

		return this.key;
	}

	@Override
	public Point3D getLocation() {
		// TODO Auto-generated method stub
		return this.Location;
	}

	@Override
	public void setLocation(Point3D p) {
		// TODO Auto-generated method stub
		this.Location = new Point3D(p.x(), p.y(), p.z());
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub
		this.weight = w;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		this.info = s;		
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;		
	}


}
