package animation.controls;

import animation.controls.GeneralBox.Orientation;
import animation.controls.GeneralButton.Function;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class GeneralControl {
	
	private Region me;
	
	public GeneralControl(Label label) {
		this.me = label;
	}

	public GeneralControl(Button button, Function t) {
		this.me = button;
		me.setMinHeight(40);
		me.setMinWidth(40);
		if (t.equals(Function.START)) {
			me.setId("go-button");
		}
		if (t.equals(Function.PAUSE)) {
			me.setId("pause-button");
		}
		if (t.equals(Function.STOP)) {
			me.setId("stop-button");
		}
	}


	public GeneralControl(double p, Orientation t) {
		if (t.equals(Orientation.VERTICAL)) {
			VBox v = new VBox(p); 
			this.me = v;
		}
		if (t.equals(Orientation.HORIZANTAL)) {
			HBox h = new HBox(p);
			this.me = h;
		}
	}

	public GeneralControl(Button button, String s) {
		this.me = button;
		((Button) me).setText(s);
		((Button) me).setId("string-button");
	}

	public void setX(double x) {
		me.setLayoutX(x);
	}
	
	public void setY(double y) {
		me.setLayoutY(y);
	}

	public Region getControl() {
		return me;
	}
	
	public void setWidth(double width) {
		me.setPrefWidth(width);
	}
	
	public void setHeight(double height) {
		me.setPrefHeight(height);
	}
	
}
