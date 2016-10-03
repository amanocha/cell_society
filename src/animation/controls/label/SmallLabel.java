package animation.controls.label;

/**
 * This is the Small Label class which extends the abstract Label class and creates a title that is smaller than the header title.
 * 
 * @author Hannah Fuchshuber
 */

public class SmallLabel extends LabelAbstract {
	
	
	/**
	 * Sets the CSS for the small header 
	 * @param text for small header
	 */
	public SmallLabel(String text) {
		super(text);
		getLabel().setId("small-label");
	}


}
