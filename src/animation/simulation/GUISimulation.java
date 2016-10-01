package animation.simulation;

import animation.controls.button.ButtonGo;
import animation.controls.button.ButtonPause;
import animation.controls.button.ButtonStop;
import animation.simulation.shape.GridShape;
import animation.simulation.shape.HexagonGrid;
import animation.simulation.shape.TriangleGrid;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import readxml.XmlMapper;



public class GUISimulation {
	
	private StackPane stack;
	private GridShape mySimulation;
	private Pane animation;
	private Scene myScene;
	private Timeline engine;
	private XmlMapper myInfo;
	
	public GUISimulation(Scene scene, XmlMapper info, Timeline animation) {
		this.myScene = scene;
		mySimulation = new HexagonGrid(info.getMeta());
		stack = new StackPane();
		this.engine = animation;
		myInfo = info;
	}
	
	public Pane getStackPane() {
		return stack;
	}

	public Pane generateSimulationScreen() {
		stack.getChildren().remove(animation);
		double left = myScene.getWidth() * .15;
	    double top = myScene.getHeight() * .55;
	    double other = myScene.getHeight() * .1;
	    int width = (int) Math.round((myScene.getWidth() * .4 - other));
	    int height = (int) Math.round((myScene.getHeight() * .7));
	    animation = ((HexagonGrid) mySimulation).drawGrid(myInfo.getGrid(), width, height);
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
			myInfo.mapXml(myInfo.getMeta().getFileName());
			engine.play();
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
