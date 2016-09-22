package animation.menu;


import animation.controls.GeneralButton;
import animation.controls.GeneralButton.Type;
import animation.controls.GeneralLabel;
import animation.controls.GeneralPane;
import animation.simulation.Simulation;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class GUIGenerator {
	
	private GeneralPane myPane;
	private Navigation myNav;
	private Scene myScene;
	private Simulation mySimulation;
	
	public GUIGenerator(Scene scene) {
		myScene = scene;
		myPane = new GeneralPane(scene);
		myNav = new Navigation(scene.getWidth(), scene.getHeight());
		mySimulation = new Simulation();
	}
	
	public GridPane generateMainScreen() {
		GridPane grid = myPane.getMainMenuPane();
		GeneralLabel header = new GeneralLabel("Cellular Automata"); 
		GeneralButton button = new GeneralButton("START");
		button.setWidth(myScene.getWidth() * .5);
		button.setStringAction(e -> myNav.simulationMenu());
		grid.add(header.getHeader(), 1, 0);
		grid.add(button.getButton(), 1, 1);
		button = new GeneralButton("CHANGE PARAMETERS");
		button.setWidth(myScene.getWidth() * .5);
		grid.add(button.getButton(), 1, 2);
		return grid;
	}

	public Node generateSimulationScreen() {
		StackPane pane = myPane.getSimulationMenuPane();
		StackPane sim = myPane.createSimulationPane();
		sim.getChildren().add(mySimulation.drawGrid());
		pane.getChildren().add(sim);
		double left = myScene.getHeight() - (((myScene.getHeight()) * 4) / 5);
		double top = myScene.getWidth() - (myScene.getWidth() * 2) / 3;
		double other = myScene.getHeight() -  (myScene.getHeight() * 8) / 9;
		StackPane.setMargin(sim, new Insets(left, top, other, other));
		GeneralButton play = new GeneralButton(Type.START);
		HBox hbox = new HBox(8);
		hbox.getChildren().add(play.getButton());
		pane.getChildren().add(hbox);
		return pane;
	}

	public Node generateXMLScreen() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
