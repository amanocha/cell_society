package animation.simulation;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Simulation {
	
	public Rectangle drawGrid() {
		Rectangle cell = new Rectangle(10, 10, 0, 0);
		cell.setFill(Color.ALICEBLUE);
		return cell;
	}
	
}
