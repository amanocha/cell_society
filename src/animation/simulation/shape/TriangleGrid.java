
package animation.simulation.shape;


import java.util.Iterator;

import animation.simulation.color.CellColor;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import structures.Cell;
import structures.Grid;
import structures.MetaData;

public class TriangleGrid extends GridShape {
	
	private CellColor myColor;
	
	
	public TriangleGrid() {
		super();
	}
	
	public TriangleGrid(MetaData meta) {
		super();
		myColor = meta.getColor();
	}
	
	public Pane drawGrid(Grid grid, int w, int h) {
		Pane screen = setUpScreen(grid, w, h);
		Iterator<Cell> itr = grid.iterator();
		int width = (int) w / (grid.getColumns() / 2);
		int height = (int) h / (grid.getRows()); 
		int wcount = (grid.getColumns() / 2);
		int vcount = (grid.getRows());
		int row = 1;
		while(itr.hasNext()) {
			Cell current = itr.next();
			screen.getChildren().add(fillGrid(current, w, h, wcount, vcount, width, height, row));
			wcount--;
			if (wcount == 0) {
				wcount = (grid.getColumns() / 2);
				row++;
				if (row % 2 != 0) {
					vcount--;
				}
			}
		}
		return screen;
	}
	

	private Shape fillGrid(Cell current, double screenwid, double screenheight, int wcount, int vcount, double width, double height, int row) {
		Polygon triangle = new Polygon();
		if (row % 4 == 1) {
			triangle.getPoints().setAll(new Double[] {
					screenwid - (wcount + .5) * width, screenwid - (vcount - 1) * height,
					screenwid - (wcount - .5) * width, screenwid - (vcount - 1) * height,
					screenwid - (wcount) * width, screenwid - (vcount) * height });
		} else if (row % 4 == 2) {
			triangle.getPoints().setAll(new Double[] {
					screenwid - wcount * width, screenwid - vcount * height,
					screenwid - (wcount - 1) * width, screenwid - vcount * height,
					screenwid - (wcount - .5) * width, screenwid - (vcount - 1) * height });
		} else if (row % 4 == 3){
			triangle.getPoints().setAll(new Double[] {
					screenwid - (wcount - 1) * width, screenwid - (vcount - 1) * height,
					screenwid - (wcount) * width, screenwid -  (vcount - 1) * height,
					screenwid - (wcount - .5) * width, screenwid - (vcount) * height });
		} else {
			triangle.getPoints().setAll(new Double[] {
					screenwid - (wcount) * width, screenwid - (vcount - 1) * height,
					screenwid - (wcount - .5) * width, screenwid -  (vcount) * height,
					screenwid - (wcount + .5) * width, screenwid -  (vcount) * height });
		}
		triangle.setFill(myColor.getColor((current.getCurrentState())));
		return triangle;
	}

}
