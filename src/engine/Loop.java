package engine;

import animation.menu.Navigation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import readxml.XmlMapper;
import structures.Grid;

public class Loop {
	private static final int FRAMES_PER_SECOND = 2;
    private static final int MILLISECOND_DELAY = 1000/FRAMES_PER_SECOND;
    
	private Timeline animation;
	private XmlMapper xml = new XmlMapper();
	private Grid grid;
	private Update update;
	private Navigation navigator;
	
	public Loop(Navigation nav) {
		animation = new Timeline();
		grid = xml.mapXmlToGrid("PredatorPrey.xml");
		update = new UpdatePredatorPrey(grid, Integer.parseInt(grid.getGlobalsMap().get("fishTime")), Integer.parseInt(grid.getGlobalsMap().get("sharkTime")));
		this.navigator = nav;
	}
	

	public void init() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void step() {
		System.out.println("draw grid");
				int count = 0;
				for(int i = 0; i < grid.getRows(); i++) {
					for (int j = 0; j < grid.getColumns(); j++) {
						System.out.print(grid.getCellList().get(count).getCurrentState() + " ");
						count++;
					}
					System.out.println();
				}
		navigator.refreshSimulationMenu(grid);
		update.determineUpdates();
		update.updateCells();
	}
	
	public void stop() {
		animation.stop();
	}
	
	public void restart() {
		animation.playFromStart();
	}
	
	public void cont() {
		animation.play();
	}

	public Grid getGrid() {
		return grid;
	}
}