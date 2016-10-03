package animation.simulation.color;

import javafx.scene.paint.Color;

/**
 * This is the Predator/Prey Color class which fills in the shapes with the correct colors for the predator/prey simulation.
 * 
 * @author Hannah Fuchshuber
 */

public class PredatorPreyColor extends CellColor {
	
	/**
	 * Creates the correct color coordinations for predator/prey simulation
	 */
	public PredatorPreyColor() {
		super();
		putColor(0, Color.WHITE);
		putColor(1, Color.ORANGE);
		putColor(2, Color.BLUE);
	}
	
}


