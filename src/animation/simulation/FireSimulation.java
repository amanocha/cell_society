package animation.simulation;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import structures.Cell;

public class FireSimulation extends AbstractDraw {

	
	private Rectangle drawState(Color c, int dim) {
		Rectangle rec = new Rectangle();
		rec.setFill(c);
		rec.setWidth(dim);
		rec.setHeight(dim);
		return rec;
	}
	
	@Override
	public Rectangle fillGrid(Cell current, int width, int height) {
		Rectangle rec;
		int dim = width;
		if (width > height) {
			dim = height;
		}
		if (current.getCurrentState() == 1) {
			rec = drawState(Color.GREEN, dim);
		} else if (current.getCurrentState() == 2){
			rec = drawState(Color.ORANGE, dim);
		} else {
			rec = drawState(Color.WHITE, dim);
		}
		return rec;
	}
	


}
