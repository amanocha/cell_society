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
 * This is the Segregation Selections class which creates the GUI elements for the Xml selection page after selecting
 * segregation from the combobox.
 * 
 * @author Hannah Fuchshuber
 */


public class SegregationSelections extends XmlSelection {
	
	private Slider mySatisfactionSlider;
	private Scene myScene;
	private Group root;
	private Navigator myNav;
	private ResourceBundle myResource;
	
	public SegregationSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
		myResource = resource;
	}
	
	
	/**
	 * Adds all the GUI elements for Segregation selection page
	 */
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue(myResource.getString("SegregationLabel"));
		System.out.println(getSimulationCombo().getValue());
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			myNav.createMainMenu();
		});
		getScreen().getChildren().add(createSatisfactionLabel());
		getScreen().getChildren().add(button);
		getScreen().getChildren().add(createSatisfactionSlider());
		return getScreen();
	}
	
	/**
	 * Maps all the user inputs to back-end xml
	 */
	private void xmlMap() {
		UserInputToXML input = super.startXMLMap(3);
		input.setSatisfactionRate(mySatisfactionSlider.getValue());
		input.setSimulation(myResource.getString("Segregationxml"));
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	/**
	 * Creates satisfaction label
	 * @return Label
	 */
	private Label createSatisfactionLabel() {
		return createSmallLabel(myResource.getString("SatisfactionLabel"), myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	/**
	 * Creates satisfaction slider
	 * @return Slider
	 */
	private Slider createSatisfactionSlider() {
		mySatisfactionSlider = createGeneralSlider(0, 1, 0.5, 0.1,  myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		return mySatisfactionSlider;
	}

}
