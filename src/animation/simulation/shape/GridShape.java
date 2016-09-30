package animation.simulation.shape;


import animation.simulation.color.CellColor;
import javafx.scene.layout.Pane;
import structures.Grid;
import structures.MetaData;

public abstract class GridShape {
	

	private Pane screen;
	private CellColor color;
	
	
	public GridShape(MetaData meta) {
		color = meta.getColor();
		this.screen = new Pane();
	}

	public Pane setUpScreen(Grid grid, int w, int h) {
		screen.getChildren().clear();
		screen.setMaxWidth(w + 2 * grid.getColumns()); //adjust for grid outline
		screen.setMaxHeight(h + 2 * grid.getRows()); //adjust for grid outline
		return screen;
	}
	
	public Pane getScreen() {
		return screen;
	}
	
	public CellColor getColorPicker() {
		return color;
	}

}

	
	
	