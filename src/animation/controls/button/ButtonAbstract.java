package animation.controls.button;


import javafx.scene.control.Button;

/**
 * This is the abstract Button class which is the class that allows for different buttons to be created.
 * 
 * @author Hannah Fuchshuber
 */


public abstract class ButtonAbstract {
	
	private Button myButton;
	
	/**
	 * Creates the button for all the button classes
	 */
	public ButtonAbstract() {
		myButton = new Button();
		myButton.setMinHeight(40);
		myButton.setMinWidth(40);
	}
	
	
	/**
	 * Getter for the button
	 */
	public Button getButton() {
		return myButton;
	}
	
	
}
