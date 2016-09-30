package engine;

import animation.simulation.GUISimulation;
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
	private Group root;
	private Scene myScene;
	private XmlMapper myInfo;
	private GUISimulation mySimulationPane;

	
	public Loop(MetaData meta, Grid grid) {
		this.animation = new Timeline();
		this.grid = grid;
	}
	
	public Loop(Scene s, XmlMapper info, Group r) {
		this.animation = new Timeline();
		this.myInfo = info;
		this.root = r;
		this.myScene = s;
		mySimulationPane = new GUISimulation(myScene, myInfo, animation);
		this.grid = info.getGrid();
		update = info.getMeta().getUpdate();
	}

	public void init() {
		root.getChildren().add(mySimulationPane.generateSimulationScreen());
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step() {
			root.getChildren().remove(mySimulationPane.getStackPane());
			update.determineUpdates();
			update.updateCells();
			root.getChildren().add(mySimulationPane.generateSimulationScreen());
			//System.out.println("draw grid");
			int count = 0;
			for(int i = 0; i < grid.getRows(); i++) {
				for (int j = 0; j < grid.getColumns(); j++) {
					//System.out.print(grid.getCellList().get(count).getCurrentState() + " ");
					count++;
				}
				//System.out.print();
			}
			//System.out.println();
		root.getChildren().remove(mySimulationPane.getStackPane());
		update.determineUpdates();
		update.updateCells();
		root.getChildren().add(mySimulationPane.generateSimulationScreen());
		//root.getChildren().add(mySimulationPane.generatSimulationChart());
	}
	
	public GUISimulation getSimulationGUI() {
		return mySimulationPane;
	}


}