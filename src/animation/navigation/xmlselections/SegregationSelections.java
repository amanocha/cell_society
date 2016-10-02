package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;

public class SegregationSelections extends XmlSelection {
	
	public SegregationSelections(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		super(scene, r, info, resource);
	}

	public Pane generateXMLScreen() {
		super.generateXMLScreen();
		addGridOptions();
		getScreen().getChildren().add(createProbSlider());
		return getScreen();
	}

}
