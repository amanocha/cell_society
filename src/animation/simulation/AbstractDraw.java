package animation.simulation;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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
	
	public Polygon fillGridTriangle(Cell current, double screenwid, double screenheight, int wcount, int vcount, double width, double height) {
		Polygon triangle = new Polygon();
		System.out.println((screenwid - wcount * width));
		triangle.getPoints().setAll(new Double[] {
		screenwid - wcount * width, screenwid - vcount * width,
		screenwid - (wcount - 1) * width, screenwid - vcount * width,
		screenwid - (wcount - .5) * width, screenwid - (vcount - 1) * width });
		triangle.setFill(getColor(current.getCurrentState()));
		return triangle;
	}
	
	protected Color getColor(int state) {
		return colorMap.get(state);
	}
	
	protected void putColor(int state, Color color) {
		colorMap.put(state, color);
	}
	

}
