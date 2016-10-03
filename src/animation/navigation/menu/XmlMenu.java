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

/**
 * This is the Xml Menu class which extends Menu. It adds all the GUI elements to the root to display the xml menu page.
 * 
 * @author Hannah Fuchshuber
 */

public class XmlMenu implements Menu {
	
	private XmlSelection mySelection;
	private Group root;
	
	public XmlMenu(Scene scene, Group r, GUIGenerator gui, XmlMapper info, ResourceBundle resource, String simulationname) {
		root = r;
		if (simulationname == null) {
			mySelection = new XmlSelection(scene, r, info, resource);
		} else if (simulationname.equals(resource.getString("FireLabel"))) {
			mySelection = new FireSelections(scene, r, info, resource);
		} else if(simulationname.equals(resource.getString("PredatorPreyLabel"))) {
			mySelection = new PredatorPreySelections(scene, r, info, resource);
		} else if(simulationname.equals(resource.getString("SegregationLabel"))) {
			mySelection = new SegregationSelections(scene, r, info, resource);
		} else if(simulationname.equals(resource.getString("GameOfLifeLabel"))) {
			mySelection = new GameOfLifeSelections(scene, r, info, resource);
		}
	}
		

	public void generateMenu() {
		root.getChildren().clear();
		mySelection.generateXMLScreen();
		root.getChildren().add(mySelection.getScreen());
	}
	
	
}
