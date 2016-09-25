package animation.menu;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Navigation {
	
	private static final String STYLESHEET = "style.css";
	private static final String RESOURCE_PACKAGE = "/src/animation/resources/";
	private static final String LANGUAGE = "English_en_US";
	
	private static Stage myStage;
	private Group root;
	private Scene myScene;
	private GUIGenerator myGUI;
	private double width;
	private double height;
	private ResourceBundle myResources;
	
	public Navigation(Stage s, double w, double h) {
		myStage = s;
		this.width = w;
    	this.height = h;
	}
	
	public Navigation(double w, double h) {
		this(myStage, w, h);
	}
	

	public void init() {
    	root = new Group();
    	myScene = new Scene(root, width, height, Color.DIMGRAY);
    	myScene.getStylesheets().add(this.getClass()
    			.getResource(STYLESHEET).toExternalForm());
        myStage.setScene(myScene);
        myStage.show();
        myResources = PropertyResourceBundle.getBundle(LANGUAGE);
        myGUI = new GUIGenerator(myScene, root, myResources);
	}
	
	public void mainMenu() {
		init();
    	root.getChildren().add(myGUI.generateMainScreen());
	}
	
	
	public void simulationMenu() {
		init();
		root.getChildren().addAll(myGUI.generateSimulationScreenMainButton(), myGUI.generateSimulationScreenLabel(), 
				myGUI.generateSimulationScreenControls(), myGUI.generateSimulationScreenButton(), myGUI.generateSimulationScreen());
	}
	
	public void xmlMenu() {
		init();
		root.getChildren().add(myGUI.generateXMLScreen());
	}

}
