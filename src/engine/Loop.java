package engine;

import animation.menu.Navigation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import readxml.XmlMapper;
import structures.Grid;

public class Loop {
	private static final int FRAMES_PER_SECOND = 5;
    private static final int MILLISECOND_DELAY = 1000/FRAMES_PER_SECOND;
    
	private Timeline animation;
	private XmlMapper xml = new XmlMapper();
	private Grid grid;
	private Update update;
	private Navigation navigator;
	private StatusOfSimulation status;
	
	public enum StatusOfSimulation {
		CONTINUE, STOP
	}
	
	public Loop(Navigation nav) {
		this.animation = new Timeline();
		this.grid = xml.mapXmlToGrid("PredatorPrey.xml");
		//this.update = new UpdatePredatorPrey(grid, Integer.parseInt(grid.getGlobalsMap().get("fishTime")), Integer.parseInt(grid.getGlobalsMap().get("sharkTime")));
		this.update = new UpdatePredatorPrey(grid, 1, 15);
		this.navigator = nav;
		this.status = StatusOfSimulation.CONTINUE;
	}

	public void init() {
		navigator.refreshSimulationMenu(grid);
		//System.out.println("initial grid");
		int count = 0;
		for(int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getColumns(); j++) {
				//System.out.print(grid.getCellList().get(count).getCurrentState() + " ");
				count++;
			}
			//System.out.println();
		}
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void step() {
		if (status.equals(StatusOfSimulation.CONTINUE)) {
			update.determineUpdates();
			update.updateCells();
			navigator.refreshSimulationMenu(grid);
			//System.out.println("draw grid");
			int count = 0;
			for(int i = 0; i < grid.getRows(); i++) {
				for (int j = 0; j < grid.getColumns(); j++) {
					//System.out.print(grid.getCellList().get(count).getCurrentState() + " ");
					count++;
				}
				//System.out.println();
			}
		} else {
			animation.stop();
		}
	}
	
	public void stop() {
		System.out.println("STOP");
		status = StatusOfSimulation.STOP;
	}

	public Grid getGrid() {
		return grid;
	}
}