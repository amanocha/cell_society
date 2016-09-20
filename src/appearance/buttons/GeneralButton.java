package appearance.buttons;


import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class GeneralButton {
	
	private HashMap<String, Node> map = new HashMap<String, Node>(); 

	public Button getButton1(String text) {
		return (Button) map.get(text);
	}
	
	
	public void createButton1(String text, double width) {
		Button b1 = new Button(text); 
		b1.setMinWidth(width);
		b1.setAlignment(Pos.CENTER);
		b1.setId("main-button");
		map.put(text, b1);
	}
	
	
	
}
