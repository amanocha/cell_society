package engine;

import java.util.ResourceBundle;

import animation.simulation.GUISimulation;
import engine.update.Update;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import readxml.XmlMapper;
import structures.Grid;
import structures.MetaData;

public class Loop {
	private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000/FRAMES_PER_SECOND;
    
	private Timeline animation;
	private Grid grid;
	private Update update;
	private Group root;
	private Scene myScene;
	private XmlMapper myInfo;
	private GUISimulation mySimulationPane;
	private ResourceBundle myResources;

	
	public Loop(MetaData meta, Grid grid) {
		this.animation = new Timeline();
		this.grid = grid;
	}
	
	public Loop(Scene s, XmlMapper info, Group r, ResourceBundle resources) {
		this.animation = new Timeline();
		this.myInfo = info;
		this.root = r;
		this.myScene = s;
		mySimulationPane = new GUISimulation(myScene, myInfo, animation);
		this.grid = info.getGrid();
		update = info.getMeta().getUpdate();
		myResources = resources;
	}
	

	public void init() {
		root.getChildren().add(mySimulationPane.generateSimulationScreen());
		root.getChildren().add(mySimulationPane.generatSimulationChart(myResources));
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step() {
		root.getChildren().remove(mySimulationPane.getStackPane());
		root.getChildren().remove(mySimulationPane.getLineChart());
		update.determineUpdates();
		update.updateCells();
		root.getChildren().add(mySimulationPane.generateSimulationScreen());
		root.getChildren().add(mySimulationPane.generatSimulationChart(myResources));
	}
	
	public GUISimulation getSimulationGUI() {
		return mySimulationPane;
	}


}