
package animation.simulation.shape;


import java.util.Iterator;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import structures.Cell;
import structures.Grid;
import structures.MetaData;

public class TriangleGrid extends GridShape {
	
	
	public TriangleGrid(MetaData meta) {
		super(meta);
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
			screen.getChildren().add(fillGrid(current, w, h, wcount, vcount, dimension, row));
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
	

	public Shape fillGrid(Cell current, double screenwid, double screenheight, int wcount, int vcount, double dim, int row) {
		Polygon triangle = new Polygon();
		System.out.println((screenwid - wcount * dim));
		if (row % 2 == 0) {
			triangle.getPoints().setAll(new Double[] {
					screenwid - (wcount + .5) * dim, screenwid - (vcount - 1) * dim,
					screenwid - (wcount - .5) * dim, screenwid - (vcount - 1) * dim,
					screenwid - (wcount) * dim, screenwid - (vcount) * dim });
		} else {
			triangle.getPoints().setAll(new Double[] {
					screenwid - wcount * dim, screenwid - vcount * dim,
					screenwid - (wcount - 1) * dim, screenwid - vcount * dim,
					screenwid - (wcount - .5) * dim, screenwid - (vcount - 1) * dim });
		}
		triangle.setFill(getColorPicker().getColor((current.getCurrentState())));
		return triangle;
	}

}
