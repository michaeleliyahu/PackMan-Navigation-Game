package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms_ex3.Graph_Algo;
import algorithms_ex3.graph_algorithms;
import dataStructure_ex3.DGraph;
import dataStructure_ex3.graph;
import dataStructure_ex3.node_data;
import dataStructure_ex3.*;
import utils.Point3D;

class Graph_AlgoTest {

	@Test
	void testSave() {
		//saves file
		Point3D p1 = new Point3D(0,0);
		Point3D p2 = new Point3D(1,1);

		node_data v1 = new Node(1, p1);		
		node_data v2 = new Node(2, p2);	

		graph g = new DGraph();

		g.addNode(v1);
		g.addNode(v2);

		g.connect(v1.getKey(), v2.getKey(), 5);

		graph_algorithms test = new Graph_Algo();

		test.init(g);

		try {
			test.save("test_graph");
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

	@Test
	void testInitGraph() {


		Point3D p1 = new Point3D(0,0);
		graph g = new DGraph();
		for (int i = 1; i <= 1000000 ; i++) {
			node_data t = new Node( i, new Point3D(p1.x()+i , p1.y()+i ,p1.z()+i ));
			g.addNode(t);

		}

		for (int i = 1; i <= 1000000-10 ; i++) {
			g.connect(i, i+1, i*0.5);
			g.connect(i, i+2, i*0.3);
			g.connect(i, i+3, 1);
			g.connect(i, i+4, i*10);
			g.connect(i, i+5, i*3);
			g.connect(i, i+6, i*0.8);
			g.connect(i, i+7, i*0.5);
			g.connect(i, i+8, i*7);
			g.connect(i, i+9, i*3);
			g.connect(i, i+10, i*2.5);
		}

		graph_algorithms test = new Graph_Algo();

		try {
			test.init(g);
		} catch (Exception e) {
			// TODO: handle exception
			fail("cant init graph");
		}
	}

	@Test
	void testCopy() {

		Point3D p1 = new Point3D(0,0);
		Point3D p2 = new Point3D(0,0);
		Point3D p3 = new Point3D(0,0);
		Point3D p4 = new Point3D(0,0);

		graph g = new DGraph();

		node_data v1 = new Node(1, p1);		
		node_data v2 = new Node(2, p2);	
		node_data v3 = new Node(3, p1);		
		node_data v4 = new Node(4, p2);	

		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);

		g.connect(1, 2, 3);
		g.connect(2, 3, 3);
		g.connect(3, 4, 3);
		g.connect(4, 1, 3);

		graph_algorithms t = new Graph_Algo();
		t.init(g);

		graph other = t.copy();
		assertEquals(g.edgeSize(), other.edgeSize());

	}

	@Test
	void testInitString() {
		//loads a graph 
		//from file
		graph_algorithms test = new Graph_Algo();

		try {
			test.init("test_graph");
		} catch (Exception e) {
			fail("Not yet implemented");
		}

	}

	@Test
	void testIsConnected() {

		Point3D p1 = new Point3D(0,0);
		Point3D p2 = new Point3D(1,1);
		Point3D p3 = new Point3D(2,2);
		Point3D p4 = new Point3D(3,3);
		Point3D p5 = new Point3D(4,4);
		Point3D p6 = new Point3D(5,5);
		Point3D p7 = new Point3D(6,6);


		graph s = new DGraph();

		s.addNode(new Node(1,p1));
		s.addNode(new Node(2,p2));
		s.addNode(new Node(3,p3));
		s.addNode(new Node(4,p4));
		s.addNode(new Node(5,p5));
		s.addNode(new Node(6,p6));
		s.addNode(new Node(7,p7));
		s.connect(1, 2, 0);
		s.connect(1, 3, 0);
		s.connect(2, 4, 0);
		s.connect(2, 5, 0);
		s.connect(3, 2, 0);
		s.connect(4, 6, 0);
		s.connect(4, 5, 0);
		s.connect(5, 6, 0);
		s.connect(5, 7, 0);
		s.connect(6, 7, 0);
		s.connect(7, 1, 0);

		Graph_Algo e = new Graph_Algo();
		e.init(s);
		assertEquals(true, e.isConnected());

		graph g2 = new DGraph();
		g2.addNode(new Node(1,p1));
		g2.addNode(new Node(2,p1));
		g2.addNode(new Node(3,p1));
		g2.addNode(new Node(4,p1));
		g2.addNode(new Node(5,p1));
		g2.connect(1, 5, 30);
		g2.connect(5, 1, 30);
		g2.connect(2, 5, 30);
		g2.connect(5, 2, 30);
		g2.connect(3, 5, 30);
		g2.connect(5, 3, 30);
		g2.connect(4, 5, 30);
		g2.connect(5, 4, 30);
		Graph_Algo ga2 = new Graph_Algo();
		ga2.init(g2);
		assertEquals(true, ga2.isConnected());

		graph g3 = new DGraph();
		g3.addNode(new Node(1,p1));
		g3.addNode(new Node(2,p1));
		g3.addNode(new Node(3,p1));
		g3.addNode(new Node(4,p1));
		g3.addNode(new Node(5,p1));
		g3.connect(1, 2, 30);
		g3.connect(2, 3, 30);
		g3.connect(3, 4, 30);
		g3.connect(4, 5, 30);
		Graph_Algo ga3 = new Graph_Algo();
		ga3.init(g3);
		assertEquals(false, ga3.isConnected());

	}

	@Test
	void testShortestPathDist() {
		Point3D p1 = new Point3D(0,0);
		Point3D p2 = new Point3D(1,1);
		Point3D p3 = new Point3D(2,2);
		Point3D p4 = new Point3D(3,3);
		Point3D p5 = new Point3D(4,4);
		Point3D p6 = new Point3D(5,5);
		Point3D p7 = new Point3D(6,6);
		Point3D p8 = new Point3D(7,5);
		Point3D p9 = new Point3D(6,3);
		Point3D p10 = new Point3D(7,7);

		node_data v1 = new Node(1, p1);		
		node_data v2 = new Node(2, p2);	
		node_data v3 = new Node(3, p3);
		node_data v4 = new Node(4, p4);		
		node_data v5 = new Node(5, p5);	
		node_data v6 = new Node(6, p6);
		node_data v7 = new Node(7, p7);
		node_data v8 = new Node(8, p8);	
		node_data v9 = new Node(9, p9);	
		node_data v10 = new Node(10, p10);

		graph g = new DGraph();


		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
		g.addNode(v5);
		g.addNode(v6);
		g.addNode(v7);
		g.addNode(v8);
		g.addNode(v9);
		g.addNode(v10);

		g.connect(v1.getKey(), v2.getKey(), 2);
		g.connect(v1.getKey(), v3.getKey(), 3);
		g.connect(v2.getKey(), v4.getKey(), 11);
		g.connect(v3.getKey(), v4.getKey(), 7);
		g.connect(v1.getKey(), v5.getKey(), 2);
		g.connect(v4.getKey(), v5.getKey(), 1);
		g.connect(v2.getKey(), v6.getKey(), 9);
		g.connect(v1.getKey(), v6.getKey(), 11);
		//		g.connect(v6.getKey(), v9.getKey(), 5);

		graph_algorithms test = new Graph_Algo();
		test.init(g);
		assertEquals(2.0,test.shortestPathDist(1,2));
		//	System.out.println(test.shortestPathDist(1,4));
		assertEquals(10.0,test.shortestPathDist(1,4));
		assertEquals(2.0,test.shortestPathDist(1,5));
		assertEquals(11.0,test.shortestPathDist(1,6));
		//	System.out.println(test.shortestPathDist(8,7));
		//assertEquals(2.0,test.shortestPathDist(1,1));
		//		System.out.println(test.shortestPathDist(1,2));
		//		graph_algorithms test = new Graph_Algo();
		//		test.init(g);
		//		assertEquals(2.0,test.shortestPathDist(1,2));
		//		System.out.println(test.shortestPathDist(1,4));
		//		assertEquals(10.0,test.shortestPathDist(1,4));
	}

	@Test
	void testShortestPath() {
		Point3D p1 = new Point3D(0,0);
		Point3D p2 = new Point3D(1,1);
		Point3D p3 = new Point3D(2,2);
		Point3D p4 = new Point3D(3,3);
		Point3D p5 = new Point3D(4,4);
		Point3D p6 = new Point3D(5,5);
		Point3D p7 = new Point3D(6,6);
		Point3D p8 = new Point3D(7,5);
		Point3D p9 = new Point3D(6,3);
		Point3D p10 = new Point3D(7,7);

		node_data v1 = new Node(1, p1);		
		node_data v2 = new Node(2, p2);	
		node_data v3 = new Node(3, p3);
		node_data v4 = new Node(4, p4);		
		node_data v5 = new Node(5, p5);	
		node_data v6 = new Node(6, p6);
		node_data v7 = new Node(7, p7);
		node_data v8 = new Node(8, p8);	
		node_data v9 = new Node(9, p9);	
		node_data v10 = new Node(10, p10);

		graph g = new DGraph();


		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
		g.addNode(v5);
		g.addNode(v6);
		g.addNode(v7);
		g.addNode(v8);
		g.addNode(v9);
		g.addNode(v10);

		g.connect(v1.getKey(), v2.getKey(), 2);
		g.connect(v1.getKey(), v3.getKey(), 3);
		g.connect(v2.getKey(), v4.getKey(), 11);
		g.connect(v3.getKey(), v4.getKey(), 7);
		g.connect(v1.getKey(), v5.getKey(), 2);
		g.connect(v4.getKey(), v5.getKey(), 1);
		g.connect(v2.getKey(), v6.getKey(), 9);
		g.connect(v1.getKey(), v6.getKey(), 11);
		//			g.connect(v6.getKey(), v9.getKey(), 5);

		graph_algorithms test = new Graph_Algo();
		test.init(g);
		List<node_data> arr = (test.shortestPath(1, 4));
		String s = "";
		for (int i = arr.size()-1 ; i >= 0; i--) { 
			if (i==0) {
				s=s+arr.get(i).getKey()+"";
				//System.out.print(arr.get(i).getKey());	
			}
			else {
				s=s+arr.get(i).getKey()+"->";
				//	System.out.print(arr.get(i).getKey()+"->");
			}
		}
		//System.out.println("\n"+s);
		assertEquals("1->3->4",s);

		List<node_data> arr2 = (test.shortestPath(1, 6));
		String s2 = "";
		for (int i = arr2.size()-1 ; i >= 0; i--) { 
			if (i==0) {
				s2=s2+arr2.get(i).getKey()+"";
				//			System.out.print(arr.get(i).getKey());	
			}
			else {
				s2=s2+arr2.get(i).getKey()+"->";
				//			System.out.print(arr.get(i).getKey()+"->");
			}
		}
		//	System.out.println("\n"+s2);
		assertEquals("1->2->6",s2);
		//	assertEquals(11.0,test.shortestPathDist(1,6));
		//	System.out.println(test.shortestPathDist(8,7));		
	}

	@Test
	void testTSP() {

		Point3D p1 = new Point3D(0,0);
		Point3D p2 = new Point3D(1,1);
		Point3D p3 = new Point3D(0,0);
		Point3D p4 = new Point3D(1,1);
		Point3D p5 = new Point3D(1,1);

		node_data v1 = new Node(1, p1);		
		node_data v2 = new Node(2, p2);	
		node_data v3 = new Node(3, p3);
		node_data v4 = new Node(4, p4);
		node_data v5 = new Node(5, p5);

		graph g = new DGraph();

		g.addNode(v1);
		g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);
		g.addNode(v5);

		g.connect(v1.getKey(), v2.getKey(), 5);
		g.connect(v2.getKey(), v3.getKey(), 5);
		g.connect(v3.getKey(), v4.getKey(), 5);
		g.connect(v4.getKey(), v5.getKey(), 5);
		g.connect(v5.getKey(), v1.getKey(), 5);

		graph_algorithms test = new Graph_Algo();

		test.init(g);

		List<Integer> keys = new ArrayList<Integer>();
		keys.add(1);
		keys.add(5);
		keys.add(4);
		//			List<node_data> ans =  new ArrayList<node_data>();

		List<node_data> ans = test.TSP(keys);
		String expected ="";
		for (int i = 0; i < ans.size() ; i++) {
			if(i==ans.size()-1) {
				expected += ans.get(i).getKey();
			}else {
				expected += ans.get(i).getKey()+">";	
			}
		}
		assertEquals("1>2>3>4>5>1>2>3>4", expected);
	}

}
