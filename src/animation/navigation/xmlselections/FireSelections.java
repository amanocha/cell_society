// This entire file is part of my masterpiece.
// Alan Guo
/**
 * Only the mapXml method is part of Alan Guo's  masterpiece (to show what the masterpiece is interfacing).
 * 
 * This selection class and all of its subclasses have a method called: startXml or mapXml.
 * I wanted to include these classes to show how easy it is to generate an XML file using the
 * UserToInputXml class as an interface/abstraction between the frontend and actual XML generator.
 */
package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import animation.navigation.Navigator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;
import readxml.XMLGenerator.UserInputToXML;

/**
 * This is the Fire Selections class which creates the GUI elements for the Xml selection page after selecting
 * fire from the combobox.
 * 
 * @author Hannah Fuchshuber
 */

public class FireSelections extends XmlSelection {
	
	private Scene myScene;
	private Slider myProb;
	private Group root;
	private Navigator myNav;
	private ResourceBundle myResource; 
	
	
	/**
	 * This is the constructor for the fire selections class that sets up the xml selection page.
	 * @param scene
	 * @param r
	 * @param info
	 * @param resource
	 */
	public FireSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
		myResource = resource;
	}
	
	/**
	 * This adds the GUI elements to the fire selections page.
	 * 
	 * @return Pane
	 */
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue(myResource.getString("FireLabel"));
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			myNav.createMainMenu();
		});
		getScreen().getChildren().add(createProbLabel());
		getScreen().getChildren().add(createProbSlider());
		getScreen().getChildren().add(button);
		return getScreen();
	}
	
	/**
	 * This creates passes the information from the page to the XML backend.
	 */
	private void xmlMap() {
		UserInputToXML input = super.startXMLMap(3);
		input.setSimulation(myResource.getString("Firexml"));
		input.setProbCatch(myProb.getValue());
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	/**
	 * This creates the probability label GUI
	 * @return Slider 
	 */
	public Label createProbLabel() {
		return createSmallLabel(myResource.getString("ProbabilityLabel"), myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	/**
	 * This creates the probability slider GUI
	 * @return
	 */
	public Slider createProbSlider() {
		myProb = createGeneralSlider(0, 1, 0.5, 0.1, myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		return myProb;
	}
	

}
