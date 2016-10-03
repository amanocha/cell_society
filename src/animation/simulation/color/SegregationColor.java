package animation.simulation.color;

import javafx.scene.paint.Color;


/**
 * This is the Segregation Color class which fills in the shapes with the correct colors for the segregation simulation.
 * 
 * @author Hannah Fuchshuber
 */

public class SegregationColor extends CellColor {

	
	/**
	 * Creates the correct color coordinations for segregation simulation
	 */
	public SegregationColor() {
		super();
		putColor(0, Color.WHITE);
		putColor(1, Color.RED);
		putColor(2, Color.BLUE);
	}
	
}
	
	

