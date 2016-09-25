package animation.simulation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import structures.Cell;

public class SegregationSimulation extends AbstractDraw {

	public SegregationSimulation() {
		super();
		putColor(0, Color.WHITE);
		putColor(1, Color.YELLOW);
	}
	
	@Override
	public Rectangle fillGrid(Cell current, int width, int height) {
		return drawState(getColor(current.getCurrentState()), width, height);
	}
	
}
	
	

