package animation.simulation.shape;


import java.util.Iterator;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import structures.Cell;
import structures.Grid;
import structures.MetaData;

public class SquareGrid extends GridShape {
	
	
	public SquareGrid(MetaData meta) {
		super(meta);
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
			screen.getChildren().add(fillGrid(current, width, height));
		}
		return screen;
	}
	
	public Shape fillGrid(Cell current, int width, int height) {
		int dim = width;
		if (width > height) {
			dim = height;
		}
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(getColorPicker().getColor(current.getCurrentState()));
		rectangle.setWidth(dim);
		rectangle.setHeight(dim);
		return rectangle;
	}
	
	
}



