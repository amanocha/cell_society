package animation.simulation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import structures.Cell;

public class PredatorPreySimulation extends AbstractDraw {
	
	public PredatorPreySimulation() {
		super();
		putColor(0, Color.WHITE);
		putColor(1, Color.ORANGE);
		putColor(2, Color.BLUE);
	}
	
	@Override
	public Rectangle fillGrid(Cell current, int width, int height) {
		return drawState(getColor(current.getCurrentState()), width, height);
	}
	
}


