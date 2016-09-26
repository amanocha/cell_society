package engine;

import animation.simulation.SimulationPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import readxml.XmlMapper;
import structures.Grid;
import structures.MetaData;

public class Loop {
	private static final int FRAMES_PER_SECOND = 5;
    private static final int MILLISECOND_DELAY = 1000/FRAMES_PER_SECOND;
    
	private Timeline animation;
	private Grid grid;
	private Update update;
	private StatusOfSimulation status;
	private MetaData myMeta;
	private Group root;
	private Scene myScene;
	private XmlMapper myInfo;
	private SimulationPane mySimulationPane;
	
	public enum StatusOfSimulation {
		CONTINUE, STOP
	}
	
	public Loop(MetaData meta, Grid grid) {
		this.animation = new Timeline();
		this.status = StatusOfSimulation.CONTINUE;
		this.grid = grid;
	}
	
	public Loop(Scene s, XmlMapper info, Group r) {
		this.animation = new Timeline();
		this.status = StatusOfSimulation.CONTINUE;
		this.myInfo = info;
		this.root = r;
		this.myScene = s;
		mySimulationPane = new SimulationPane(myScene, myInfo);
		this.grid = info.getGrid();
		update = info.getMeta().getUpdate();
	}

	public void init() {
		root.getChildren().add(mySimulationPane.generateSimulationScreen(grid));
		System.out.println("initial grid");
		int count = 0;
		for(int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getColumns(); j++) {
				System.out.print(grid.getCellList().get(count).getCurrentState() + " ");
				count++;
			}
			System.out.println();
		}
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step() {
			root.getChildren().remove(mySimulationPane.getStackPane());
			update.determineUpdates();
			update.updateCells();
			root.getChildren().add(mySimulationPane.generateSimulationScreen(grid));
			System.out.println("draw grid");
			int count = 0;
			for(int i = 0; i < grid.getRows(); i++) {
				for (int j = 0; j < grid.getColumns(); j++) {
					System.out.print(grid.getCellList().get(count).getCurrentState() + " ");
					count++;
				}
				System.out.println();
			}
	}
	
	public void stop() {
		animation.stop();
		status = StatusOfSimulation.STOP;
	}
	
	public Timeline getAnimation() {
		return animation;
	}

	public Grid getGrid() {
		return grid;
	}
}