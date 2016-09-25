package animation.simulation;

import javafx.scene.shape.Rectangle;
import structures.Cell;

public abstract class AbstractDraw {
	
	public abstract Rectangle fillGrid(Cell current, int width, int height);

}
