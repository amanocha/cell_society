package engine;

import animation.menu.Navigation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
		navigator = nav;
	}
	
	public void init() {
		navigator.simulationMenuRefresh(grid);
		//KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
		//animation.setCycleCount(Timeline.INDEFINITE);
		//animation.getKeyFrames().add(frame);
		//animation.play();
	}
	
	public void step() {
		update.determineUpdates();
		update.updateCells();
		navigator.simulationMenuRefresh(grid);
	}
}