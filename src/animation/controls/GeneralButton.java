package animation.controls;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;

public class GeneralButton extends GeneralControl{
	
	public enum Function {
		START, STOP, PAUSE
	}

	
	public GeneralButton(String s) {
		super(new Button(), s);
	}
	
	public GeneralButton(Function t) {
		super(new Button(), t);
	}

	
	public void setStringAction(EventHandler<ActionEvent> event) {
		((ButtonBase) getControl()).setOnAction(event); 
	}
	
	
}
