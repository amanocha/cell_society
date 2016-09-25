package animation.menu;


import java.util.ResourceBundle;

import animation.controls.GeneralBox;
import animation.controls.GeneralBox.Orientation;
import animation.controls.GeneralButton;
import animation.controls.GeneralButton.Function;
import animation.controls.GeneralLabel;
import animation.controls.GeneralPane;
import animation.simulation.Simulation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class GUIGenerator {
	
	private static String[] SIMULATIONS = {"SEGREGATION", "GAME OF LIFE", "FIRE", "WA-TOR"};
	
	private GeneralPane myPane;
	private Navigation myNav;
	private Scene myScene;
	private Simulation mySimulation;
	private ResourceBundle myResource;
	
	public GUIGenerator(Scene scene, Group r, ResourceBundle resource) {
		this.myScene = scene;
		this.myResource = resource;
		myPane = new GeneralPane(scene);
		myNav = new Navigation(scene.getWidth(), scene.getHeight());
		mySimulation = new Simulation();
	}


	public Pane generateMainScreen() {
		GridPane grid = myPane.getMainMenuPane();
		GeneralLabel header = new GeneralLabel(myResource.getString("Title")); 
		GeneralButton button = new GeneralButton(myResource.getString("Start"));
		button.setWidth(myScene.getWidth() * .5);
		button.setStringAction(e -> myNav.simulationMenu());
		grid.add(header.getHeader(), 1, 0);
		grid.add(button.getControl(), 1, 1);
		button = new GeneralButton(myResource.getString("ParameterButton"));
		button.setWidth(myScene.getWidth() * .5);
		button.setStringAction(e -> myNav.xmlMenu());
		grid.add(button.getControl(), 1, 2);
		GeneralLabel text = new GeneralLabel(myResource.getString("CurrentSimulation"));
		text.setWidth(myScene.getWidth() * .5);
		grid.add(text.getMessage(), 1, 4);
		return grid;
	}

	public Pane generateSimulationScreen() {
		StackPane pane = myPane.getSimulationMenuPane();
		int temp = 5;
		TilePane baby = mySimulation.drawGrid(temp);
        double left = myScene.getWidth() * .1;
        double top = myScene.getHeight() * .5;
        double other = myScene.getHeight() * .1;
        StackPane.setMargin(baby, new Insets(left, top, other, other));
        pane.getChildren().add(baby);
		return pane;
	}
	
	
	public Node generateSimulationScreenLabel() {
		GeneralLabel label = new GeneralLabel(myResource.getString("SimulationLabel"));
		label.setX(myScene.getWidth() * .07);
		label.setY(myScene.getHeight() * .06);
		return label.getHeader();
	}
	
	public Pane generateSimulationScreenButton() {
		GeneralButton play = new GeneralButton(Function.START);
		GeneralButton pause = new GeneralButton(Function.PAUSE);
		GeneralButton stop = new GeneralButton(Function.STOP);
		GeneralBox hbox = new GeneralBox((myScene.getWidth() * .45) / 3, Orientation.HORIZANTAL);
		hbox.addAll(play.getControl(), pause.getControl(), stop.getControl());
		hbox.setX(myScene.getWidth() * .14);
		hbox.setY(myScene.getHeight() * .91);
		return (Pane) hbox.getControl();
	}
	
	public Pane generateSimulationScreenControls() {
		GeneralBox vbox = new GeneralBox(10, Orientation.VERTICAL);
		GeneralButton speed = new GeneralButton(myResource.getString("SpeedUp"));
		speed.setWidth(myScene.getWidth() * .20);
		speed.setStringAction(e -> myNav.mainMenu());
		GeneralButton slow = new GeneralButton(myResource.getString("SlowDown"));
		slow.setWidth(myScene.getWidth() * .20);
		slow.setStringAction(e -> myNav.mainMenu());
		GeneralButton step = new GeneralButton(myResource.getString("Step"));
		step.setWidth(myScene.getWidth() * .20);
		step.setStringAction(e -> myNav.mainMenu());
		vbox.addAll(speed.getControl(), slow.getControl(), step.getControl());
		vbox.setX(myScene.getWidth() * .73);
		vbox.setY(myScene.getHeight() * .15);
		return (Pane) vbox.getControl();
	}
	
	public Button generateSimulationScreenMainButton() {
		GeneralButton main = new GeneralButton(myResource.getString("MainMenu"));
		main.setWidth(myScene.getWidth() * .20);
		main.setStringAction(e -> myNav.mainMenu());
		main.setX(myScene.getWidth() * .73);
		main.setY(myScene.getHeight() * .8);
		return (Button) main.getControl();
	}

	public Pane generateXMLScreen() {
		Pane grid = myPane.getXMLMenuPane();
		GeneralButton button = new GeneralButton(myResource.getString("MainMenu"));
		button.setWidth(myScene.getWidth() * .25);
		button.setStringAction(e -> myNav.mainMenu());
		GeneralBox hbox = new GeneralBox((myScene.getWidth() * .01), Orientation.HORIZANTAL);
		for (String sim : SIMULATIONS) {
			setXMLSelectionButtons(sim, e -> myNav.mainMenu(), hbox);
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
		hbox.add(button.getControl());
	}
	
	
}