package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import animation.navigation.Navigator;
import engine.UserInputToXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;

public class FireSelections extends XmlSelection {
	
	private Scene myScene;
	private Slider myProb;
	private Group root;
	private Navigator myNav;
	private ResourceBundle myResource; 
	
	public FireSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
		myResource = resource;
		
	}
	
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
	
	private void xmlMap() {
		UserInputToXML input = super.startXMLMap();
		input.setSimulation(myResource.getString("Firexml"));
		input.setProbCatch(myProb.getValue());
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	public Label createProbLabel() {
		return createSmallLabel(myResource.getString("ProbabilityLabel"), myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	public Slider createProbSlider() {
		myProb = createGeneralSlider(0, 1, 0.5, 0.1, myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		return myProb;
	}
	

}
