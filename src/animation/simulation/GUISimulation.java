package animation.simulation;

import animation.controls.GeneralBox;
import animation.controls.GeneralButton;
import animation.controls.GeneralBox.Orientation;
import animation.controls.GeneralButton.Function;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import readxml.XmlMapper;
import structures.Grid;


public class GUISimulation {
	
	private StackPane stack;
	private Simulation mySimulation;
	private Pane animation;
	private Scene myScene;
	private Timeline engine;
	
	public GUISimulation(Scene scene, XmlMapper info, Timeline animation) {
		this.myScene = scene;
		mySimulation = new TriangleSimulation(info);
		stack = new StackPane();
		this.engine = animation;
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
	    animation = ((TriangleSimulation) mySimulation).drawGrid(grid, width, height);
        StackPane.setMargin(animation, new Insets(left, top, other, other));
        stack.getChildren().add(animation);
        stack.setMouseTransparent(true);
		return stack;
	}
	
	public Pane generateSimulationScreenButton() {
		GeneralButton play = new GeneralButton(Function.START);
		play.setStringAction(e -> engine.play());
		GeneralButton pause = new GeneralButton(Function.PAUSE);
		pause.setStringAction(e -> stopAnimation());
		GeneralButton stop = new GeneralButton(Function.STOP);
		stop.setStringAction(e -> {
			stopAnimation();
			engine.playFromStart();
		});
		GeneralBox hbox = new GeneralBox((myScene.getWidth() * .40) / 3, Orientation.HORIZANTAL);
		hbox.addAll(play.getControl(), pause.getControl(), stop.getControl());
		hbox.setX(myScene.getWidth() * .08);
		hbox.setY(myScene.getHeight() * .90);
		return (Pane) hbox.getControl();
	}
	
	public void stopAnimation() {
		engine.stop();
	}
	
}
