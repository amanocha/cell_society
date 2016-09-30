package animation.controls.button;

public class ButtonString extends ButtonAbstract {
	
	public ButtonString(String s) {
		super();
		getButton().setText(s);
		getButton().setId("string-button");
	}

}
