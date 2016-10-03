package animation.controls.button;

/**
 * This is the Button string class which extends abstract button and allows for any of the buttons that have writing in them
 * to be created.
 * 
 * @author Hannah Fuchshuber
 */

public class ButtonString extends ButtonAbstract {
	
	/**
	 * Sets the CSS of button string
	 * @param text of button
	 */
	public ButtonString(String text) {
		super();
		getButton().setText(text);
		getButton().setId("string-button");
	}

}
