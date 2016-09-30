package animation.controls.label;

import javafx.scene.control.Label;

public class LabelAbstract {
		
	private Label myLabel;
		
	public LabelAbstract(String s){
		myLabel = new Label(s);
	}
		
	public Label getLabel() {
		return myLabel;
	}
		
		

}
