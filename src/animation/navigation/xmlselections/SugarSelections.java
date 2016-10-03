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

public class SugarSelections extends XmlSelection {
	
	private Scene myScene;
	private Slider myInitSugar;
	private Slider myVision;
	private Slider myMetabolism;
	private Slider mySugarGrowBack;
	private Navigator myNav;
	private Group root;
	private ResourceBundle myResource;
	
	/**
	 * Intializes instance variables
	 * @param scene
	 * @param r
	 * @param info
	 * @param resource
	 */
	public SugarSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		root = r;
		myResource = resource;
	}
	
	/**
	 * This adds all the GUI elements to the view
	 * 
	 * @return Pane
	 */
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue(myResource.getString("PredatorPreyLabel"));
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			myNav.createMainMenu();
		});
		getScreen().getChildren().add(button);
		getScreen().getChildren().add(createSugarGrowthLabel());
		getScreen().getChildren().add(createVisionLabel());
		getScreen().getChildren().add(createMetabolismLabel());
		getScreen().getChildren().add(createInitialSugarLabel());
		getScreen().getChildren().add(createSugarGrowthSlider());
		getScreen().getChildren().add(createVisionSlider());
		getScreen().getChildren().add(createMetabolismSlider());
		getScreen().getChildren().add(createInitialSugarSlider());
		return getScreen();
	}
	
	
	/**
	 * Maps all the user inputs to the XML backend 
	 */
	private void xmlMap() {
		UserInputToXML input = super.startXMLMap(3);
		input.setInitSugar((int) myInitSugar.getValue());
		input.setSugarGrowBackTime((int) mySugarGrowBack.getValue());
		input.setVision((int) myVision.getValue());
		input.setSugarMetabolism((int) myMetabolism.getValue());
		input.setSimulation(myResource.getString("PredatorPreyxml"));
		input.generateXML();
		myNav = new Navigator(myScene, root, input.getMapper());
	}
	
	/**
	 * Create sugar growth label
	 * @return Label
	 */
	public Label createSugarGrowthLabel() {
		return createSmallLabel(myResource.getString("SugarGrowBackLabel"), myScene.getWidth() * .3, myScene.getHeight() * .8);
	}
	
	/**
	 * Creates vision Label
	 * @return Label
	 */
	public Label createVisionLabel() {
		return createSmallLabel(myResource.getString("SugarVisionLabel"), myScene.getWidth() * .25, myScene.getHeight() * .7);
	}
	
	
	/**
	 * Creates metabolism Label
	 * @return Label
	 */
	public Label createMetabolismLabel() {
		return createSmallLabel(myResource.getString("SugarMetabolismLabel"), myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	/**
	 * Creates initial sugar Label
	 * @return Label
	 */
	public Label createInitialSugarLabel() {
		return createSmallLabel(myResource.getString("SugarInitialValueLabel"), myScene.getWidth() * .25, myScene.getHeight() * .8);
	}
	
	/**
	 * Create sugar growth slider 
	 * @return Slider
	 */
	private Slider createSugarGrowthSlider() {
		mySugarGrowBack = createGeneralSlider(1, 5, 2, 1, myScene.getWidth() * .4, myScene.getHeight() * .8, myScene.getWidth() * .25);
		return mySugarGrowBack;
	}
	
	/**
	 * Creates vision slider
	 * @return Slider
	 */
	private Slider createVisionSlider() {
		myVision = createGeneralSlider(1, 6, 3, 1, myScene.getWidth() * .4, myScene.getHeight() * .7, myScene.getWidth() * .25);
		return myVision;
	}
	
	/**
	 * Creates metabolism slider
	 * @return Slider
	 */
	private Slider createMetabolismSlider() {
		myMetabolism = createGeneralSlider(1, 4, 2, 1, myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		return myMetabolism;
	}
	
	/**
	 * Creates initial sugar slider
	 * @return Slider
	 */
	private Slider createInitialSugarSlider() {
		myInitSugar = createGeneralSlider(5, 25, 15, 5, myScene.getWidth() * .4, myScene.getHeight() * .8, myScene.getWidth() * .25);
		return myInitSugar;
	}

}
