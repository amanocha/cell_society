package animation.navigation;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;

import animation.controls.button.ButtonString;
import animation.controls.label.Header;
import animation.controls.label.Message;
import animation.controls.pane.PaneGenerator;
import animation.navigation.menu.MainMenu;
import animation.navigation.menu.Menu;
import engine.Loop;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import readxml.XmlMapper;



public class GUIGenerator {
	
	private static String[] SIMULATIONS = {"SEGREGATION", "GAME OF LIFE", "FIRE", "WA-TOR"};
	private static String[] FILES = {"Segregation.xml", "GameOfLife.xml", "Fire.xml", "PredatorPrey.xml"};
	
	private PaneGenerator myPane;
	private Menu myMenu;
	private Scene myScene;
	private ResourceBundle myResource;
	private XmlMapper myInfo;
	private Group root;
	private Navigator myNav;
	
	public GUIGenerator(Scene scene, Group r, ResourceBundle resource, XmlMapper info) {
		this.myScene = scene;
		this.myResource = resource;
		myPane = new PaneGenerator(scene);
		myResource = resource;
		myInfo = info;
		root = r;
		myNav = new Navigator(scene, r, info);
	}


	public Pane generateMainScreen() {
		GridPane grid = myPane.getMainMenuPane();
		Label header = (new Header(myResource.getString("Title"))).getLabel(); 
		Button button = (new ButtonString(myResource.getString("Start"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> {
			myInfo.getGrid().reset();
			myNav.createSimluationMenu();
		});
		grid.add(header, 1, 0);
		grid.add(button, 1, 1);
		button = (new ButtonString(myResource.getString("ParameterButton"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> myNav.createXmlMenu());
		grid.add(button, 1, 2);
		Label text = (new Message(myResource.getString("CurrentSimulation"))).getLabel();
		text.setPrefWidth(myScene.getWidth() * .5);
		grid.add(text, 1, 4);
		return grid;
	}
	
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


	public Pane generateXMLScreen() {
		Pane grid = myPane.getXMLMenuPane();
		Button button = (new ButtonString(myResource.getString("MainMenu"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .25);
		button.setOnAction(e -> ((MainMenu) myMenu).generateMenu());
		HBox hbox = new HBox((myScene.getWidth() * .01));
		for (int i = 0; i < SIMULATIONS.length; i++) {
			String file = FILES[i];
			setXMLSelectionButtons(SIMULATIONS[i], e -> 
				{myInfo.mapXmlToGrid(file); 
				myNav.createMainMenu(); }, hbox);
		}
		Label title = (new Header(myResource.getString("SimulationSelection"))).getLabel();
		title.setLayoutX(myScene.getWidth() * .32);
		title.setLayoutY(myScene.getHeight() * .2);
		hbox.setLayoutX(myScene.getWidth() * .08);
		hbox.setLayoutY(myScene.getHeight() * .35);
		button.setLayoutX(myScene.getWidth() * .38);
		button.setLayoutY(myScene.getHeight() * .6);
		grid.getChildren().add(title);
		grid.getChildren().add(button);
		grid.getChildren().add(hbox);
		return grid;
	}
	
	private void setXMLSelectionButtons(String sim, EventHandler<ActionEvent> e, HBox hbox) {
		Button button = (new ButtonString(sim)).getButton();
		button.setPrefWidth(myScene.getWidth() * .20);
		button.setOnAction(e);
		hbox.getChildren().add(button);
	}
	
	public Pane generateSimulationScreenControls() {
		VBox vbox = new VBox(10);
		Button speed = (new ButtonString(myResource.getString("SpeedUp"))).getButton();
		speed.setPrefWidth(myScene.getWidth() * .20);
		speed.setOnAction(e -> myNav.createMainMenu());
		Button slow = (new ButtonString(myResource.getString("SlowDown"))).getButton();
		slow.setPrefWidth(myScene.getWidth() * .20);
		slow.setOnAction(e -> myNav.createMainMenu());
		Button step = (new ButtonString(myResource.getString("Step"))).getButton();
		step.setPrefWidth(myScene.getWidth() * .20);
		step.setOnAction(e -> myNav.createMainMenu());
		vbox.getChildren().addAll(speed, slow, step);
		vbox.setLayoutX(myScene.getWidth() * .73);
		vbox.setLayoutY(myScene.getHeight() * .15);
		return (Pane) vbox;
	}
	
	public Node generateSimulationScreenLabel() {
		Label label = (new Header(myResource.getString("SimulationLabel"))).getLabel();
		label.setLayoutX(myScene.getWidth() * .07);
		label.setLayoutY(myScene.getHeight() * .06);
		return label;
	}
	
	
}
