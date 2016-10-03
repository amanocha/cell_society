package animation.controls.label;

/**
 * This is the Header class which extends the abstract Label class which creates large text to be a header.
 * 
 * @author Hannah Fuchshuber
 */

public class Header extends LabelAbstract {
	
	/**
	 * Sets the css of the header label
	 * @param text
	 */
	public Header(String text) {
		super(text);
		getLabel().setId("label-header");
	}

}
