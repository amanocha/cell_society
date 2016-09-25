package engine;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import readxml.XmlMapper;
import structures.Grid;



public class Loop {
	public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000/FRAMES_PER_SECOND;
    
	private Timeline animation;
	private XmlMapper xml = new XmlMapper();
	private Grid grid;
	private Update update;
	
	public Loop() {
		animation = new Timeline();
		grid = xml.mapXmlToGrid("GameOfLife.xml");
		update = new UpdateGameOfLife(grid);
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
		
	}
}