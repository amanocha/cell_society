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

public class PredatorPreySelections extends XmlSelection {
	
	private Scene myScene;
	private Slider myEnergy;
	private Slider myFishReproduction;
	private Slider mySharkReproduction;
	private Navigator myNav;
	private Group root;
	private ResourceBundle myResource;

	public PredatorPreySelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
		myResource = resource;
	}
	
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue(myResource.getString("PredatorPreyLabel"));
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			myNav.createMainMenu();
		});
		getScreen().getChildren().add(button);
		getScreen().getChildren().add(createEnergyLabel());
		getScreen().getChildren().add(createFishReproductionLabel());
		getScreen().getChildren().add(createSharkReproductionLabel());
		getScreen().getChildren().add(createEnergySlider());
		getScreen().getChildren().add(createFishReproductiveSlider());
		getScreen().getChildren().add(createSharkReproductiveSlider());
		return getScreen();
	}
	
	private void xmlMap() {
		UserInputToXML input = new UserInputToXML(getCellNumber(), 3);
		input = super.startXMLMap(input);
		input.setEnergy((int) myEnergy.getValue());
		input.setFishReproductionTime((int) myFishReproduction.getValue());
		input.setSharkReproductionTime((int) mySharkReproduction.getValue());
		input.setSimulation(myResource.getString("PredatorPreyxml"));
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	public Label createEnergyLabel() {
		return createSmallLabel(myResource.getString("EnergyLabel"), myScene.getWidth() * .3, myScene.getHeight() * .8);
	}
	
	
	public Label createFishReproductionLabel() {
		return createSmallLabel(myResource.getString("FishLabel"), myScene.getWidth() * .25, myScene.getHeight() * .7);
	}
	
	public Label createSharkReproductionLabel() {
		return createSmallLabel(myResource.getString("SharkLabel"), myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	
	private Slider createEnergySlider() {
		myEnergy = createGeneralSlider(0, 50, 22, 5, myScene.getWidth() * .4, myScene.getHeight() * .8, myScene.getWidth() * .25);
		return myEnergy;
	}
	

	private Slider createFishReproductiveSlider() {
		myFishReproduction = createGeneralSlider(3, 20, 8, 5, myScene.getWidth() * .4, myScene.getHeight() * .7, myScene.getWidth() * .25);
		return myFishReproduction;
	}
	
	private Slider createSharkReproductiveSlider() {
		mySharkReproduction = createGeneralSlider(3, 20, 8, 5, myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		return mySharkReproduction;
	}
}
