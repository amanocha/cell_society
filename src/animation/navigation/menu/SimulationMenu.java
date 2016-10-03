package animation.navigation.menu;

import java.util.ResourceBundle;

import animation.navigation.GUIGenerator;
import engine.Loop;
import javafx.scene.Group;
import javafx.scene.Scene;
import readxml.XmlMapper;


/**
 * This is the Simulation Menu class which adds all the GUI elements for the simulation page to the root to be displayed.
 * 
 * @author Hannah Fuchshuber
 */

public class SimulationMenu implements Menu {
	
	private Loop myLoop;
	private ResourceBundle myResource;
	private Group root;
	private GUIGenerator myGUI;
	
	public SimulationMenu(Scene scene, Group r, XmlMapper info, GUIGenerator gui, ResourceBundle resources) {
		myLoop = new Loop(scene, info, r, resources);
		myResource = resources;
		root = r;
		myGUI = gui;
	}

	/**
	 * Adds GUI elements to the root to make the Simulation Menu
	 */
	public void generateMenu() {
		root.getChildren().clear();
		root.getChildren().addAll(myGUI.generateSimulationScreenMainButton(myLoop), myGUI.generateSimulationScreenLabel(), 
				myLoop.getSimulationGUI().generateSimulationScreenControls(myResource), myLoop.getSimulationGUI().generateSimulationScreenButton(myResource, root));
		myLoop.init();
	}
	
}
