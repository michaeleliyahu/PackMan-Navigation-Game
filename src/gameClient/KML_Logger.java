package gameClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;

import Server.game_service;
import dataStructure_ex3.edge_data;
import dataStructure_ex3.graph;
import dataStructure_ex3.node_data;
/*
 * KML Logger - is our project creating KML files , saves 
 * the class gets all nodes and edges  , than all the fruits and robots on every given moment
 * and writes them into a KML format .kml file
 * the file can be opened and viewed in google Earth pro app
 */
public class KML_Logger {

	game_service game;
	GamePar now;
	graph grap;
	String all_kml="";
	String ans;
	public KML_Logger() {

	}
	public void set_now(GamePar now) {
		this.now = now;
	}
	public GamePar get_now() {
		return now;
	}
	public void setGraph(graph grap) {
		this.grap= grap;
	}
	public graph getGraph(){
		return grap;
	}
	public void setGame(game_service game) {
		this.game= game;
	}
	public game_service getGame(){
		return game;
	}
	/*
	 * this function starts to create our KML file by adding all the needed styles , vertexes and edges 
	 * to the kml file
	 */
	public void openKML() {
		Collection<node_data> keep_node = grap.getV();


		String upper ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\r\n" + 
				"<Document>\r\n" + 
				"	<name>1.kml</name>\r\n" + 
				"	<Style id=\"sh_ranger_station\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<scale>0.945455</scale>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/shapes/ranger_station.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<BalloonStyle>\r\n" + 
				"		</BalloonStyle>\r\n" + 
				"		<ListStyle>\r\n" + 
				"		</ListStyle>\r\n" + 
				"	</Style>\r\n" + 
				"	<Style id=\"sn_ranger_station\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<scale>0.8</scale>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/shapes/ranger_station.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<BalloonStyle>\r\n" + 
				"		</BalloonStyle>\r\n" + 
				"		<ListStyle>\r\n" + 
				"		</ListStyle>\r\n" + 
				"	</Style>\r\n" + 
				"	<StyleMap id=\"msn_ranger_station\">\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>normal</key>\r\n" + 
				"			<styleUrl>#sn_ranger_station</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>highlight</key>\r\n" + 
				"			<styleUrl>#sh_ranger_station</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"	</StyleMap>\r\n" + 
				"		<name>ED1.kml</name>\r\n" + 
				"	<StyleMap id=\"m_ylw-pushpin\">\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>normal</key>\r\n" + 
				"			<styleUrl>#s_ylw-pushpin</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>highlight</key>\r\n" + 
				"			<styleUrl>#s_ylw-pushpin_hl</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"	</StyleMap>\r\n" + 
				"	<Style id=\"s_ylw-pushpin_hl\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<scale>1.3</scale>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<LineStyle>\r\n" + 
				"			<color>ff0000ff</color>\r\n" + 
				"			<width>2</width>\r\n" + 
				"		</LineStyle>\r\n" + 
				"	</Style>\r\n" + 
				"	<Style id=\"s_ylw-pushpin\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<scale>1.1</scale>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<LineStyle>\r\n" + 
				"			<color>ff0000ff</color>\r\n" + 
				"			<width>2</width>\r\n" + 
				"		</LineStyle>\r\n" + 
				"	</Style>\r\n" + 
				"	<name>Robot.kml</name>\r\n" + 
				"	<Style id=\"sn_motorcycling\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<color>ff00aaff</color>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/shapes/motorcycling.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<BalloonStyle>\r\n" + 
				"		</BalloonStyle>\r\n" + 
				"		<ListStyle>\r\n" + 
				"		</ListStyle>\r\n" + 
				"	</Style>\r\n" + 
				"	<Style id=\"sh_motorcycling\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<color>ff00aaff</color>\r\n" + 
				"			<scale>1.18182</scale>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/shapes/motorcycling.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<BalloonStyle>\r\n" + 
				"		</BalloonStyle>\r\n" + 
				"		<ListStyle>\r\n" + 
				"		</ListStyle>\r\n" + 
				"	</Style>\r\n" + 
				"	<StyleMap id=\"msn_motorcycling\">\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>normal</key>\r\n" + 
				"			<styleUrl>#sn_motorcycling</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>highlight</key>\r\n" + 
				"			<styleUrl>#sh_motorcycling</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"	</StyleMap>\r\n" ;

		String ans="";
		for (node_data nd : keep_node) {
			String points ="<Placemark>\r\n" + 
					"			<name></name>\r\n" + 
					"			<LookAt>\r\n" + 
					"				<longitude>34.8960629819371</longitude>\r\n" + 
					"				<latitude>31.51045941503297</latitude>\r\n" + 
					"				<altitude>0</altitude>\r\n" + 
					"				<heading>0.04263291216809318</heading>\r\n" + 
					"				<tilt>0</tilt>\r\n" + 
					"				<range>105178.7509273705</range>\r\n" + 
					"				<gx:altitudeMode>relativeToSeaFloor</gx:altitudeMode>\r\n" + 
					"			</LookAt>\r\n" + 
					"			<styleUrl>#msn_ranger_station</styleUrl>\r\n" + 
					"			<Point>\r\n" + 
					"				<gx:drawOrder>1</gx:drawOrder>\r\n" + 
					"				<coordinates>"+nd.getLocation().x()+","+nd.getLocation().y()+",0</coordinates>\r\n" + 
					"			</Point>\r\n" + 
					"		</Placemark>\r\n" + 
					"";
			ans+=points;
		}
		String edge = "";
		for (node_data nd : keep_node) {
			Collection<edge_data> ed_node = grap.getE(nd.getKey());
			for (edge_data ed : ed_node) {
				double x1=grap.getNode(ed.getSrc()).getLocation().x();
				double y1=grap.getNode(ed.getSrc()).getLocation().y();

				double x2=grap.getNode(ed.getDest()).getLocation().x();
				double y2=grap.getNode(ed.getDest()).getLocation().y();
				String edges = "	<Placemark>\r\n" + 
						"		<name>"+ed.getWeight()+"</name>\r\n" + 
						"		<styleUrl>#m_ylw-pushpin</styleUrl>\r\n" + 
						"		<LineString>\r\n" + 
						"			<tessellate>1</tessellate>\r\n" + 
						"			<coordinates>\r\n" + 
						"				"+x1+","+y1+",0 "+x2+","+y2+",0\r\n" + 
						"			</coordinates>\r\n" + 
						"		</LineString>\r\n" + 
						"	</Placemark>\n";
				edge=edge+edges;
			}
		}
		String KML = upper+ans+edge;
		all_kml+=KML;
	}
	/*
	 * this function writes in every given moment all the fruits and robots that exists in the game
	 * the function gets the information using built-in JSON constructors 
	 * and continues to make to file that open kml function started to create
	 */
	public void restart_fr_rb(String timeStart ,String timeEnd) {

		List<myRobot> all_robot = new ArrayList<myRobot>();
		List<myFruit> all_fruit = new ArrayList<myFruit>();
		try {		
			List<String> aweae = game.getRobots();
			for (String string : aweae) 
			{		
				all_robot.add(new myRobot(string));
			}		
			List<String> qweqwe = game.getFruits();
			for (String string : qweqwe) {
				myFruit temp = new myFruit();
				temp.initFromJson(string);
				all_fruit.add(temp);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		String robot = "";
		for (myRobot rb : all_robot) {
			String temp = "<Placemark>\r\n" + 
					"		<name></name>\r\n" + 
					"		<LookAt>\r\n" + 
					"			<longitude>35.20057915651626</longitude>\r\n" + 
					"			<latitude>32.10642421692141</latitude>\r\n" + 
					"			<altitude>0</altitude>\r\n" + 
					"			<heading>0.001400841528527689</heading>\r\n" + 
					"			<tilt>48.82445584719025</tilt>\r\n" + 
					"			<range>2429.727375786245</range>\r\n" + 
					"			<gx:altitudeMode>relativeToSeaFloor</gx:altitudeMode>\r\n" + 
					"		</LookAt>\r\n" + 
					"		<TimeSpan>\r\n" + 
					"            <begin>"+timeStart+"</begin>\r\n" + 
					"            <end>"+timeEnd+"</end>\r\n" + 
					"        </TimeSpan>\r\n" + 
					"		<styleUrl>#msn_motorcycling</styleUrl>\r\n" + 
					"		<Point>\r\n" + 
					"			<gx:drawOrder>1</gx:drawOrder>\r\n" + 
					"			<coordinates>"+rb.getLocation().x()+","+rb.getLocation().y()+",0</coordinates>\r\n" + 
					"		</Point>\r\n" + 
					"	</Placemark>\r\n";
			robot=robot+temp;
		}
		String euro = "<name>ssss.kml</name>\r\n" + 
				"	<StyleMap id=\"msn_euro\">\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>normal</key>\r\n" + 
				"			<styleUrl>#sn_euro</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"		<Pair>\r\n" + 
				"			<key>highlight</key>\r\n" + 
				"			<styleUrl>#sh_euro</styleUrl>\r\n" + 
				"		</Pair>\r\n" + 
				"	</StyleMap>\r\n" + 
				"	<Style id=\"sn_euro\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<scale>1.2</scale>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/shapes/euro.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<BalloonStyle>\r\n" + 
				"		</BalloonStyle>\r\n" + 
				"		<ListStyle>\r\n" + 
				"		</ListStyle>\r\n" + 
				"	</Style>\r\n" + 
				"	<Style id=\"sh_euro\">\r\n" + 
				"		<IconStyle>\r\n" + 
				"			<scale>1.4</scale>\r\n" + 
				"			<Icon>\r\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/shapes/euro.png</href>\r\n" + 
				"			</Icon>\r\n" + 
				"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
				"		</IconStyle>\r\n" + 
				"		<BalloonStyle>\r\n" + 
				"		</BalloonStyle>\r\n" + 
				"		<ListStyle>\r\n" + 
				"		</ListStyle>\r\n" + 
				"	</Style>";

		String fruit = euro ;
		for (myFruit mF : all_fruit) {
			if(mF.getType()==-1) {
				String temp = "<Placemark>\r\n" + 
						"		<name></name>\r\n" + 
						"		<Camera>\r\n" + 
						"			<longitude>45.30783826982074</longitude>\r\n" + 
						"			<latitude>38.61056557598812</latitude>\r\n" + 
						"			<altitude>1575.476434584097</altitude>\r\n" + 
						"			<heading>4.241565502288204</heading>\r\n" + 
						"			<tilt>21.68983571564891</tilt>\r\n" + 
						"			<roll>0.7847198150865365</roll>\r\n" + 
						"			<gx:altitudeMode>relativeToSeaFloor</gx:altitudeMode>\r\n" + 
						"		</Camera>\r\n" + 
						"		<TimeSpan>\r\n" + 
						"            <begin>"+timeStart+"</begin>\r\n" + 
						"            <end>"+timeEnd+"</end>\r\n" + 
						"        </TimeSpan>\r\n" + 
						"		<styleUrl>#msn_euro</styleUrl>\r\n" + 
						"		<Point>\r\n" + 
						"			<gx:drawOrder>1</gx:drawOrder>\r\n" + 
						"			<coordinates>"+mF.getLocation().x()+","+mF.getLocation().y()+",0</coordinates>\r\n" + 
						"		</Point>\r\n" + 
						"	</Placemark>\r\n";
				fruit = fruit + temp;
			}
			else if(mF.getType()==1) {
				String temp = 	"<name>fruit.kml</name>\r\n" + 
						"	<StyleMap id=\"msn_dollar\">\r\n" + 
						"		<Pair>\r\n" + 
						"			<key>normal</key>\r\n" + 
						"			<styleUrl>#sn_dollar</styleUrl>\r\n" + 
						"		</Pair>\r\n" + 
						"		<Pair>\r\n" + 
						"			<key>highlight</key>\r\n" + 
						"			<styleUrl>#sh_dollar</styleUrl>\r\n" + 
						"		</Pair>\r\n" + 
						"	</StyleMap>\r\n" + 
						"	<Style id=\"sn_dollar\">\r\n" + 
						"		<IconStyle>\r\n" + 
						"			<color>ff0000ff</color>\r\n" + 
						"			<scale>1.18182</scale>\r\n" + 
						"			<Icon>\r\n" + 
						"				<href>http://maps.google.com/mapfiles/kml/shapes/dollar.png</href>\r\n" + 
						"			</Icon>\r\n" + 
						"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
						"		</IconStyle>\r\n" + 
						"		<BalloonStyle>\r\n" + 
						"		</BalloonStyle>\r\n" + 
						"		<ListStyle>\r\n" + 
						"		</ListStyle>\r\n" + 
						"	</Style>\r\n" + 
						"	<Style id=\"sh_dollar\">\r\n" + 
						"		<IconStyle>\r\n" + 
						"			<color>ff0000ff</color>\r\n" + 
						"			<scale>1.18182</scale>\r\n" + 
						"			<Icon>\r\n" + 
						"				<href>http://maps.google.com/mapfiles/kml/shapes/dollar.png</href>\r\n" + 
						"			</Icon>\r\n" + 
						"			<hotSpot x=\"0.5\" y=\"0\" xunits=\"fraction\" yunits=\"fraction\"/>\r\n" + 
						"		</IconStyle>\r\n" + 
						"		<BalloonStyle>\r\n" + 
						"		</BalloonStyle>\r\n" + 
						"		<ListStyle>\r\n" + 
						"		</ListStyle>\r\n" + 
						"	</Style>" + 
						"	<Placemark>\r\n" + 
						"		<name></name>\r\n" + 
						"		<Camera>\r\n" + 
						"			<longitude>45.30783826982074</longitude>\r\n" + 
						"			<latitude>38.61056557598812</latitude>\r\n" + 
						"			<altitude>1575.476434584097</altitude>\r\n" + 
						"			<heading>4.241565502288204</heading>\r\n" + 
						"			<tilt>21.68983571564891</tilt>\r\n" + 
						"			<roll>0.7847198150865365</roll>\r\n" + 
						"			<gx:altitudeMode>relativeToSeaFloor</gx:altitudeMode>\r\n" + 
						"		</Camera>\r\n" + 
						"		<TimeSpan>\r\n" + 
						"            <begin>"+timeStart+"</begin>\r\n" + 
						"            <end>"+timeEnd+"</end>\r\n" + 
						"        </TimeSpan>\r\n" + 
						"		<styleUrl>#msn_dollar</styleUrl>" + 
						"		<Point>\r\n" + 
						"			<gx:drawOrder>1</gx:drawOrder>\r\n" + 
						"			<coordinates>"+mF.getLocation().x()+","+mF.getLocation().y()+",0</coordinates>\r\n" + 
						"		</Point>\r\n" + 
						"	</Placemark>\r\n";
				fruit = fruit + temp;
			}
			String KML = robot+fruit;		
			all_kml+=KML;
		}
	}
	/*
	 * the function takes all the gatherd info of our KML future file
	 * and saves it into an NAME.kml file (NAME stands for the current scene of game ex: 0 , 0.kml)
	 * the file can be accessed and viewed via Google earth pro
	 */
	public void saveKML() {
		String bottom = "	</Document>\r\n" + 
				"</kml>";
		all_kml+=bottom;
		try {
			FileWriter f1 = new FileWriter(now.scene+".kml");
			f1.write(all_kml);
			f1.close();

			System.out.println("finished file");
		} catch (RuntimeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getKML() {
		return this.all_kml;
	}
}
