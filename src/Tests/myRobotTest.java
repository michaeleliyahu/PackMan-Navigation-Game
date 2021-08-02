package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import Server.Game_Server;
import Server.game_service;
import dataStructure_ex3.DGraph;
import gameClient.GamePar;
import gameClient.myFruit;
import gameClient.myRobot;

class myRobotTest {

	@Test
	void testMyRobot() throws JSONException {

		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getRobots();
		GamePar now = new GamePar(2, gg);

		now.initRobot(fr);;

		//		System.out.println(fr);
		List<myRobot> curr = now.getrobot();
		for (myRobot myRB : curr) {
			if(myRB.getDest()!=-1) {
				fail("Not yet implemented");
			}
			else {

			}
		}
		//		
	}

	@Test
	void testMyRobotString() throws JSONException {
		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getRobots();
		GamePar now = new GamePar(2, gg);

		now.initRobot(fr);;

		//		System.out.println(fr);
		List<myRobot> curr = now.getrobot();
		for (myRobot myRB : curr) {
			try {
				myRB.toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Test
	void testGetValue() throws JSONException {
		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getRobots();
		GamePar now = new GamePar(2, gg);

		now.initRobot(fr);;

		//		System.out.println(fr);
		List<myRobot> curr = now.getrobot();
		for (myRobot myRB : curr) {
			if(myRB.getValue()==0)
			{

			}
			else {
				fail("Not yet implemented");
			}
		}
	}

	@Test
	void testGetSrc() throws JSONException {
		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getRobots();
		GamePar now = new GamePar(2, gg);

		now.initRobot(fr);;

		//		System.out.println(fr);
		List<myRobot> curr = now.getrobot();
		for (myRobot myRB : curr) {
			if(myRB.getSrc()==0)
			{

			}
			else {
				fail("Not yet implemented");
			}
		}
	}

	@Test
	void testGetSpeed() throws JSONException {
		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getRobots();
		GamePar now = new GamePar(2, gg);

		now.initRobot(fr);;

		//		System.out.println(fr);
		List<myRobot> curr = now.getrobot();
		for (myRobot myRB : curr) {
			if(myRB.getSpeed()==1)
			{
				
			}
			else {
				fail("Not yet implemented");
			}
		}
	}

}
