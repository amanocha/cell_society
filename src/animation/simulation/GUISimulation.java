package animation.simulation;

import animation.controls.button.ButtonGo;
import animation.controls.button.ButtonPause;
import animation.controls.button.ButtonStop;
import animation.controls.button.ButtonString;
import animation.simulation.shape.GridShape;
import animation.simulation.shape.HexagonGrid;
import animation.simulation.shape.TriangleGrid;
import animation.simulation.shape.SquareGrid;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import readxml.XmlMapper;



public class GUISimulation {
	
	private StackPane stack;
	private GridShape mySimulation;
	private Pane animation;
	private Scene myScene;
	private Timeline engine;
	private XmlMapper myInfo;
	private ScrollPane scroll;
	
	public GUISimulation(Scene scene, XmlMapper info, Timeline animation) {
		this.myScene = scene;
		mySimulation = new TriangleGrid(info.getMeta());
		stack = new StackPane();
		this.engine = animation;
		myInfo = info;
		scroll = new ScrollPane();
	}
	
	public Pane getStackPane() {
		return stack;
	}

	public Pane generateSimulationScreen() {
		stack.getChildren().remove(scroll);
		double left = myScene.getWidth() * .1;
	    double top = myScene.getHeight() * .53;
	    double other = myScene.getHeight() * .1;
	    int width = (int) Math.round((myScene.getWidth() * .6 - other));
	    int height = (int) Math.round((myScene.getWidth() * .6 - other));
	    scroll.setMaxWidth(width);
	    scroll.setMaxHeight(height);
	    scroll.setFitToWidth(true);
	    scroll.setFitToHeight(true);
	    scroll.setPrefSize(width, height);
	    scroll.setPrefViewportWidth(width);
	    scroll.setPrefViewportHeight(height); 
	    scroll.setMinViewportHeight(height);
	    scroll.setMinViewportWidth(width);
	    animation = ((TriangleGrid) mySimulation).drawGrid(myInfo.getGrid(), width, height);
	    scroll.setContent(animation);
	    StackPane.setMargin(scroll, new Insets(left, top, other, other));
        stack.getChildren().add(scroll);
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
	
	public Pane generateSimulationScreenControls() {
		VBox vbox = new VBox(10);
		Button speed = (new ButtonString("SPEED UP")).getButton();
		speed.setPrefWidth(myScene.getWidth() * .20);
		speed.setOnAction(e -> engine.setRate(engine.getCurrentRate() + 2));
		Button slow = (new ButtonString("SLOW DOWN")).getButton();
		slow.setPrefWidth(myScene.getWidth() * .20);
		slow.setOnAction(e -> engine.setRate(engine.getCurrentRate() - 2));
		Button step = (new ButtonString("STEP")).getButton();
		step.setPrefWidth(myScene.getWidth() * .20);
		step.setOnAction(e -> step());
		vbox.getChildren().addAll(speed, slow, step);
		vbox.setLayoutX(myScene.getWidth() * .73);
		vbox.setLayoutY(myScene.getHeight() * .15);
		return (Pane) vbox;
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
	
	private void step() {
		engine.setCycleCount(1);
	}
	
}
