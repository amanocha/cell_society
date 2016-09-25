package animation.simulation;


import java.util.Iterator;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import structures.Cell;
import structures.Grid;


public class Simulation {
	
	private AbstractDraw sim;
	private TilePane screen;
	
	public Simulation() {
		this.sim = new FireSimulation(); 
		this.screen = new TilePane();
	}
	
	public TilePane drawGrid(Grid grid, int w, int h) {
		//System.out.println(grid.getCellList().get(5).getCurrentState());
		screen.getChildren().clear();
		Iterator<Cell> itr = grid.iterator();
		screen.setTileAlignment(Pos.CENTER);
		int width = (int) w / (grid.getColumns());
		int height = (int) h / (grid.getRows());
		screen.setPrefColumns(grid.getColumns());
		screen.setMaxWidth(w);
		screen.setMaxHeight(h);
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
