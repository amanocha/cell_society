package animation.controls;



import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GeneralBox extends GeneralControl {
	
	public enum Orientation {
		HORIZANTAL, VERTICAL
	}
	
	
	public GeneralBox(double p, Orientation t) {
		super(p, t);
	}
	
	public void add(Node add) {
		((Pane) getControl()).getChildren().add(add);
	}
	
	public boolean addAll(Node... add) {
		((Pane) getControl()).getChildren().addAll(add);
		return true;
	}

}
