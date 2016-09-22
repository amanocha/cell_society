package animation.menu;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Navigation {
	
	private static Stage myStage;
	private Group root;
	private Scene myScene;
	private GUIGenerator myGUI;
	private double width;
	private double height;
	
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
    	myScene = new Scene(root, width, height, Color.DARKGRAY);
    	myScene.getStylesheets().add(this.getClass()
    			.getResource("practice.css").toExternalForm());
        myStage.setScene(myScene);
        myStage.show();
        myGUI = new GUIGenerator(myScene);
	}
	
	public void mainMenu() {
		init();
    	root.getChildren().add(myGUI.generateMainScreen());
	}
	
	
	public void simulationMenu() {
		init();
		root.getChildren().add(myGUI.generateSimulationScreen());
	}
	
	public void xmlMenu() {
		init();
		root.getChildren().add(myGUI.generateXMLScreen());
	}

}
