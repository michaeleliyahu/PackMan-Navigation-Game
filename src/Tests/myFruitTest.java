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

class myFruitTest {

	@Test
	void testMyFruit() throws JSONException {

		game_service game = Game_Server.getServer(2);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getFruits();
		GamePar now = new GamePar(2, gg);

		now.initFruit(fr);

//		System.out.println(fr);
		List<myFruit> curr = now.getfruit();
		for (myFruit myFruit : curr) {
			if(myFruit.getType()==1 || myFruit.getType()==-1) {

			}
			else {
				fail("bad fruit");
			}
		}
	}

	@Test
	void testGetsrc() throws JSONException {
		game_service game = Game_Server.getServer(2);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getFruits();
		GamePar now = new GamePar(2, gg);

		now.initFruit(fr);
		List<myFruit> curr = now.getfruit();
		for (myFruit myFruit : curr) {

			if(myFruit.getsrc().getSrc()==9 || myFruit.getsrc().getSrc()==4 || myFruit.getsrc().getSrc()==3) {

			}
			else {
				fail("bad fruit");
			}
		}
	}

	@Test
	void testGetValue() throws JSONException {
		game_service game = Game_Server.getServer(2);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getFruits();
		GamePar now = new GamePar(2, gg);

		now.initFruit(fr);

//		System.out.println(fr);
		List<myFruit> curr = now.getfruit();
		for (myFruit myFruit : curr) {
			if(myFruit.getValue()==5 ||myFruit.getValue()==8 || myFruit.getValue()==13 ) {

			}
			else {
				fail("bad fruit");
			}
		}
	}

	@Test
	void testToString() throws JSONException {

		game_service game = Game_Server.getServer(2);
		DGraph gg = new DGraph();
		String g = game.getGraph();
		gg.init(g);
		List<String> fr = game.getFruits();
		GamePar now = new GamePar(2, gg);

		now.initFruit(fr);

//		System.out.println(fr);
		List<myFruit> curr = now.getfruit();
		
		try {
			for (myFruit myFruit : curr) {
				myFruit.toString();
			}
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	
	}

}
