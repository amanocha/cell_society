package animation.simulation;


import java.util.Iterator;

import javafx.scene.layout.TilePane;
import readxml.XmlMapper;
import structures.Cell;
import structures.Grid;


public class Simulation {
	
	private AbstractDraw sim;
	private TilePane screen;
	private XmlMapper xml;
	private String SimulationName;
	private Grid myGrid;
	
	public Simulation() {
		this.xml = new XmlMapper();
		this.SimulationName = xml.mapXmlToGrid("PredatorPrey.xml").getNameOfSimulation();
		this.sim = new PredatorPreySimulation(); 
		this.screen = new TilePane();
	}
	
	public TilePane drawGrid(Grid grid, int w, int h) {
		screen.getChildren().clear();
		Iterator<Cell> itr = grid.iterator();
		int width = (int) w / (grid.getColumns());
		int height = (int) h / (grid.getRows());
		screen.setPrefColumns(grid.getColumns());
		screen.setMaxWidth(w + 2 * grid.getColumns());
		screen.setMaxHeight(h + 2 * grid.getRows());
		while(itr.hasNext()) {
			Cell current = itr.next();
			screen.getChildren().add(sim.fillGrid(current, width, height));
		}
		return screen;
	}
	
	public TilePane getScreen() {
		return screen;
	}
	
}
