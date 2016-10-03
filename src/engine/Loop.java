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

/**
 * This is the Loop class, which creates a timeline so that the simulations can run by continuously updating cell properties
 * and the grid display. Simulations are run indefinitely, so key frames are created for each iteration through a 
 * simulation. 
 * 
 * @author Aninda Manocha
 */

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

	/**
	 * Constructor that takes in metadata and the grid 
	 * @param meta - the MetaData object
	 * @param grid - the grid
	 */
	public Loop(MetaData meta, Grid grid) {
		this.animation = new Timeline();
		this.grid = grid;
	}
	
	/**
	 * Constructor that is called from the simulation menu in order to run a simulation
	 * @param s - the scene
	 * @param info - the xml mapper
	 * @param r - the root
	 * @param resources - the resources bundle
	 */
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
	
	/**
	 * Initializes the timeline by creating an initial grid and setting up the properties of the Timeline object (keyframes
	 * and cycle count)
	 */
	public void init() {
		root.getChildren().add(mySimulationPane.generateSimulationScreen());
		root.getChildren().add(mySimulationPane.generatSimulationChart(myResources));
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/**
	 * Performs one iteration (tick) in a simulation. With every step, all cells have their neighbors checked in order to
	 * determine whether they need to be updated. Their states are then updated.
	 */
	private void step() {
		root.getChildren().remove(mySimulationPane.getStackPane());
		root.getChildren().remove(mySimulationPane.getLineChart());
		update.determineUpdates();
		update.updateCells();
		root.getChildren().add(mySimulationPane.generateSimulationScreen());
		root.getChildren().add(mySimulationPane.generatSimulationChart(myResources));
	}
	
	/**
	 * Gets access to the GUISimulation class that creates GUIs for the simulation page.
	 * @return the GUISimulation object
	 */
	public GUISimulation getSimulationGUI() {
		return mySimulationPane;
	}


}