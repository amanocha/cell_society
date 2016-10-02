package animation.navigation.menu;

import java.util.ResourceBundle;

import animation.navigation.GUIGenerator;
import animation.navigation.xmlselections.FireSelections;
import animation.navigation.xmlselections.GameOfLifeSelections;
import animation.navigation.xmlselections.PredatorPreySelections;
import animation.navigation.xmlselections.SegregationSelections;
import animation.navigation.xmlselections.XmlSelection;
import javafx.scene.Group;
import javafx.scene.Scene;
import readxml.XmlMapper;

public class XmlMenu extends Menu {
	
	private XmlSelection mySelection;
	
	public XmlMenu(Scene scene, Group r, GUIGenerator gui, XmlMapper info, ResourceBundle resource, String simulationname) {
		super(r, gui);
		if (simulationname == null) {
			mySelection = new XmlSelection(scene, r, info, resource);
		} else if (simulationname.equals("FIRE")) {
			mySelection = new FireSelections(scene, r, info, resource);
		} else if(simulationname.equals("WA-TOR")) {
			mySelection = new PredatorPreySelections(scene, r, info, resource);
		} else if(simulationname.equals("SEGREGATION")) {
			mySelection = new SegregationSelections(scene, r, info, resource);
		} else if(simulationname.equals("GAME OF LIFE")) {
			mySelection = new GameOfLifeSelections(scene, r, info, resource);
		}
	}
		

	public void generateMenu() {
		getRoot().getChildren().clear();
		mySelection.generateXMLScreen();
		getRoot().getChildren().add(mySelection.getScreen());
	}
	
	
}
