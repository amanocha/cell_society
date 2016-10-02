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

public class SegregationSelections extends XmlSelection {
	
	private double mySatisfaction;
	private Scene myScene;
	
	public SegregationSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
	}

	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		getSimulationCombo().setValue("SEGREGATION");
		addGridOptions();
		System.out.println(getSimulationCombo().getValue());
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			xmlMap();
			getNavigator().createMainMenu();
		});
		getScreen().getChildren().add(createSatisfactionLabel());
		getScreen().getChildren().add(button);
		getScreen().getChildren().add(createSatisfactionSlider());
		return getScreen();
	}
	
	private void xmlMap() {
		System.out.println(getCellNumber());
		UserInputToXML input = new UserInputToXML(getCellNumber());
		input.setShape(getShape());
		input.setSatisfactionRate(mySatisfaction);
		input.setSimulation("segregation");
		input.generateXML();
	}
	
	public Label createSatisfactionLabel() {
		return createSmallLabel("Satisfaction Rate", myScene.getWidth() * .25, myScene.getHeight() * .6);
	}
	
	private Slider createSatisfactionSlider() {
		Slider slider = createGeneralSlider(0, 1, 0.5, 0.1,  myScene.getWidth() * .4, myScene.getHeight() * .6, myScene.getWidth() * .25);
		mySatisfaction = slider.getValue();
		slider.setOnDragDone(e -> mySatisfaction = slider.getValue());
		return slider;
	}

}
