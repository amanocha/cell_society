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


/**
 * This is the Navigator class which handles the logic for deciding which menu should be opened depending on
 * what the user chooses. 
 * 
 * @author Hannah Fuchshuber
 */

public class Navigator {
	
	private static final String STYLESHEET = "style.css";
	private static final String LANGUAGE = "simulation";
	
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
	
	/**
	 * Generates the constructor called from main with default simulation
	 * @param s
	 * @param w
	 * @param h
	 */
	public Navigator(Stage s, double w, double h) {
		myInfo = new XmlMapper();
		myResource = PropertyResourceBundle.getBundle(LANGUAGE);
		myInfo.mapXml(myResource.getString("DefaultSelection"));
		myStage = s;
		this.width = w;
    	this.height = h;
	}

	/**
	 * Generates the constructor for every other call to Navigator
	 * @param scene
	 * @param r
	 * @param info
	 */
	public Navigator(Scene scene, Group r, XmlMapper info) {
		this.root = r;
		this.myScene = scene;
		myResource = PropertyResourceBundle.getBundle(LANGUAGE);
		this.myInfo = info;
		
	}
	
	/**
	 * Initializes the scene and the stage (only called from main)
	 */
	public void init() {
		root = new Group();
    	myScene = new Scene(root, width, height, Color.DIMGRAY);
    	myScene.getStylesheets().add(this.getClass()
    			.getResource(STYLESHEET).toExternalForm());
        myStage.setScene(myScene);
        myStage.show();
        createMainMenu();
	}
	
	/**
	 * Instantiates the xml Menu class
	 * @param The simulation selected
	 */
	public void createXmlMenu(String name) {
		xmlMenu = new XmlMenu(myScene, root, new GUIGenerator(myScene, root, myResource, myInfo), myInfo, myResource, name); 
		xmlMenu.generateMenu();
	}
	
	/**
	 * Instantiates the simulation menu class
	 */
	public void createSimluationMenu() {
		simulationMenu = new SimulationMenu(myScene, root, myInfo, new GUIGenerator(myScene, root, myResource, myInfo), myResource);
		simulationMenu.generateMenu();
	}
	
	/**
	 * Instantiates the main menu class
	 */
	public void createMainMenu() {
		mainMenu = new MainMenu(root, new GUIGenerator(myScene, root, myResource, myInfo));
        mainMenu.generateMenu();
	}
	

}
