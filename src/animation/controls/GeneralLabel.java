package animation.controls;

import javafx.scene.control.Label;

public class GeneralLabel {
	
	private Label header;
	
	public GeneralLabel(String s){
		header = new Label(s);
	}
	
	public Label getHeader() {

		return header;
	}
	
}
