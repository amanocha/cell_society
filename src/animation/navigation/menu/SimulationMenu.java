package animation.navigation.menu;

import java.util.ResourceBundle;

import animation.navigation.GUIGenerator;
import engine.Loop;
import javafx.scene.Group;
import javafx.scene.Scene;
import readxml.XmlMapper;

public class SimulationMenu extends Menu {
	
	private Loop myLoop;
	private ResourceBundle myResource;
	private Group root;
	
	public SimulationMenu(Scene scene, Group r, XmlMapper info, GUIGenerator gui, ResourceBundle resources) {
		super(r, gui);
		myLoop = new Loop(scene, info, r, resources);
		myResource = resources;
		root = r;
	}

	public void generateMenu() {
		root.getChildren().clear();
		root.getChildren().addAll(getGUI().generateSimulationScreenMainButton(myLoop), getGUI().generateSimulationScreenLabel(), 
				myLoop.getSimulationGUI().generateSimulationScreenControls(myResource), myLoop.getSimulationGUI().generateSimulationScreenButton(myResource, root));
		myLoop.init();
	}
	
}
