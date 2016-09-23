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
		button = new Button();
		button.setMinHeight(40);
		button.setMinWidth(40);
		if (t.equals(Type.START)) {
			button.setId("go-button");
		}
		if (t.equals(Type.PAUSE)) {
			button.setId("pause-button");
		}
		if (t.equals(Type.STOP)) {
			button.setId("stop-button");
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
	
	public void setX(double x) {
		button.setLayoutX(x);
	}
	
	public void setY(double y){
		button.setLayoutY(y);
	}
	
}
