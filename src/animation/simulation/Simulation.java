package animation.simulation;


import java.util.Iterator;

import javafx.scene.layout.TilePane;
import structures.Cell;
import structures.Grid;


public class Simulation {
	
	private AbstractDraw sim;
	private TilePane screen;
	private String SimulationName;
	
	public Simulation() {
		this.sim = new PredatorPreySimulation(); 
		Grid myGrid = new Grid();
		//this.SimulationName = myGrid.getNameOfSimulation();
		this.sim = new FireSimulation(); 
		this.screen = new TilePane();
	}
	
	public TilePane drawGrid(Grid grid, int w, int h) {
		screen.getChildren().clear();
		Iterator<Cell> itr = grid.iterator();
		int width = (int) w / (grid.getColumns());
		int height = (int) h / (grid.getRows());
		screen.setPrefColumns(grid.getColumns());
		while(itr.hasNext()) {
			Cell current = itr.next();
			screen.getChildren().add(sim.fillGrid(current, width, height));
		}
		return screen;
	}
	
	public TilePane getScreen() {
		return screen;
	}
	
}
