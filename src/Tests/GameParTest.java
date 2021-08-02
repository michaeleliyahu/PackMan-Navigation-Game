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

class GameParTest {

	@Test
	void testGamePar() throws JSONException {
		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getRobots();
		
		try {
			GamePar now = new GamePar(2, gg);
		} catch (Exception e) {
			// TODO: handle exception
			fail("Not yet implemented");
		}

//		now.initRobot(fr);
//		try {
//			List<myRobot> curr = now.getrobot();
//		} catch (Exception e) {
//			
//		}
	}

	@Test
	void testInitFruit() throws JSONException {
		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getFruits();
		GamePar now = new GamePar(2, gg);
		
		now.initFruit(fr);
		try {
			List<myFruit> curr = now.getfruit();
		} catch (Exception e) {
			fail("Not yet implemented");
		}
		
	}

	@Test
	void testInitRobot() throws JSONException {
		game_service game = Game_Server.getServer(2);
		game.addRobot(0);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getRobots();
		GamePar now = new GamePar(2, gg);
		
		now.initRobot(fr);
		try {
			List<myRobot> curr = now.getrobot();
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

}
