package animation.simulation;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import structures.Cell;

public abstract class AbstractDraw {
	
	private Map<Integer, Color> colorMap;
	
	public AbstractDraw() {
		colorMap = new HashMap<Integer, Color>();
	}
	
	public Rectangle fillGrid(Cell current, int width, int height) {
		int dim = width;
		if (width > height) {
			dim = height;
		}
		Rectangle rec = new Rectangle();
		rec.setFill(getColor(current.getCurrentState()));
		rec.setWidth(dim);
		rec.setHeight(dim);
		return rec;
	}
	
	protected Color getColor(int state) {
		return colorMap.get(state);
	}
	
	protected void putColor(int state, Color color) {
		colorMap.put(state, color);
	}
	

}
