package animation.navigation;


import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import animation.navigation.menu.MainMenu;
import animation.navigation.menu.SimulationMenu;
import animation.navigation.menu.XmlMenu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import readxml.XmlMapper;

public class Navigator {
	
	private static final String STYLESHEET = "style.css";
	private static final String LANGUAGE = "English_en_US";
	
	private static Stage myStage;
	private Group root;
	private Scene myScene;
	private double width;
	private double height;
	private XmlMapper myInfo;
	private MainMenu mainMenu;
	private XmlMenu xmlMenu;
	private SimulationMenu simulationMenu;
	private ResourceBundle myResource;
	
	public Navigator() {
	}
	
	public Navigator(Stage s, double w, double h) {
		myInfo = new XmlMapper();
		myInfo.mapXml("fire_2500.xml");
		myResource = PropertyResourceBundle.getBundle(LANGUAGE);
		myStage = s;
		this.width = w;
    	this.height = h;
	}

	public Navigator(Scene scene, Group r, XmlMapper info) {
		this.root = r;
		this.myScene = scene;
		myResource = PropertyResourceBundle.getBundle(LANGUAGE);
		this.myInfo = info;
		
	}
	
	public void init() {
		root = new Group();
    	myScene = new Scene(root, width, height, Color.DIMGRAY);
    	myScene.getStylesheets().add(this.getClass()
    			.getResource(STYLESHEET).toExternalForm());
        myStage.setScene(myScene);
        myStage.show();
        createMainMenu();
	}
	
	public void createXmlMenu(String name) {
		xmlMenu = new XmlMenu(myScene, root, new GUIGenerator(myScene, root, myResource, myInfo), myInfo, myResource, name); 
		xmlMenu.generateMenu();
	}
	
	public void createSimluationMenu() {
		simulationMenu = new SimulationMenu(myScene, root, myInfo, new GUIGenerator(myScene, root, myResource, myInfo));
		simulationMenu.generateMenu();
	}
	
	public void createMainMenu() {
		mainMenu = new MainMenu(root, new GUIGenerator(myScene, root, myResource, myInfo));
        mainMenu.generateMenu();
	}
	

}
