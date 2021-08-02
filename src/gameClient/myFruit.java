package gameClient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dataStructure_ex3.edge_data;
import dataStructure_ex3.graph;
import utils.Point3D;

public class myFruit implements Fruits {

	
		double value ;
		int type;
		Point3D location;
		List<edge_data> alledges ;
		graph graps;
		edge_data src_edge;
		boolean bool = false;
		
		//{"Fruit":{"value":5.0,"type":-1,"pos":"35.188900353135324,32.105320110855615,0.0"}}
		//{"Fruit":[{"value":5.0,"type":-1,"pos":"35.188900353135324,32.105320110855615,0.0"}]}
		
		
		public myFruit() {
			// TODO Auto-generated constructor stub
		}
		//read from JSON and keep the all information about the fruit
		public myFruit(String info , List<edge_data> edge_for_edes,graph grap) throws JSONException {
			this.graps=grap;
			alledges = edge_for_edes;
			String temp = info.substring(1);
			int y = temp.indexOf("{");
			info = info.substring(0, y)+":[{"+info.substring(y+2, info.length()-2)+"}]}";
			JSONObject jsonObject = new JSONObject(info);
			JSONArray json_fruit =jsonObject.getJSONArray("Fruit");
			JSONObject empObj = new JSONObject();
			empObj = json_fruit.getJSONObject(0);
			this.value = (double) empObj.get("value");
			this.type = (int) empObj.get("type");
			//this.location = (Point3D)empObj.get("pos");
			String pos = (String)empObj.get("pos");
			Point3D loc = new Point3D(pos);
			this.location=loc;
			setedges(alledges, this.location, grap);
			
		//	System.out.println("src:"+this.src_edge.getSrc()+" dest:" +this.src_edge.getDest());
			
	}
		/*check on which edges the fruit "sitting"
		 * check on which direction the fruit "sitting"
		 */
		@Override
		public void setedges(List<edge_data> coledg,Point3D fruit,graph grap)
		{
			double x_fruit=fruit.x();
			double y_fruit=fruit.y();
					
			for (edge_data ed : coledg) {
				//a
				double edg_x1 = grap.getNode(ed.getSrc()).getLocation().x();			
				double edg_y1 = grap.getNode(ed.getSrc()).getLocation().y();
				//b
				double edg_x2 = grap.getNode(ed.getDest()).getLocation().x();
				double edg_y2 = grap.getNode(ed.getDest()).getLocation().y();
				
				double x1_x2 = (edg_x2-edg_x1)*(edg_x2-edg_x1);
				double y1_y2 = (edg_y2-edg_y1)*(edg_y2-edg_y1);
				
				double dis_a_b = Math.sqrt(x1_x2+y1_y2);
				
				//a to f
				double dis_a_f = Math.sqrt((x_fruit-edg_x1)*(x_fruit-edg_x1)+((y_fruit-edg_y1)*(y_fruit-edg_y1)));
				
				//a to b
				double dis_f_b = Math.sqrt((edg_x2-x_fruit)*(edg_x2-x_fruit)+((edg_y2-y_fruit)*(edg_y2-y_fruit)));
				
				double EPS = 0.0000001;
				if ((dis_a_f+dis_f_b)-dis_a_b<=EPS) {
					if(this.type==-1 && ed.getSrc()>ed.getDest()) {
						this.src_edge=ed;
					}
					else if(this.type==1 && ed.getSrc()<ed.getDest()) {
						this.src_edge=ed;
					}
				}						
			}		
		}
		public boolean getBool() {
			return this.bool;
		}
		public void setBool() {
			this.bool=true;
		}
		@Override

		public edge_data getsrc() {
			return this.src_edge;
			
		}
		@Override

		public double getValue() {
			return this.value;
		}
		@Override

		public Point3D getLocation() {
			return this.location;
		}
		@Override

		public int getType() {
			return this.type;
		}
		@Override

		public String toString() {
			return "value: "+this.value+" location: "+this.location+" type: " +this.type;		
			}
		

		/*read from JSON and keep fruit information 
		 * constructor for kml
		 */
		public void initFromJson(String json)
		{
			if(!json.isEmpty())
			{
				try
				{
					JSONObject obj = new JSONObject(json);
					JSONObject CurrFruit = (JSONObject) obj.get("Fruit");
					String pos = CurrFruit.getString("pos");
					String[] arr = pos.split(",");
					double x = Double.parseDouble(arr[0]);
					double y = Double.parseDouble(arr[1]);
					double z = Double.parseDouble(arr[2]);
					this.location = new Point3D(x, y, z);
					int value = CurrFruit.getInt("value");
					this.value = value;
					int type = CurrFruit.getInt("type");
					this.type = type;
					
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	
}
