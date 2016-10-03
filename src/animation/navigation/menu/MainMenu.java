package animation.navigation.menu;


import animation.navigation.GUIGenerator;
import javafx.scene.Group;


/**
 * This is the Main Menu class which extends Menu. It adds all the GUI elements to the root to display the main menu page.
 * 
 * @author Hannah Fuchshuber
 */

public class MainMenu implements Menu {
	
	private Group root;
	private GUIGenerator myGUI;
	
	/**
	 * Sets up the root and the passes to the super constructor
	 * @param r
	 * @param gui
	 */
	public MainMenu(Group r, GUIGenerator gui) {
		root = r;
		myGUI = gui;
	}

	/**
	 * Adds all the GUI elements to the root
	 */
	public void generateMenu() {
		root.getChildren().clear();
    	root.getChildren().add(myGUI.generateMainScreen());
	}
	
}
