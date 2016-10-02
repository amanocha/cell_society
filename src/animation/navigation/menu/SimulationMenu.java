package animation.navigation.menu;

import animation.navigation.GUIGenerator;
import engine.Loop;
import javafx.scene.Group;
import javafx.scene.Scene;
import readxml.XmlMapper;

public class SimulationMenu extends Menu {
	
	private Loop myLoop;
	
	public SimulationMenu(Scene scene, Group r, XmlMapper info, GUIGenerator gui) {
		super(r, gui);
		myLoop = new Loop(scene, info, r);
	}

	public void generateMenu() {
		getRoot().getChildren().clear();
		getRoot().getChildren().addAll(getGUI().generateSimulationScreenMainButton(myLoop), getGUI().generateSimulationScreenLabel(), 
				myLoop.getSimulationGUI().generateSimulationScreenControls(), myLoop.getSimulationGUI().generateSimulationScreenButton());
		myLoop.init();
	}
	
}
