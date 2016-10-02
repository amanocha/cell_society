package animation.simulation.color;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class CellColor {
	
	private Map<Integer, Color> colorMap;
	
	public CellColor() {
		colorMap = new HashMap<Integer, Color>();
	}
	
	public Color getColor(int state) {
		return colorMap.get(state);
	}
	
	protected void putColor(int state, Color color) {
		colorMap.put(state, color);
	}
	

}
