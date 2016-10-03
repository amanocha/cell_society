package animation.controls.label;

import javafx.scene.control.Label;

/**
 * This is the Abstract Label class which every other label extends.
 * 
 * @author Hannah Fuchshuber
 */

public class LabelAbstract {
		
	private Label myLabel;
		
	/**
	 * Creates a label
	 * @param text
	 */
	public LabelAbstract(String text){
		myLabel = new Label(text);
	}
		
	/**
	 * Getter for the label
	 * @return Label
	 */
	public Label getLabel() {
		return myLabel;
	}
		
		

}
