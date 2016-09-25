package animation.simulation;


import java.util.Iterator;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import structures.Cell;
import structures.Grid;
import javafx.scene.shape.*;


public class Simulation {
	
	private AbstractDraw sim;
	
	public Simulation() {
		sim = new FireSimulation(); 
	}
	
	public TilePane drawGrid(Grid grid) {
		Iterator<Cell> itr = grid.iterator();
		TilePane graph = new TilePane();
		graph.setStyle("-fx-background-color: #000000;");
		graph.setTileAlignment(Pos.CENTER);
		graph.setPrefColumns(grid.getColumns());
		graph.setMinHeight(100);
		graph.setMinWidth(100);
		graph.setLayoutX(100);
		graph.setLayoutY(100);
		while(itr.hasNext()) {
			Cell current = itr.next();
			//graph.getChildren().add(sim.fillGrid(current));
			graph.getChildren().add(new Rectangle(0, 0, Color.GREEN));
		}
		return graph;
	}
	
}
