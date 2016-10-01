package animation.simulation.shape;

import java.util.Iterator;

import animation.simulation.color.CellColor;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import structures.Cell;
import structures.Grid;
import structures.MetaData;

public class HexagonGrid extends GridShape {
	
private CellColor myColor;
	
	
	public HexagonGrid() {
		super();
	}
	
	public HexagonGrid(MetaData meta) {
		super();
		myColor = meta.getColor();
	}


	public Pane drawGrid(Grid grid, int w, int h) {
		Pane screen = setUpScreen(grid, w, h);
		Iterator<Cell> itr = grid.iterator();
		int top = (w * 2) / ((3 * grid.getColumns() / 2) + 1);
		int diagonal = top / 2;
		int row = 1;
		int wcount = 0;
		int vcount = 0;
		int count = 0;
		System.out.println(grid.getCellList().size());
		while (itr.hasNext()) {
			Cell current = itr.next();
			screen.getChildren().add(fillGrid(current, top, diagonal, wcount, vcount, row));
			wcount = wcount + top * 2 + diagonal * 2;
			count++;
			if (count % (grid.getColumns() / 2) == 0) {
				count = 0;
				wcount = 0;
				row++;
				vcount = vcount + diagonal;
			}
		}
		return screen;
	}
	
	private Shape fillGrid(Cell current, double top, double diag, double wcount, double vcount, int row) {
		Polygon hexagon = new Polygon();
		if (row % 2 != 0) {
			wcount = wcount + diag + top;
		}
		hexagon.getPoints().setAll(new Double[] {
				(wcount + top), vcount - diag,
				(wcount + top + diag), vcount,
				(wcount + top * 2 + diag), vcount,
				(wcount + top * 2 + diag * 2), vcount - diag,
				(wcount + top * 2 + diag), vcount - (diag * 2),
				(wcount + top + diag), vcount - (diag * 2), });
		hexagon.setFill(myColor.getColor((current.getCurrentState())));
		return hexagon;
	}

}
