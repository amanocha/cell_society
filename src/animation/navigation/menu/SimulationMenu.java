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
	
	public SimulationMenu(Scene scene, Group r, XmlMapper info, GUIGenerator gui, ResourceBundle resources) {
		super(r, gui);
		myLoop = new Loop(scene, info, r, resources);
		myResource = resources;
	}

	public void generateMenu() {
		getRoot().getChildren().clear();
		getRoot().getChildren().addAll(getGUI().generateSimulationScreenMainButton(myLoop), getGUI().generateSimulationScreenLabel(), 
				myLoop.getSimulationGUI().generateSimulationScreenControls(myResource), myLoop.getSimulationGUI().generateSimulationScreenButton());
		myLoop.init();
	}
	
}
