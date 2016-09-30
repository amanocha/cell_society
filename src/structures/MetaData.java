package structures;

import animation.simulation.color.CellColor;
import animation.simulation.color.FireColor;
import animation.simulation.color.GameOfLifeColor;
import animation.simulation.color.PredatorPreyColor;
import animation.simulation.color.SegregationColor;
import animation.simulation.shape.GridShape;
import animation.simulation.shape.HexagonGrid;
import animation.simulation.shape.SquareGrid;
import animation.simulation.shape.TriangleGrid;
import engine.SquareNeighbors;
import engine.TriangleNeighbors;
import engine.HexagonalNeighbors;
import engine.Neighbor;
import engine.Update;
import engine.UpdateFire;
import engine.UpdateGameOfLife;
import engine.UpdatePredatorPrey;
import engine.UpdateSegregation;

public class MetaData {


	private String simulationName;
	private String shape; 
	private Neighbor myNeighbors;
	private Update myUpdate;
	private CellColor myColor;
	private String myFile;
	private GridShape myShape;

	public void setCellShape(String name, Grid grid) {
		this.shape = name;
		if (shape.equals("square")) {
			myNeighbors = new SquareNeighbors(grid);
			myShape = new SquareGrid();
		}
		if (shape.equals("triangle")) {
			myNeighbors = new TriangleNeighbors(grid);
			myShape = new TriangleGrid();
		}
		if (shape.equals("hexagon")) {
			myNeighbors = new HexagonalNeighbors(grid);
			myShape = new HexagonGrid();
		}
	}
	
	public void setSimulationName(String name, Grid grid) {
		this.simulationName = name;
		if (name.equals("fire")) {
			myUpdate = new UpdateFire(grid, myNeighbors); 
			myColor = new FireColor();
		}
		if (name.equals("predator prey")) {
			myUpdate = new UpdatePredatorPrey(grid, myNeighbors);
			myColor = new PredatorPreyColor();
		}
		if (name.equals("game of life")) {
			myUpdate = new UpdateGameOfLife(grid, myNeighbors);
			myColor = new GameOfLifeColor();
		}
		if (name.equals("segregation")) {
			myUpdate = new UpdateSegregation(grid, myNeighbors);
			myColor = new SegregationColor();
		}
	}

	/*****GETTERS*****/
	
	public String getSimulationName() {
		return simulationName;
	}

	public String getFileName() {
		return myFile;
	}

	
	public CellColor getColor() {
		return myColor;
	}
	
	public String getShape() {
		return shape;
	}
	
	public Neighbor getNeighbors() {
		return myNeighbors;
	}
	
	public Update getUpdate() {
		return myUpdate;
	}

	
	/*****SETTERS*****/
	
	public void setSimulationName(String simulationName) {
		this.simulationName = simulationName;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public void setFileName(String file) {
		myFile = file;
	}
	
}
