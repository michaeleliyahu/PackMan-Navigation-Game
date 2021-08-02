
package gameClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.Point3D;

///////robots:[{"Robot":{"id":0,"value":0.0,"src":0,"dest":-1,"speed":1.0,"pos":"35.212217299435025,32.106235628571426,0.0"}}]
public class myRobot implements Robots {

	int id ;
	double value ;
	int src ;
	int dest ;
	double speed;
	Point3D location;     // pos
	public myRobot() {
		// TODO Auto-generated constructor stub
	}
	
	//read from JSON and keep the all information about the fruit
	public myRobot(String info) throws JSONException {

		String temp = info.substring(1);
		int y = temp.indexOf("{");
		info = info.substring(0, y)+":[{"+info.substring(y+2, info.length()-2)+"}]}";
		JSONObject jsonObject = new JSONObject(info);
		JSONArray json_fruit =jsonObject.getJSONArray("Robot");
		JSONObject empObj = new JSONObject();

		empObj = json_fruit.getJSONObject(0);
		this.id = (int) empObj.get("id");
		this.value = (double) empObj.get("value");
		this.src = (int) empObj.get("src");
		this.dest = (int) empObj.get("dest");
		this.speed = (double) empObj.get("speed");
		//this.location = (Point3D)empObj.get("pos");
		String pos = (String)empObj.get("pos");
		Point3D loc = new Point3D(pos);
		this.location=loc;

	}
	
	@Override
	public double getValue() {     //this.id
		return this.value;
	}
	
	@Override
	public Point3D getLocation() {
		return this.location;
	}
	
	@Override
	public int getSrc() {
		return this.src;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public int getDest() {
		return this.dest;
	}
	
	@Override
	public double getSpeed() {
		return this.speed;
	}
	
	@Override
	public String toString() {
		return  "id: "+this.id+" value: "+this.value+"src : " +this.src+" dest:"+this.dest+" speed:"+this.speed+" location: "+this.location;
	}
	
	@Override
	public void setDest(int dest) {
		this.dest=dest;
	}
	
	@Override
	public void setSrc(int src) {
		this.src=src;
	}
	
	@Override	
	public void setLocation(Point3D loc) {
		this.location = loc;
	}

}
