package animation.menu;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	
	public enum Menu {
		XML, MAIN, SIMULATION, REFRESH
	}
	
	public Navigation(Stage s, double w, double h) {
		myStage = s;
		this.width = w;
    	this.height = h;
	}
	
	public Navigation() {
	}

	public Navigation(Scene scene, Group r) {
		this.root = r;
		this.myScene = scene;
	}
	
	public void init() {
		root = new Group();
    	myScene = new Scene(root, width, height, Color.DIMGRAY);
    	myScene.getStylesheets().add(this.getClass()
    			.getResource(STYLESHEET).toExternalForm());
        myStage.setScene(myScene);
        myStage.show();
        makeScreen(Menu.MAIN);
	}
	
	public void makeScreen(Menu menu) {
		myResources = PropertyResourceBundle.getBundle(LANGUAGE);
		myGUI = new GUIGenerator(myScene, root, myResources);
		if (menu.equals(Menu.MAIN)) {
			mainMenu();
		}
		if (menu.equals(Menu.SIMULATION)) {
			simulationMenu();
		}
		if (menu.equals(Menu.XML)) {
			xmlMenu();
		}
	}
	
	private void mainMenu() {
		root.getChildren().clear();
    	root.getChildren().add(myGUI.generateMainScreen());
	}
	
	
	private void simulationMenu() {
		root.getChildren().clear();
		root.getChildren().addAll(myGUI.generateSimulationScreenMainButton(), myGUI.generateSimulationScreenLabel(), 
				myGUI.generateSimulationScreenControls());
	}
	 
	public void refreshSimulationMenu(Grid grid) {
		root.getChildren().remove(myGUI.getStackPane());
		root.getChildren().add(myGUI.generateSimulationScreen(grid));
	}
	
	
	private void xmlMenu() {
		root.getChildren().clear();
		root.getChildren().add(myGUI.generateXMLScreen());
	}


}
