package engine;

import animation.menu.Navigation;
import animation.menu.Navigation.Menu;
import animation.simulation.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import readxml.XmlMapper;
import structures.Grid;

public class Loop {
	private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000/FRAMES_PER_SECOND;
    
	private Timeline animation;
	private XmlMapper xml = new XmlMapper();
	private Grid grid;
	private Update update;
	private Navigation navigator;
	
	public Loop(Navigation nav) {
		animation = new Timeline();
		grid = xml.mapXmlToGrid("SpreadingOfFire.xml");
		update = new UpdateFire(grid);
		this.navigator = nav;
	}
	

	public void init() {
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
		navigator.refreshSimulationMenu(grid);
		update.determineUpdates();
		update.updateCells();
	}

	public Grid getGrid() {
		return grid;
	}
}