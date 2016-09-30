package animation.menu;


import javafx.scene.Group;

public class MainMenu extends Menu {
	
	
	public MainMenu(Group r, GUIGenerator gui) {
		super(r, gui);
	}

	public void generateMenu() {
		getRoot().getChildren().clear();
    	getRoot().getChildren().add(getGUI().generateMainScreen());
	}
	
}
