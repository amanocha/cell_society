package animation.simulation.color;

import javafx.scene.paint.Color;


/**
 * This is the Game Of Life Color class which fills in the shapes with the correct colors for the game of life simulation.
 * 
 * @author Hannah Fuchshuber
 */


public class GameOfLifeColor extends CellColor {

	/**
	 * Creates the correct color coordinations for the game of life simulation
	 */
	public GameOfLifeColor() {
		super();
		putColor(0, Color.WHITE);
		putColor(1, Color.YELLOW);
	}
	
	
}
	

