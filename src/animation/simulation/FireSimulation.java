package animation.simulation;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import structures.Cell;

public class FireSimulation extends AbstractDraw {
	
	public FireSimulation() {
		super();
		putColor(0, Color.WHITE);
		putColor(1, Color.DARKGREEN);
		putColor(2, Color.ORANGE);
	}
	
	@Override
	public Rectangle fillGrid(Cell current, int width, int height) {
		return drawState(getColor(current.getCurrentState()), width, height);
	}
	
}
