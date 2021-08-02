
package gui;

import utils.*;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Server.Fruit;
import algorithms_ex3.*;
import dataStructure_ex3.DGraph;
import dataStructure_ex3.edge_data;
import dataStructure_ex3.graph;
import dataStructure_ex3.node_data;
import gameClient.myFruit;
import gameClient.myRobot;
import dataStructure_ex3.*;



public class MyGameGUI implements ActionListener, MouseListener,Serializable
{
	graph grap;
	LinkedList<Point3D> points = new LinkedList<Point3D>();
	ArrayList<node_data> SP= new ArrayList<node_data>();
	List<myFruit> fruits = new ArrayList<myFruit>();
	List<myRobot> robot = new ArrayList<myRobot>();
	List<edge_data> edge_fruit = new ArrayList<edge_data>();

	public double minx = Integer.MAX_VALUE;
	public double miny = Integer.MAX_VALUE;
	public double maxx = Integer.MIN_VALUE;
	public double maxy = Integer.MIN_VALUE;
	long temp1;   // time of game
	int temp2;    // result of the game
	 

	public MyGameGUI()
	{
		grap = null;
		initGUI();
	}
	public MyGameGUI(graph g )
	{
		this.grap = g;
		initGUI();
	}
	public void initGUI()
	{   
		if(grap!=null)
		{
		StdDraw.enableDoubleBuffering();
		minx = Integer.MAX_VALUE;
		miny = Integer.MAX_VALUE;
		maxx = Integer.MIN_VALUE;
		maxy = Integer.MIN_VALUE;
		}
		else
		{
			minx = 0;
			miny = 0;
			maxx = 800;
			maxy = 600;
		}
		if(!StdDraw.getAllready())
		{
			StdDraw.setCanvasSize(800, 600);
			StdDraw.changeDone();
		}
		StdDraw.setGUI(this);

		if(grap != null)
		{
			Collection<node_data> nd = grap.getV();
			for (node_data node_data : nd) {
				Point3D pos = node_data.getLocation();
				if(pos.x() > maxx)
				{
					maxx= pos.x();
				}
				if(pos.x() < minx)
				{
					minx = pos.x();
				}
				if(pos.y() < miny)
				{
					miny = pos.y();
				}
				if(pos.y() > maxy)
				{
					maxy = pos.y();
				}
			}					
		}
		StdDraw.setXscale(minx, maxx);
		StdDraw.setYscale(miny, maxy);

		paint();
		//		minx = Integer.MAX_VALUE;
		//	    miny = Integer.MAX_VALUE;
		//	    maxx = Integer.MIN_VALUE;
		//	    maxy = Integer.MIN_VALUE;
		//		StdDraw.show();	
	}
	public void paint()
	{
		StdDraw.clear();
		if (this.grap!=null) 
		{
			for(node_data node : this.grap.getV())
			{
				StdDraw.setPenColor(Color.BLACK);
                /////////////////////////////////////show the time of the game////////////////////////
		        StdDraw.text(minx+0.001,miny+0.0001, "Time to end :"+(this.temp1/1000));
                //////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////show the result of the game////////////////////////////
		        StdDraw.text(minx+0.0004,miny-0.0002, "Result :"+(this.temp2));
                ///////////////////////////////////////////////////////////////////////////////////////////

				StdDraw.setPenColor(Color.RED);
				StdDraw.filledCircle(node.getLocation().x(), node.getLocation().y(), (maxx-minx)*0.005);
				Collection<edge_data> edd = this.grap.getE(node.getKey());
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.text(node.getLocation().x(),node.getLocation().y()+(maxy-miny)*0.03, ""+node.getKey());
				for (edge_data ed : edd)
				{
					if(ed.getTag()==2)
					{
						StdDraw.setPenColor(Color.GREEN);
						ed.setTag(0);
					}
					else
					{
						StdDraw.setPenColor(Color.BLUE);
					}					
					StdDraw.line(node.getLocation().x(), node.getLocation().y(), this.grap.getNode(ed.getDest()).getLocation().x(), this.grap.getNode(ed.getDest()).getLocation().y());
					double x1 = node.getLocation().x();
					double y1 = node.getLocation().y();
					double x2 = this.grap.getNode(ed.getDest()).getLocation().x();
					double y2 = this.grap.getNode(ed.getDest()).getLocation().y();
					double yyyyy = (((node.getLocation().y()+this.grap.getNode(ed.getDest()).getLocation().y())/2)+this.grap.getNode(ed.getDest()).getLocation().y())/2;
					double xxxxx = (((node.getLocation().x()+this.grap.getNode(ed.getDest()).getLocation().x())/2)+this.grap.getNode(ed.getDest()).getLocation().x())/2;
					StdDraw.setPenColor(Color.YELLOW);
					StdDraw.filledCircle((xxxxx+this.grap.getNode(ed.getDest()).getLocation().x())/2, (yyyyy+this.grap.getNode(ed.getDest()).getLocation().y())/2, (maxx-minx)*0.004);
					StdDraw.setPenColor(Color.BLACK);

					double ans = Math.floor(ed.getWeight()*100)/100;
					StdDraw.text((xxxxx+this.grap.getNode(ed.getDest()).getLocation().x())/2,(yyyyy+this.grap.getNode(ed.getDest()).getLocation().y())/2+(maxy-miny)*0.03,""+ans);
				}
			}
		
			
			

			if(fruits.size()!=0) 
			{
				for (int i = 0; i < fruits.size(); i++) {    // banana
					if(fruits.get(i).getType() == -1) {
						StdDraw.picture(fruits.get(i).getLocation().x(), fruits.get(i).getLocation().y(), "banana.PNG",(maxx-minx)*0.02,(maxx-minx)*0.02);
					}
					else if(fruits.get(i).getType() == 1){   // apple 
						StdDraw.picture(fruits.get(i).getLocation().x(), fruits.get(i).getLocation().y(), "apple.PNG",(maxx-minx)*0.02,(maxx-minx)*0.02);
					}
				}
			}
			if(robot.size()!=0) {
				for (int i = 0; i < robot.size(); i++)
				{
				StdDraw.setPenColor(Color.black);
				StdDraw.picture(robot.get(i).getLocation().x(), robot.get(i).getLocation().y(), "robot.PNG",(maxx-minx)*0.02,(maxx-minx)*0.02);
				}
				}				
		}

		StdDraw.show();
	}
    public void setTime(long x) 
    { 
        this.temp1 = x;   
    }
//    public void result(String y) 
//    { 
//         
//    	
//    }
	public void result (String info) {

		try {
		String temp = info.substring(1);
		int y = temp.indexOf("{");
		info = info.substring(0, y)+":[{"+info.substring(y+2, info.length()-2)+"}]}";
		JSONObject jsonObject = new JSONObject(info);
		JSONArray json_fruit =jsonObject.getJSONArray("GameServer");
		JSONObject empObj = new JSONObject();
		empObj = json_fruit.getJSONObject(0);
		this.temp2 = (int) empObj.get("grade");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String str = e.getActionCommand();
		if(str.equals("manuel"))
		{
			System.out.println("avi");
		}

		////Shortest path distance///
		if(str.equals("Shortest path dist"))
		{

			Graph_Algo temp = new Graph_Algo();
			temp.init(grap);
			JFrame jinput = new JFrame();
			double x =0;
			String start = JOptionPane.showInputDialog(jinput,"Start point");
			String finish = JOptionPane.showInputDialog(jinput,"Finish point");
			try
			{
				int src = Integer.parseInt(start);
				int des = Integer.parseInt(finish);

				x = temp.shortestPathDist(src, des);
				if(x!=-1)
				{
					JOptionPane.showMessageDialog(jinput, "The shortest distance is: " + x);
				}
				else
				{
					JOptionPane.showMessageDialog(jinput, "char not valid ");
				}
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}

			paint();

		}

		//"Shortest path - draws the path"
		if(str.equals("Shortest path"))
		{

			java.util.List<node_data> nodes = new ArrayList<node_data>();

			Graph_Algo temp = new Graph_Algo();
			temp.init(grap);

			JFrame jinput = new JFrame();
			String start = JOptionPane.showInputDialog(jinput,"Start point");
			String finish = JOptionPane.showInputDialog(jinput,"Finish point");
			try
			{
				int src = Integer.parseInt(start);
				int des = Integer.parseInt(finish);
				nodes = temp.shortestPath(src, des);

			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
			if(nodes.size()!=0)
			{
				for (int i = nodes.size()-1; i >= 0; i--) {
					SP.add(nodes.get(i));
				}

				for (int i = 0; i < SP.size()-1; i++) {
					this.grap.getEdge(SP.get(i).getKey(), SP.get(i+1).getKey()).setTag(2);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(jinput, "char not valid ");
			}

			paint();
			SP = new ArrayList<node_data>();
		}

		//"Is connected"
		if(str.equals("Is connected"))
		{
			JFrame jinput = new JFrame();
			if (this.grap!=null) {

				Graph_Algo temp = new Graph_Algo();
				temp.init(grap);
				boolean ans = temp.isConnected();

				try
				{
					Graph_Algo ga = new Graph_Algo();
					ga.init(grap);
					JOptionPane.showMessageDialog(jinput, "Is connected? "+ans );
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
				paint();
			}
			else
			{
				JOptionPane.showMessageDialog(jinput, "the graph is empty ");
			}
		}

		////save///
		if(str.equals("save"))
		{
			///////////////// TO DO FIX ????????????????
			//			StdDraw.save("filename");
			//			Graph_Algo temp = new Graph_Algo();
			//			temp.init(grap);
			//
			//			FileDialog chooser = new FileDialog(Graph_GUI.frame, "Use a .txt extension", FileDialog.SAVE);
			//			chooser.setVisible(true);
			//			String filename = chooser.getFile();
			//			if (filename != null)
			//			{
			//				temp.save(chooser.getDirectory()+filename+".txt");
			//			}
		}

		////load///
		if(str.equals("load"))
		{
			Graph_Algo temp = new Graph_Algo();
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			JFrame frame = new JFrame("JComboBox Test");
			frame.setLayout(new FlowLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();

				temp.init(selectedFile.getPath());
				grap = temp.copy();
				paint();
			}
			frame.pack();
		}

		//test1
		if(str.equals("test1"))
		{
			Point3D a = new Point3D(25,90,0);
			Point3D b = new Point3D(340,300,0);
			Point3D c = new Point3D(24,300,0);
			Point3D d = new Point3D(68,100,0);
			Point3D f = new Point3D(500,600,0);
			//Point3D h = new Point3D(300,550,0);

			node_data v1 = new Node(1, a);
			node_data v2 = new Node(2, b);
			node_data v3 = new Node(3, c);
			node_data v4 = new Node(4, d);
			node_data v5 = new Node(5, f);
			//node_data v8 = new Node(8, h);

			graph grap = new DGraph();

			grap.addNode(v1);
			grap.addNode(v2);
			grap.addNode(v3);
			grap.addNode(v4);
			grap.addNode(v5);

			grap.connect(v1.getKey(), v3.getKey(), 6);
			grap.connect(v1.getKey(), v4.getKey(), 10);
			grap.connect(v3.getKey(), v4.getKey(), 9);
			grap.connect(v4.getKey(), v2.getKey(), 3);
			grap.connect(v3.getKey(), v5.getKey(), 1);
			grap.connect(v1.getKey(), v3.getKey(), 5);
			grap.connect(v2.getKey(), v3.getKey(), 5);
			grap.connect(v2.getKey(), v5.getKey(), 1);
			// Graph_GUI window = new Graph_GUI(grap);
			//
			//
			// window.setVisible(true);
			this.grap=grap;
			paint();
		}
		if(str.equals("test2"))
		{
			Point3D a = new Point3D(25,90,0);
			Point3D b = new Point3D(100,300,0);
			Point3D c = new Point3D(190,200,0);
			Point3D d = new Point3D(390,400,0);
			Point3D h = new Point3D(500,600,0);
			Point3D f = new Point3D(300,550,0);
			Point3D g = new Point3D(300,450,0);
			//Point3D h = new Point3D(300,550,0);

			node_data v1 = new Node(1, a);
			node_data v2 = new Node(2, b);
			node_data v3 = new Node(3, c);
			node_data v4 = new Node(4, d);
			node_data v5 = new Node(5, h);
			node_data v6 = new Node(6, f);
			node_data v7 = new Node(7, g);
			//node_data v8 = new Node(8, h);

			graph grap = new DGraph();

			grap.addNode(v1);
			grap.addNode(v2);
			grap.addNode(v3);
			grap.addNode(v4);
			grap.addNode(v5);
			grap.addNode(v6);
			grap.addNode(v7);
			// grap.addNode(v8);
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

			this.grap=grap;
			// Graph_GUI window = new Graph_GUI(grap);
			// window.setVisible(true);
			paint();

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		Point3D p = new Point3D(x,y);
		points.add(p);

	}
	public void setG(graph g)
	{
		this.grap = g;


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouseReleased");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("mouseExited");
	}
	public void setFr(List<myFruit> other) {

		fruits=other;
	}
	public void setrb(List<myRobot> other) {

		robot=other;
	}
	public void setFr_Edge(List<edge_data> other) {

		edge_fruit=other;
	}

}
