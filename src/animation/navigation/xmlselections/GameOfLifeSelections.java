package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import animation.navigation.Navigator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;
import readxml.XMLGenerator.UserInputToXML;


/**
 * This is the Game of Life Selections class which creates the GUI elements for the Xml selection page after selecting
 * game of life from the combobox.
 * 
 * @author Hannah Fuchshuber
 */

public class GameOfLifeSelections extends XmlSelection {
	
	private Scene myScene;
	private Group root;
	private Navigator myNav;
	private ResourceBundle myResource;
	
	/**
	 * This creates the game of selection Pane
	 * @param scene
	 * @param r
	 * @param info
	 * @param resource
	 */
	public GameOfLifeSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
		myResource = resource;
	}
	
	/**
	 * This adds all the GUI elements for game of life
	 */
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue(myResource.getString("GameOfLifeLabel"));
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			myNav.createMainMenu();
		});
		getScreen().getChildren().add(button);
		return getScreen();
	}
	
	/**
	 * This maps all the info to the XML backend
	 */
	private void xmlMap() {
		UserInputToXML input = super.startXMLMap(2);
		input.setSimulation(myResource.getString("GameOfLifexml"));
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	

}
