package animation.simulation;


import java.util.Iterator;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import readxml.XmlMapper;
import structures.Cell;
import structures.Grid;

public class SquareSimulation extends Simulation {
	
	private AbstractDraw sim;
	
	public SquareSimulation(XmlMapper info) {
		super();
		sim = info.getMeta().getSimulation();
	}


	public Pane drawGrid(Grid grid, int w, int h) {
		Pane screen = setUpScreen(grid, w, h);
		int width = (int) w / (grid.getColumns());
		int height = (int) h / (grid.getRows());
		((TilePane) screen).setPrefColumns(grid.getColumns());
		((TilePane) screen).setTileAlignment(Pos.CENTER);
		((TilePane) screen).setHgap(2);
		((TilePane) screen).setVgap(2);
		Iterator<Cell> itr = grid.iterator();
		while(itr.hasNext()) {
			Cell current = itr.next();
			screen.getChildren().add(sim.fillGrid(current, width, height));
		}
		return screen;
	}
	
}
