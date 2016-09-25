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
	private static final int FRAMES_PER_SECOND = 60;
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
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void step() {
		update.determineUpdates();
		update.updateCells();
		navigator.makeScreen(Menu.REFRESH);
	}

	public Grid getGrid() {
		return grid;
	}
}