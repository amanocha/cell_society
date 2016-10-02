package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import animation.navigation.Navigator;
import engine.UserInputToXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;

public class GameOfLifeSelections extends XmlSelection {
	
	private Scene myScene;
	private Group root;
	private Navigator myNav;
	

	public GameOfLifeSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
	}
	
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue("GAME OF LIFE");
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			myNav.createMainMenu();
		});
		getScreen().getChildren().add(button);
		return getScreen();
	}
	
	private void xmlMap() {
		System.out.println(getCellNumber());
		UserInputToXML input = new UserInputToXML(getCellNumber());
		input.setShape(getShape());
		input.setSimulation("game of life");
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	

}
