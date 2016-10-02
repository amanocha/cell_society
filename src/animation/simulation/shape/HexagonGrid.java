package animation.simulation.shape;

import java.util.Iterator;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import structures.Cell;
import structures.Grid;

public class HexagonGrid extends GridShape {
	
	public HexagonGrid() {
		super();
	}


	public Pane drawGrid(Grid grid, int w, int h) {
		Pane screen = (Pane) super.drawGrid(grid, w, h);
		Iterator<Cell> itr = grid.iterator();
		int top = (w * 2) / ((3 * grid.getColumns()) + 1);
		int diagonal = top / 2;
		double height = h / grid.getRows();
		int row = 1;
		double wcount = 0;
		double vcount = 1;
		int count = 0;
		while (itr.hasNext()) {
			Cell current = itr.next();
			insertStatesList(current.getCurrentState());
			screen.getChildren().add(fillGrid(current, top, diagonal, height, wcount, vcount, row));
			wcount = wcount + top * 2 + diagonal * 2;
			count++;
			if (count % (grid.getColumns() / 2) == 0) {
				count = 0;
				wcount = 0;
				row++;
				vcount = vcount + (height / 2);
			}
		}
		return screen;
	}
	
	private Shape fillGrid(Cell current, double top, double diag, double height, double wcount, double vcount, int row) {
		Polygon hexagon = new Polygon();
		if (row % 2 != 0) {
			wcount = wcount + diag + top;
		}
		hexagon.getPoints().setAll(new Double[] {
				(wcount + top), vcount - (height / 2),
				(wcount + top + diag), vcount,
				(wcount + top * 2 + diag), vcount,
				(wcount + top * 2 + diag * 2), vcount - (height / 2),
				(wcount + top * 2 + diag), vcount - (height),
				(wcount + top + diag), vcount - (height), });
		hexagon.setFill(getColor().getColor((current.getCurrentState())));
		return hexagon;
	}

}
