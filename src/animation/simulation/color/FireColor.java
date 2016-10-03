package animation.simulation.color;


import javafx.scene.paint.Color;

/**
 * This is the Fire Color class which fills in the shapes with the correct colors for fire simulation.
 * 
 * @author Hannah Fuchshuber
 */

public class FireColor extends CellColor {
	
	/**
	 * Creates the correct color coordinations for fire simulation
	 */
	public FireColor() {
		super();
		putColor(0, Color.WHITE);
		putColor(1, Color.DARKGREEN);
		putColor(2, Color.ORANGE);
	}
	
}
