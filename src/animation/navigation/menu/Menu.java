package animation.navigation.menu;

import animation.navigation.GUIGenerator;
import javafx.scene.Group;

public class Menu {
	
	private Group root;
	private GUIGenerator myGUI;
	
	
	public Menu(Group r, GUIGenerator gui) {
		root = r;
		myGUI = gui;
	}
	
	public void generateMenu() {
	}
	
	public Group getRoot() {
		return root;
	}
	
	public GUIGenerator getGUI() {
		return myGUI;
	}

}
