package animation.simulation.color;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class CellColor {
	
	private Map<Integer, Color> colorMap;
	
	public CellColor() {
		colorMap = new HashMap<Integer, Color>();
	}
	
	/*public Rectangle fillGrid(Cell current, int width, int height) {
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
	
	public Polygon fillGridTriangle(Cell current, double screenwid, double screenheight, int wcount, int vcount, double dim, int row) {
		Polygon triangle = new Polygon();
		System.out.println((screenwid - wcount * dim));
		if (row % 2 == 0) {
			triangle.getPoints().setAll(new Double[] {
					screenwid - (wcount + .5) * dim, screenwid - (vcount - 1) * dim,
					screenwid - (wcount - .5) * dim, screenwid - (vcount - 1) * dim,
					screenwid - (wcount) * dim, screenwid - (vcount) * dim });
		} else {
			triangle.getPoints().setAll(new Double[] {
					screenwid - wcount * dim, screenwid - vcount * dim,
					screenwid - (wcount - 1) * dim, screenwid - vcount * dim,
					screenwid - (wcount - .5) * dim, screenwid - (vcount - 1) * dim });
		}
		triangle.setFill(getColor(current.getCurrentState()));
		return triangle;
	}*/
	
	public Color getColor(int state) {
		return colorMap.get(state);
	}
	
	protected void putColor(int state, Color color) {
		colorMap.put(state, color);
	}
	

}
