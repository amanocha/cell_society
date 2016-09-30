package animation.simulation;

import animation.controls.button.ButtonGo;
import animation.controls.button.ButtonPause;
import animation.controls.button.ButtonStop;
import animation.simulation.shape.GridShape;
import animation.simulation.shape.TriangleGrid;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import readxml.XmlMapper;
import structures.Grid;


public class GUISimulation {
	
	private StackPane stack;
	private GridShape mySimulation;
	private Pane animation;
	private Scene myScene;
	private Timeline engine;
	
	public GUISimulation(Scene scene, XmlMapper info, Timeline animation) {
		this.myScene = scene;
		mySimulation = new TriangleGrid(info.getMeta());
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
	    animation = ((TriangleGrid) mySimulation).drawGrid(grid, width, height);
        StackPane.setMargin(animation, new Insets(left, top, other, other));
        stack.getChildren().add(animation);
        stack.setMouseTransparent(true);
		return stack;
	}
	
	public Pane generateSimulationScreenButton() {
		Button play = (new ButtonGo()).getButton();
		play.setOnAction(e -> engine.play());
		Button pause = (new ButtonPause()).getButton();
		pause.setOnAction(e -> stopAnimation());
		Button stop = (new ButtonStop()).getButton();
		stop.setOnAction(e -> {
			stopAnimation();
			engine.playFromStart();
		});
		HBox hbox = new HBox((myScene.getWidth() * .40) / 3);
		hbox.getChildren().addAll(play, pause, stop);
		hbox.setLayoutX(myScene.getWidth() * .08);
		hbox.setLayoutY(myScene.getHeight() * .90);
		return hbox;
	}
	
	/*public LineChart generatSimulationChart(double x, double y) {
		XAxis x = new XAxis();
		YAxis y = new YAxis();
		LineChart chart = new LineChart(null, null); 
		chart.addPoint(x, y);
		return (LineChart) chart.getControl();
	}*/
	
	public void stopAnimation() {
		engine.stop();
	}
	
}
