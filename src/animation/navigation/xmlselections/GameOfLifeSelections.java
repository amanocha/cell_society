package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import animation.navigation.Navigator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;
import readxml.XMLGenerator.UserInputToXML;

public class GameOfLifeSelections extends XmlSelection {
	
	private Scene myScene;
	private Group root;
	private Navigator myNav;
	private ResourceBundle myResource;
	

	public GameOfLifeSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
		myResource = resource;
	}
	
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
	
	private void xmlMap() {
		UserInputToXML input = super.startXMLMap(2);
		input.setSimulation(myResource.getString("GameOfLifexml"));
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	

}
