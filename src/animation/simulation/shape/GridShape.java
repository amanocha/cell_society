package animation.simulation.shape;



import javafx.scene.layout.Pane;
import structures.Grid;


public abstract class GridShape {
	

	private Pane screen;
	
	
	public GridShape() {
		this.screen = new Pane();
	}

	public Pane setUpScreen(Grid grid, int w, int h) {
		screen.getChildren().clear();
		screen.setMaxWidth(w); //adjust for grid outline
		screen.setMaxHeight(h); //adjust for grid outline
		return screen;
	}
	
	public Pane getScreen() {
		return screen;
	}

	

}

	
	
	