package animation.simulation.color;


import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * This is the Cell color abstract class which allows all other cell colors to extend from it.
 * 
 * @author Hannah Fuchshuber
 */


public class CellColor {
	
	private Map<Integer, Color> colorMap;
	
	/**
	 * Makes a map of the cell state to the color
	 */
	public CellColor() {
		colorMap = new HashMap<Integer, Color>();
	}
	
	/**
	 * Gets color associates to cell state
	 * @param state
	 * @return Color
	 */
	public Color getColor(int state) {
		return colorMap.get(state);
	}
	
	/**
	 * Puts the color into the color Map
	 * @param state
	 * @param color
	 */
	protected void putColor(int state, Color color) {
		colorMap.put(state, color);
	}
	

}
