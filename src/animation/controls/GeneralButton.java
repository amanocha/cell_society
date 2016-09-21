package animation.controls;

import javafx.scene.control.Button;

public class GeneralButton {
	
	private Button stringButton;
	
	public GeneralButton(String s) {
		stringButton = new Button(s);
		stringButton.setId("main-button");
	}
	
	public Button getStringButton() {
		return stringButton;
	}
	
	public void setWidth(double width) {
		stringButton.setPrefWidth(width);
	}
	
}
