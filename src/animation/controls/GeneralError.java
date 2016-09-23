package animation.controls;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GeneralError {

	private Alert alert;
	
	public GeneralError() {
		alert = new Alert(AlertType.ERROR);
	}
	
	public Alert getNoInformationError() {
		alert.setTitle("No Information Error");
		alert.setHeaderText("No Information Entered");
		alert.setContentText("Please make sure that you have selected the type of simulation you want to run.");
		return alert;
	}
}
