package animation.navigation.menu;


import animation.navigation.GUIGenerator;
import javafx.scene.Group;

public class MainMenu extends Menu {
	
	private Group root;
	
	public MainMenu(Group r, GUIGenerator gui) {
		super(r, gui);
		root = r;
	}

	public void generateMenu() {
		root.getChildren().clear();
    	root.getChildren().add(getGUI().generateMainScreen());
	}
	
}
