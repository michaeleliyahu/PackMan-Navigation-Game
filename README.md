# OOP3 readme
Over the last few weeks we have created a project that deals with Graph algorithms. In the project, we created a game that has several fruits that are eaten by robots(In the shortest route). There are two options of computer game - manual or automatic. The track that exists in the game, based on real codindates from the city of Ariel.

# **Example:**


<a href="https://ibb.co/7YC8XFw"><img src="https://i.ibb.co/tmKk457/ppppppp.png" alt="ppppppp" border="0"></a>

To move the robot, the user clicks on the robot. Then, clicks on the point where the robot will move. The point must be a robot neighbor.

<a href="http://www.siz.co.il/"><img src="http://up419.siz.co.il/up3/tohahq0mtyz0.png" border="0" alt="manual3" /></a>

# Main classes:
1. DGraph (datastructure)

2. Edge (datastructure)

3. GamePar (gameClient)

4. Graph_Algo (algorithms)

5. myFruit (gameClient)

6. myRobot (gameClient)

7. MyGameGUI




# DGraph class: 
This class is an implements represents a directional weighted graph. The class has a road-system or communication network in mind - and should support a large number of nodes (over 100,000).


List of functions:
node_data getNode, edge_data getEdge, addNode, connect, Collection, node_data removeNode, edge_data removeEdge, nodeSize, edgeSize, getMC

 
# GamePar class:
This class is an implements of Game_par class.

List of functions:
GamePar, initFruit, getedges, initRobot, numRobot, fruit_edges, getfruit, getrobot.


# myFruit class:

This class is an implements of Fruits class.

List of functions:
myFruit, setedges, edge_data, getValue, getLocation, getType, toString, initFromJson.



# myRobot class:

This class is an implements of Robots class.

List of functions:
myRobot, getValue, getLocation, getSrc, getId, getDest, getSpeed, toString, setDest, setSrc, setLocation, botFromJSON




# MyGameGUI class: 
In this class, we used stddraw's library. Through this class, we create the gui.

List of functions:
MyGameGUI, initGUI, paint, setTime, result, actionPerformed, mousePressed, setG, setFr, setrb, setFr_Edge



# Graph_Algo class: 

This class is an implements represents the "regular" Graph Theory algorithms.

List of functions:
init,cleanGraf, save, isConnected, shortestPathDist, shortestPath, TSP, copy

Issues:
Function Name: TSP We can't really complete the TSP without checking all the possibilities, but checking them will cost too much time.

# **Results:**


<a href="http://www.siz.co.il/"><img src="http://up419.siz.co.il/up2/zbtugoukzdoo.png" border="0" alt="results" /></a>




