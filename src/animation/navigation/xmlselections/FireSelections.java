package animation.navigation.xmlselections;

import java.util.ResourceBundle;

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
	private double myProb;
	
	public FireSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
		
	}
	
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue("FIRE");
		addGridOptions();
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			getNavigator().createMainMenu();
		});
		getScreen().getChildren().add(createProbLabel());
		getScreen().getChildren().add(createProbSlider());
		getScreen().getChildren().add(button);
		return getScreen();
	}
	
	private void xmlMap() {
		UserInputToXML input = new UserInputToXML(getCellNumber());
		input.setShape(getShape());
		input.setSimulation("fire");
		input.setProbCatch(myProb);
		input.generateXML();
	}
	
	public Label createProbLabel() {
		return createSmallLabel("Probability Catch", myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	public Slider createProbSlider() {
		Slider slider = createGeneralSlider(0, 1, 0.5, 0.1, myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		myProb = slider.getValue();
		slider.setOnDragDone(e -> myProb = slider.getValue());
		return slider;
	}
	

}
