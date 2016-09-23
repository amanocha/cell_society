package animation.simulation;


import java.util.Iterator;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import readxml.Cell;
import readxml.Graph;


public class Simulation {
	
	private Graph myGraph; 
	private Iterator<Cell> itr;
	private AbstractDraw sim;
	
	public Simulation() {
		myGraph = new Graph();
		itr = myGraph.iterator();
		sim = new FireSimulation(); 
	}
	
	public TilePane drawGrid(int width) {
		TilePane graph = new TilePane();
		graph.setTileAlignment(Pos.CENTER);
		graph.setPrefColumns(width);
		while(itr.hasNext()) {
			Cell current = itr.next();
			graph.getChildren().add(sim.fillGrid(current));
		}
		return graph;
	}
	
}
