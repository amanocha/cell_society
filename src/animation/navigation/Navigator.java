// This entire file is part of my masterpiece.
// HANNAH FUCHSHUBER


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
 * what the user chooses. I think that this is good design because it uses inheritance instead of using an 
 * if-else statement to generate the different types of Menus. I think that all the methods are easily readable 
 * and short. 
 * 
 * @author Hannah Fuchshuber
 */

public class Navigator {
	
	private static final String LANGUAGE = "simulation";
	
	private static Stage myStage;
	private Group root;
	private Scene myScene;
	private double width;
	private double height;
	private XmlMapper myInfo;
	private ResourceBundle myResource;
	
	/**
	 * Generates the constructor called from main with default simulation. This constructor is only called from main,
	 * because it calls mapXML.
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
	 * Generates the constructor for every other call to Navigator. This is the constructor called every other time that 
	 * Navigator is initiated in this program. It already has the XMLMapper passed in.
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
	 * Initializes the scene and the stage (only called from main). This sets up the Stage and the Scene for the first time
	 */
	public void init() {
		root = new Group();
    	myScene = new Scene(root, width, height, Color.DIMGRAY);
    	myScene.getStylesheets().add(this.getClass()
    			.getResource(myResource.getString("StyleSheet")).toExternalForm());
        myStage.setScene(myScene);
        myStage.show();
        createMainMenu();
	}
	
	/**
	 * Instantiates the xml Menu class. This is called every time that the XML Menu is created.
	 * @param The simulation selected
	 */
	public void createXmlMenu(String name) {
		XmlMenu xmlMenu = new XmlMenu(myScene, root, new GUIGenerator(myScene, root, myResource, myInfo), myInfo, myResource, name); 
		xmlMenu.generateMenu();
	}
	
	/**
	 * Instantiates the simulation menu class. This is called every time that Simulation Menu is created.
	 */
	public void createSimluationMenu() {
		SimulationMenu simulationMenu = new SimulationMenu(myScene, root, myInfo, new GUIGenerator(myScene, root, myResource, myInfo), myResource);
		simulationMenu.generateMenu();
	}
	
	/**
	 * Instantiates the main menu class. This is called every time the main menu is created.
	 */
	public void createMainMenu() {
		MainMenu mainMenu = new MainMenu(root, new GUIGenerator(myScene, root, myResource, myInfo));
        mainMenu.generateMenu();
	}
	

}
