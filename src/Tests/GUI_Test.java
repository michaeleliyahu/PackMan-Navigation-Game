
package Tests;
import dataStructure_ex3.DGraph;
import dataStructure_ex3.graph;
import dataStructure_ex3.node_data;
import dataStructure_ex3.*;
import gui.MyGameGUI;
import gui.Graph_GUI;
import utils.Point3D;

public class GUI_Test {

	public static void main(String[] args) {
		//Window window = new Window();
		//window.setVisible(true);
		Point3D a = new Point3D(25,90,0);
		Point3D b = new Point3D(100,300,0);
		Point3D c = new Point3D(190,200,0);
		Point3D d = new Point3D(390,400,0);
		Point3D e = new Point3D(500,600,0);
		Point3D f = new Point3D(300,550,0);
		Point3D g = new Point3D(300,450,0);
		//Point3D h = new Point3D(300,550,0);

		node_data v1 = new Node(1, a);
		node_data v2 = new Node(2, b);
		node_data v3 = new Node(3, c);
		node_data v4 = new Node(4, d);
		node_data v5 = new Node(5, e);
		node_data v6 = new Node(6, f);
		node_data v7 = new Node(7, g);
		//node_data v8 = new Node(8, h);
//		
		graph grap = new DGraph();
		// graph_algorithms test = new Graph_Algo();
		// test.init(grap);
		grap.addNode(v1);
		grap.addNode(v2);
		grap.addNode(v3);
		grap.addNode(v4);
		grap.addNode(v5);
		grap.addNode(v6);
		grap.addNode(v7);
	//	grap.addNode(v8);


		grap.connect(v1.getKey(), v2.getKey(), 6);
		grap.connect(v2.getKey(), v3.getKey(), 10);
		grap.connect(v3.getKey(), v4.getKey(), 9);
		grap.connect(v4.getKey(), v5.getKey(), 3);
		grap.connect(v5.getKey(), v1.getKey(), 1);
		grap.connect(v1.getKey(), v3.getKey(), 5);
		grap.connect(v3.getKey(), v5.getKey(), 6);
		grap.connect(v2.getKey(), v4.getKey(), 1);
		grap.connect(v2.getKey(), v6.getKey(), 4);
		grap.connect(v4.getKey(), v7.getKey(), 3);
		grap.connect(v7.getKey(), v6.getKey(), 1);
		grap.connect(v7.getKey(), v5.getKey(), 6);
		grap.connect(v2.getKey(), v7.getKey(), 1);
		grap.connect(v6.getKey(), v5.getKey(), 1);
		grap.connect(v4.getKey(), v6.getKey(), 3);
		// Window window = new Window();
		MyGameGUI window = new MyGameGUI(grap);


	//	window.setVisible(true);


	}

}
