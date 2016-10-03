package animation.controls.label;

/**
 * This is the Message Label class which extends abstract label and creates the message box displayed on the front
 * of the GUI.
 * 
 * @author Hannah Fuchshuber
 */

public class Message extends LabelAbstract {

	
	/**
	 * Sets the CSS for the MEssage
	 * @param Text for message
	 */
	public Message(String text) {
		super(text);
		getLabel().setId("message-box");
	}
}
