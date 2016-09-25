package animation.menu;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import structures.Grid;

public class Navigation {
	
	private static final String STYLESHEET = "style.css";
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
	
	
	public Navigation() {
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
		//root.getChildren().addAll(myGUI.generateSimulationScreenMainButton(), myGUI.generateSimulationScreenLabel(), 
				//myGUI.generateSimulationScreenControls());
	}
	
	public void simulationMenuRefresh(Grid grid) {
		root.getChildren().clear();
		root.getChildren().add(new Rectangle(0, 0, Color.GREEN));
	}
	
	public void simulationMenuRefresh() {
		root.getChildren().add(myGUI.generateSimulationScreen());
	}
	
	public void xmlMenu() {
		init();
		root.getChildren().add(myGUI.generateXMLScreen());
	}


}
