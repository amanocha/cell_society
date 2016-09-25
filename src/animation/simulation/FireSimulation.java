package animation.simulation;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import readxml.Cell;
import readxml.State;

public class FireSimulation extends AbstractDraw {

	
	public Rectangle drawState(Color c) {
		Rectangle grass = new Rectangle();
		grass.setFill(c);
		return grass;
	}
	
	@Override
	public Rectangle fillGrid(Cell current) {
		Rectangle rec;
		if (Math.random() < 0.5) {
			rec = drawState(Color.GREEN);
		} else {
			rec = drawState(Color.RED);
		}
		return rec;
	}
	


}
