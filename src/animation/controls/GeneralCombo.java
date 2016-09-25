package animation.controls;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class GeneralCombo extends GeneralControl {
	
	public GeneralCombo() {
		super(new ComboBox<ListView<String>>());
	}
	
	public void setItems(ObservableList<ListView<String>> str) {
		((ComboBox<ListView<String>>) getControl()).setItems(str);
	}

}
