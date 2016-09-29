package animation.simulation;


import javafx.scene.layout.Pane;
import structures.Grid;


public class Simulation {
	
	private Pane screen;
	
	public Simulation() {
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
	
	
	
}
