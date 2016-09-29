package animation.simulation;

import java.util.Iterator;

import javafx.scene.layout.Pane;
import readxml.XmlMapper;
import structures.Cell;
import structures.Grid;

public class TriangleSimulation extends Simulation {
	
	private AbstractDraw sim;
	
	public TriangleSimulation(XmlMapper info) {
		super();
		sim = info.getMeta().getSimulation();
	}
	

	public Pane drawGrid(Grid grid, int w, int h) {
		Pane screen = setUpScreen(grid, w, h);
		Iterator<Cell> itr = grid.iterator();
		int width = (int) w / (grid.getColumns());
		int height = (int) h / (grid.getRows());
		int wcount = grid.getColumns();
		int vcount = grid.getRows();
		while(itr.hasNext()) {
			Cell current = itr.next();
			screen.getChildren().add(sim.fillGridTriangle(current, w, h, wcount, vcount, width, height));
			wcount--;
			if (wcount % grid.getColumns() == 0) {
				vcount--;
				wcount = grid.getColumns();
			}
		}
		return screen;
	}

}
