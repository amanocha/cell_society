package animation.controls;



import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GeneralBox {
	
	public enum Orientation {
		HORIZANTAL, VERTICAL
	}
	
	private Pane box;

	public GeneralBox(double p, Orientation t) {
		if (t.equals(Orientation.HORIZANTAL)) {
			box = new HBox(p); 
		}
		if (t.equals(Orientation.VERTICAL)) {
			box = new VBox(p); 
		}
	}
	
	public void add(Node add) {
		box.getChildren().add(add);
	}
	
	public boolean addAll(Node... add) {
		box.getChildren().addAll(add);
		return true;
	}
	
	public void setX(double x) {
		box.setLayoutX(x);
	}
	
	public void setY(double y) {
		box.setLayoutY(y);
	}

	public Pane getBox() {
		return box;
	}
}
