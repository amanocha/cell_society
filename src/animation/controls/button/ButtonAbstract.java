package animation.controls.button;


import javafx.scene.control.Button;

public abstract class ButtonAbstract {
	
	private Button myButton;
	
	public ButtonAbstract() {
		myButton = new Button();
		myButton.setMinHeight(40);
		myButton.setMinWidth(40);
	}
	
	public Button getButton() {
		return myButton;
	}
	
	
}
