package animation.controls;

import javafx.scene.control.Label;

public class GeneralMessage {
	
	private Label text;

	public GeneralMessage(String s) {
		text = new Label(s);
		text.setId("message-box");
	}
	
	public Label getMessage() {
		return text;
	}
	
	public void setWidth(double width) {
		text.setMinWidth(width);
	}
	
}
