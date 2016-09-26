package animation.menu;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import animation.simulation.SimulationPane;
import engine.Loop;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import readxml.XmlMapper;
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
	private XmlMapper myInfo;
	private Loop myLoop;
	
	public enum Menu {
		XML, MAIN, SIMULATION, REFRESH
	}
	
	public Navigation() {
	}
	
	public Navigation(Stage s, double w, double h) {
		myInfo = new XmlMapper();
		myInfo.mapXmlToGrid("Fire.xml");
		myStage = s;
		this.width = w;
    	this.height = h;
	}

	public Navigation(Scene scene, Group r, XmlMapper info) {
		this.root = r;
		this.myScene = scene;
		this.myInfo = info;
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
		myGUI = new GUIGenerator(myScene, root, myResources, myInfo);
		if (menu.equals(Menu.MAIN)) {
			mainMenu();
		}
		if (menu.equals(Menu.SIMULATION)) {
			myLoop = new Loop(myScene, myInfo, root);
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
				myGUI.generateSimulationScreenControls(), myGUI.generateSimulationScreenButton());
		myLoop.init();
	}
	
	
	private void xmlMenu() {
		root.getChildren().clear();
		root.getChildren().add(myGUI.generateXMLScreen());
	}
	
	public Loop getLoop() {
		return myLoop;
	}


}
