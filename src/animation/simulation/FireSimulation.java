package animation.simulation;

import java.util.Iterator;

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
		if (current.getCurrentState() == 1) {
			rec = drawState(Color.GREEN);
		}
		if (current.getCurrentState() == 2){
			rec = drawState(Color.RED);
		}
		return rec;
	}
	


}
