package animation.menu;


import animation.controls.GeneralBox;
import animation.controls.GeneralBox.Orientation;
import animation.controls.GeneralButton;
import animation.controls.GeneralButton.Type;
import animation.controls.GeneralLabel;
import animation.controls.GeneralPane;
import animation.simulation.Simulation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GUIGenerator {
	
	private GeneralPane myPane;
	private Navigation myNav;
	private Scene myScene;
	private Simulation mySimulation;
	
	public GUIGenerator(Scene scene, Group r) {
		this.myScene = scene;
		myPane = new GeneralPane(scene);
		myNav = new Navigation(scene.getWidth(), scene.getHeight());
		mySimulation = new Simulation();
	}
	
	public Pane generateMainScreen() {
		GridPane grid = myPane.getMainMenuPane();
		GeneralLabel header = new GeneralLabel("Cellular Automata"); 
		GeneralButton button = new GeneralButton("START");
		button.setWidth(myScene.getWidth() * .5);
		button.setStringAction(e -> myNav.simulationMenu());
		grid.add(header.getHeader(), 1, 0);
		grid.add(button.getButton(), 1, 1);
		button = new GeneralButton("CHANGE PARAMETERS");
		button.setWidth(myScene.getWidth() * .5);
		button.setStringAction(e -> myNav.xmlMenu());
		grid.add(button.getButton(), 1, 2);
		GeneralLabel text = new GeneralLabel("THE CURRENT SIMULATION \n \n The current simulation is WA-TOR.");
		text.setWidth(myScene.getWidth() * .5);
		grid.add(text.getMessage(), 1, 4);
		return grid;
	}

	public Pane generateSimulationScreen() {
		StackPane pane = myPane.getSimulationMenuPane();
		return pane;
	}
	
	public Node generateSimulationScreenLabel() {
		GeneralLabel label = new GeneralLabel("Simulation: WA-TOR");
		label.setX(myScene.getWidth() * .07);
		label.setY(myScene.getHeight() * .06);
		return label.getHeader();
	}
	
	public Pane generateSimulationScreenButton() {
		GeneralButton play = new GeneralButton(Type.START);
		GeneralButton pause = new GeneralButton(Type.PAUSE);
		GeneralButton stop = new GeneralButton(Type.STOP);
		GeneralBox hbox = new GeneralBox((myScene.getWidth() * .45) / 3, Orientation.HORIZANTAL);
		hbox.addAll(play.getButton(), pause.getButton(), stop.getButton());
		hbox.setX(myScene.getWidth() * .1);
		hbox.setY(myScene.getHeight() * .91);
		return hbox.getBox();
	}
	
	public Pane generateSimulationScreenControls() {
		GeneralBox vbox = new GeneralBox(10, Orientation.VERTICAL);
		GeneralButton main = new GeneralButton("MAIN MENU");
		main.setWidth(myScene.getWidth() * .25);
		main.setStringAction(e -> myNav.mainMenu());
		GeneralButton speed = new GeneralButton("SPEED UP");
		speed.setWidth(myScene.getWidth() * .25);
		speed.setStringAction(e -> myNav.mainMenu());
		GeneralButton slow = new GeneralButton("SLOW DOWN");
		slow.setWidth(myScene.getWidth() * .25);
		slow.setStringAction(e -> myNav.mainMenu());
		GeneralButton step = new GeneralButton("STEP");
		step.setWidth(myScene.getWidth() * .25);
		step.setStringAction(e -> myNav.mainMenu());
		vbox.addAll(main.getButton(), speed.getButton(), slow.getButton(), step.getButton());
		vbox.setX(myScene.getWidth() * .7);
		vbox.setY(myScene.getHeight() * .2);
		return vbox.getBox();
	}

	public GridPane generateXMLScreen() {
		GridPane grid = myPane.getXMLMenuPane();
		GeneralButton button = new GeneralButton("MAIN MENU");
		button.setWidth(myScene.getWidth() * .25);
		button.setStringAction(e -> myNav.mainMenu());
		GeneralBox hbox = setXMLSelectionButtons("SEGREGATION", "GAME OF LIFE", "FIRE", "WA-TOR");
		GeneralLabel title = new GeneralLabel("Simulation Selection");
		grid.add(title.getHeader(), 0, 0);
		grid.setAlignment(Pos.CENTER);
		grid.add(button.getButton(), 0, 3);
		grid.add(hbox.getBox(), 0, 1);
		return grid;
	}
	
	public GeneralBox setXMLSelectionButtons(String...simulation) {
		GeneralBox hbox = new GeneralBox((myScene.getWidth() * .01), Orientation.HORIZANTAL);
		for (String sim : simulation) {
			GeneralButton button = new GeneralButton(sim);
			button.setWidth(myScene.getWidth() * .20);
			hbox.add(button.getButton());
		}
		return hbox;
	}
	
	
}
