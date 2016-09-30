package animation.navigation.menu;

import animation.navigation.GUIGenerator;
import javafx.scene.Group;

public class XmlMenu extends Menu {
	
	
	public XmlMenu(Group r, GUIGenerator gui) {
		super(r, gui);
	}

	public void generateMenu() {
		getRoot().getChildren().clear();
		getRoot().getChildren().add(getGUI().generateXMLScreen());
	}
}
