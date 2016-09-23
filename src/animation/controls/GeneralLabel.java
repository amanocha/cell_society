package animation.controls;

import javafx.scene.control.Label;

public class GeneralLabel {
	
	private Label header;
	
	public GeneralLabel(String s){
		header = new Label(s);
	}
	
	public Label getHeader() {
		header.setId("label-header");
		return header;
	}
	
	public Label getMessage() {
		header.setId("message-box");
		return header;
	}
	
	public void setWidth(double width) {
		header.setMinWidth(width);
	}
	
	public void setX(double x) {
		header.setLayoutX(x);
	}
	
	public void setY(double y) {
		header.setLayoutY(y);
	}
	
	
}
