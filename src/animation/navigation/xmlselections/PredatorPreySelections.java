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
	private int myEnergy;
	private int myFishReproduction;
	private int mySharkReproduction;
	private Navigator myNav;
	private Group root;

	public PredatorPreySelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
	}
	
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue("WA-TOR");
		addGridOptions();
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
		UserInputToXML input = new UserInputToXML(getCellNumber());
		input.setShape(getShape());
		input.setEnergy(myEnergy);
		input.setFishReproductionTime(myFishReproduction);
		input.setSharkReproductionTime(mySharkReproduction);
		input.setSimulation("predator prey");
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	public Label createEnergyLabel() {
		return createSmallLabel("Energy", myScene.getWidth() * .3, myScene.getHeight() * .8);
	}
	
	
	public Label createFishReproductionLabel() {
		return createSmallLabel("Fish Reproduction", myScene.getWidth() * .25, myScene.getHeight() * .7);
	}
	
	public Label createSharkReproductionLabel() {
		return createSmallLabel("Shark Reproduction", myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	
	private Slider createEnergySlider() {
		Slider energy = createGeneralSlider(0, 50, 22, 5, myScene.getWidth() * .4, myScene.getHeight() * .8, myScene.getWidth() * .25);
		myEnergy = (int) energy.getValue();
		energy.setOnDragDone(e -> myEnergy = (int) energy.getValue());
		return energy;
	}
	

	private Slider createFishReproductiveSlider() {
		Slider fishrep = createGeneralSlider(3, 20, 8, 5, myScene.getWidth() * .4, myScene.getHeight() * .7, myScene.getWidth() * .25);
		myFishReproduction = (int) fishrep.getValue();
		fishrep.setOnDragDone(e -> myFishReproduction = (int) fishrep.getValue());
		return fishrep;
	}
	
	private Slider createSharkReproductiveSlider() {
		Slider sharkrep = createGeneralSlider(3, 20, 8, 5, myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		mySharkReproduction = (int) sharkrep.getValue();
		sharkrep.setOnDragDone(e -> mySharkReproduction = (int) sharkrep.getValue());
		return sharkrep;
	}
}
