package animation.menu;

import java.util.ResourceBundle;

import animation.controls.GeneralBox;
import animation.controls.GeneralBox.Orientation;
import animation.controls.GeneralButton;
import animation.controls.GeneralLabel;
import animation.controls.GeneralPane;
import animation.menu.Navigation.Menu;
import engine.Loop;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;



public class GUIGenerator {
	
	private static String[] SIMULATIONS = {"SEGREGATION", "GAME OF LIFE", "FIRE", "WA-TOR"};
	private static String[] FILES = {"Segregation.xml", "GameOfLife.xml", "Fire.xml", "PredatorPrey.xml"};
	
	private GeneralPane myPane;
	private Navigation myNav;
	private Scene myScene;
	private ResourceBundle myResource;
	private XmlMapper myInfo;
	
	public GUIGenerator(Scene scene, Group r, ResourceBundle resource, XmlMapper info) {
		this.myScene = scene;
		this.myResource = resource;
		myPane = new GeneralPane(scene);
		myInfo = info;
		myNav = new Navigation(scene, r, info);
	}


	public Pane generateMainScreen() {
		GridPane grid = myPane.getMainMenuPane();
		GeneralLabel header = new GeneralLabel(myResource.getString("Title")); 
		GeneralButton button = new GeneralButton(myResource.getString("Start"));
		button.setWidth(myScene.getWidth() * .5);
		button.setStringAction(e -> {
			myNav.makeScreen(Menu.SIMULATION);
		});
		grid.add(header.getHeader(), 1, 0);
		grid.add(button.getControl(), 1, 1);
		button = new GeneralButton(myResource.getString("ParameterButton"));
		button.setWidth(myScene.getWidth() * .5);
		button.setStringAction(e -> myNav.makeScreen(Menu.XML));
		grid.add(button.getControl(), 1, 2);
		GeneralLabel text = new GeneralLabel(myResource.getString("CurrentSimulation"));
		text.setWidth(myScene.getWidth() * .5);
		grid.add(text.getMessage(), 1, 4);
		return grid;
	}
	
	public Button generateSimulationScreenMainButton(Loop loop) {
		GeneralButton main = new GeneralButton(myResource.getString("MainMenu"));
		main.setStringAction(e -> {
			myNav.makeScreen(Menu.MAIN);
			loop.getSimulationGUI().stopAnimation();
		});
		main.setWidth(myScene.getWidth() * .20);
		main.setX(myScene.getWidth() * .73);
		main.setY(myScene.getHeight() * .8);
		return (Button) main.getControl();
	}
	
	private GeneralButton generateMainMenuButton() {
		GeneralButton main = new GeneralButton(myResource.getString("MainMenu"));
		main.setStringAction(e -> myNav.makeScreen(Menu.MAIN));
		return main;
	}

	public Pane generateXMLScreen() {
		Pane grid = myPane.getXMLMenuPane();
		GeneralButton button = new GeneralButton(myResource.getString("MainMenu"));
		button.setWidth(myScene.getWidth() * .25);
		button.setStringAction(e -> myNav.makeScreen(Menu.MAIN));
		GeneralBox hbox = new GeneralBox((myScene.getWidth() * .01), Orientation.HORIZANTAL);
		for (int i = 0; i < SIMULATIONS.length; i++) {
			String file = FILES[i];
			setXMLSelectionButtons(SIMULATIONS[i], e -> 
				{myInfo.mapXmlToGrid(file); 
				myNav.makeScreen(Menu.MAIN);}, hbox);
		}
		GeneralLabel title = new GeneralLabel(myResource.getString("SimulationSelection"));
		title.setX(myScene.getWidth() * .32);
		title.setY(myScene.getHeight() * .2);
		hbox.setX(myScene.getWidth() * .08);
		hbox.setY(myScene.getHeight() * .35);
		button.setX(myScene.getWidth() * .38);
		button.setY(myScene.getHeight() * .6);
		grid.getChildren().add(title.getHeader());
		grid.getChildren().add(button.getControl());
		grid.getChildren().add(hbox.getControl());
		return grid;
	}
	
	private void setXMLSelectionButtons(String sim, EventHandler<ActionEvent> e, GeneralBox hbox) {
		GeneralButton button = new GeneralButton(sim);
		button.setWidth(myScene.getWidth() * .20);
		button.setStringAction(e);
		hbox.add(button.getControl());
	}
	
	public Pane generateSimulationScreenControls() {
		GeneralBox vbox = new GeneralBox(10, Orientation.VERTICAL);
		GeneralButton speed = new GeneralButton(myResource.getString("SpeedUp"));
		speed.setWidth(myScene.getWidth() * .20);
		speed.setStringAction(e -> myNav.makeScreen(Menu.MAIN));
		GeneralButton slow = new GeneralButton(myResource.getString("SlowDown"));
		slow.setWidth(myScene.getWidth() * .20);
		slow.setStringAction(e -> myNav.makeScreen(Menu.MAIN));
		GeneralButton step = new GeneralButton(myResource.getString("Step"));
		step.setWidth(myScene.getWidth() * .20);
		step.setStringAction(e -> myNav.makeScreen(Menu.MAIN));
		vbox.addAll(speed.getControl(), slow.getControl(), step.getControl());
		vbox.setX(myScene.getWidth() * .73);
		vbox.setY(myScene.getHeight() * .15);
		return (Pane) vbox.getControl();
	}
	
	public Node generateSimulationScreenLabel() {
		GeneralLabel label = new GeneralLabel(myResource.getString("SimulationLabel"));
		label.setX(myScene.getWidth() * .07);
		label.setY(myScene.getHeight() * .06);
		return label.getHeader();
	}
	
	
}
