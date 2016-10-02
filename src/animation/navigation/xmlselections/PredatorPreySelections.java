package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;

public class PredatorPreySelections extends XmlSelection {
	
	private Scene myScene;

	public PredatorPreySelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
		myScene = scene;
	}
	
	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		addGridOptions();
		getScreen().getChildren().add(createEnergySlider());
		getScreen().getChildren().add(createReproductiveSlider());
		return getScreen();
	}
	
	
	private Slider createEnergySlider() {
		return createGeneralSlider(0, 50, 22, 5, myScene.getWidth() * .30, myScene.getHeight() * .9, myScene.getWidth() * .25);
	}
	

	private Slider createReproductiveSlider() {
		return createGeneralSlider(3, 20, 8, 5, myScene.getWidth() * .30, myScene.getHeight() * .2, myScene.getWidth() * .25);
	}
}
