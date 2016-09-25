package animation.simulation;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import structures.Cell;

public class FireSimulation extends AbstractDraw {

	
	public Rectangle drawState(Color c) {
		Rectangle grass = new Rectangle();
		grass.setFill(c);
		return grass;
	}
	
	@Override
	public Rectangle fillGrid(Cell current) {
		Rectangle rec;
		if (current.getCurrentState() == 1) {
			rec = drawState(Color.GREEN);
		} else if (current.getCurrentState() == 2){
			rec = drawState(Color.ORANGE);
		} else {
			rec = drawState(Color.WHITE);
		}
		return rec;
	}
	


}
