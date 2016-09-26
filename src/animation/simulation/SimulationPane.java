package animation.simulation;

import animation.controls.GeneralPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import readxml.XmlMapper;
import structures.Grid;

public class SimulationPane {
	
	private StackPane stack;
	private GeneralPane myPane;
	private Simulation mySimulation;
	private TilePane animation;
	private Scene myScene;
	
	public SimulationPane(Scene scene, XmlMapper info) {
		this.myScene = scene;
		myPane = new GeneralPane(scene);
		mySimulation = new Simulation(info);
		stack = new StackPane();
	}
	
	public Pane getStackPane() {
		return stack;
	}

	public Pane generateSimulationScreen(Grid grid) {
		stack.getChildren().remove(animation);
		double left = myScene.getWidth() * .15;
	    double top = myScene.getHeight() * .55;
	    double other = myScene.getHeight() * .1;
	    int width = (int) Math.round((myScene.getWidth() * .5 - other));
	    int height = (int) Math.round((myScene.getHeight() * .8));
	    animation = mySimulation.drawGrid(grid, width, height);
        StackPane.setMargin(animation, new Insets(left, top, other, other));
		animation.setTileAlignment(Pos.CENTER);
		animation.setHgap(2);
		animation.setVgap(2);
        stack.getChildren().add(animation);
        stack.setMouseTransparent(true);
		return stack;
	}
}
