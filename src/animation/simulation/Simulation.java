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
		graph.setStyle("-fx-background-color: #000000;");
		graph.setTileAlignment(Pos.CENTER);
		graph.setPrefColumns(width);
		for (int i = 0; i < width * 5; i++) {
			Cell current = new Cell();
			graph.getChildren().add(sim.fillGrid(current));
		}
		//while(itr.hasNext()) {
			//Cell current = itr.next();
			//graph.getChildren().add(sim.fillGrid(current));
		//}
		return graph;
	}
	
}
