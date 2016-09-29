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
		int dimension = (int) w / (grid.getColumns());
		int wcount = grid.getColumns();
		int vcount = grid.getRows();
		int row = 1;
		while(itr.hasNext()) {
			Cell current = itr.next();
			screen.getChildren().add(sim.fillGridTriangle(current, w, h, wcount, vcount, dimension, row));
			wcount--;
			if (wcount % grid.getColumns() == 0) {
				wcount = grid.getColumns();
				row++;
				if (row % 2 != 0) {
					vcount--;
				}
			}
		}
		return screen;
	}

}
