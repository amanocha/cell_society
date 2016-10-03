package animation.navigation;


import java.util.ResourceBundle;
import animation.controls.button.ButtonString;
import animation.controls.label.Header;
import animation.controls.label.Message;
import animation.controls.pane.PaneGenerator;
import engine.Loop;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;


/**
 * This is the GUI Generator, it creates the main page GUI, including the screen, buttons and title.
 * 
 * @author Hannah Fuchshuber
 */

public class GUIGenerator {

	
	private PaneGenerator myPane;
	private Scene myScene;
	private ResourceBundle myResource;
	private XmlMapper myInfo;
	private Navigator myNav;
	
	/**
	 * Sets up the instance variables
	 * @param scene
	 * @param r
	 * @param resource
	 * @param info
	 */
	public GUIGenerator(Scene scene, Group r, ResourceBundle resource, XmlMapper info) {
		this.myScene = scene;
		this.myResource = resource;
		myPane = new PaneGenerator(scene);
		myResource = resource;
		myInfo = info;
		myNav = new Navigator(scene, r, info);
	}

	/**
	 * Generates the display GUI for the main screen
	 * @return Pane
	 */
	public Pane generateMainScreen() {
		GridPane grid = myPane.getMainMenuPane();
		Label header = (new Header(myResource.getString("Title"))).getLabel(); 
		grid.add(header, 1, 0);
		grid.add(startButton(), 1, 1);
		grid.add(parameterButton(), 1, 2);
		Label text = (new Message((myResource.getString("CurrentSimulation")) + myInfo.getMeta().getSimulationName())).getLabel();
		text.setPrefWidth(myScene.getWidth() * .5);
		grid.add(text, 1, 4);
		return grid;
	}
	
	/**
	 * Generates the simulation screen's main button
	 * @param loop
	 * @return Button
	 */
	public Button generateSimulationScreenMainButton(Loop loop) {
		Button main = (new ButtonString(myResource.getString("MainMenu"))).getButton();
		main.setOnAction(e -> {
			myNav.createMainMenu();
			loop.getSimulationGUI().stopAnimation();
		});
		main.setPrefWidth(myScene.getWidth() * .20);
		main.setLayoutX(myScene.getWidth() * .73);
		main.setLayoutY(myScene.getHeight() * .8);
		return main;
	}


	/**
	 * Generates the Simulation screen's label
	 * @return Label
	 */
	public Label generateSimulationScreenLabel() {
		Label label = (new Header((myResource.getString("SimulationLabel")) + myInfo.getMeta().getSimulationName())).getLabel();
		label.setLayoutX(myScene.getWidth() * .07);
		label.setLayoutY(myScene.getHeight() * .04);
		return label;
	}
	
	
	/**
	 * Generates the main menu's start button
	 * @return Button
	 */
	private Button startButton() {
		Button button = (new ButtonString(myResource.getString("Start"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> {
			myInfo.mapXml(myInfo.getMeta().getFileName());
			myNav.createSimluationMenu();
		});
		return button;
	}

	/**
	 * Generates the main menu's parameter button
	 * @return Button
	 */
	private Button parameterButton() {
		Button button = (new ButtonString(myResource.getString("ParameterButton"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> myNav.createXmlMenu(null));
		return button;
	}
}