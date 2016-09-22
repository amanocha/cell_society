package animation.controls;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class GeneralButton {
	
	public enum Type {
		START, STOP, PAUSE
	}
	
	private Button button;
	
	public GeneralButton(String s) {
		button = new Button(s);
		button.setId("string-button");
	}
	
	public GeneralButton(Type t) {
		if (t.equals(Type.START)) {
			button = new Button();
			button.setId("shape-button");
			button.setMinHeight(40);
			button.setMinWidth(40);
		}
		if (t.equals(Type.PAUSE)) {
			
		}
		if (t.equals(Type.PAUSE)) {
			
		}
	}
	
	public Button getButton() {
		return button;
	}
	
	public void setWidth(double width) {
		button.setPrefWidth(width);
	}
	
	public void setStringAction(EventHandler<ActionEvent> event) {
		button.setOnAction(event); 
	}
	
}
