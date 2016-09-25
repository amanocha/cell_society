package animation.controls;


import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class GeneralLabel extends GeneralControl {
	
	public GeneralLabel(String s){
		super(new Label(s));
	}
	
	public Region getHeader() {
		getControl().setId("label-header");
		return getControl();
	}
	
	public Region getMessage() {
		getControl().setId("message-box");
		return getControl();
	}
	
}
