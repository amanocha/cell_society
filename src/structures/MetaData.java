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
import engine.update.Update;
import engine.update.UpdateFire;
import engine.update.UpdateGameOfLife;
import engine.update.UpdatePredatorPrey;
import engine.update.UpdateSegregation;

public class MetaData {


	private String simulationName;
	private Neighbor myNeighbor;
	private Update myUpdate;
	private CellColor myColor;
	private String myFile;
	private GridShape myShape;
	private String shape;

	public void setCellShape(Grid grid, String name, String wrapping) {
		this.shape = name;
		if (shape.equals("square")) {
			myNeighbor = new SquareNeighbors(grid, wrapping);
			myShape = new SquareGrid();
		}
		if (shape.equals("triangle")) {
			myNeighbor = new TriangleNeighbors(grid, wrapping);
			myShape = new TriangleGrid();
		}
		if (shape.equals("hexagon")) {
			myNeighbor = new HexagonalNeighbors(grid, wrapping);
			myShape = new HexagonGrid();
		}
	}
	
	public void setSimulationName(String name, Grid grid) {
		this.simulationName = name;
		if (name.equals("fire")) {
			myUpdate = new UpdateFire(grid, myNeighbor); 
			myColor = new FireColor();
		}
		if (name.equals("predator prey")) {
			myUpdate = new UpdatePredatorPrey(grid, myNeighbor);
			myColor = new PredatorPreyColor();
		}
		if (name.equals("game of life")) {
			myUpdate = new UpdateGameOfLife(grid, myNeighbor);
			myColor = new GameOfLifeColor();
		}
		if (name.equals("segregation")) {
			myUpdate = new UpdateSegregation(grid, myNeighbor);
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
	
	public GridShape getGridShape() {
		return myShape;
	}
	
	public Neighbor getNeighbors() {
		return myNeighbor;
	}
	
	public Update getUpdate() {
		return myUpdate;
	}
	
	public String getShape() {
		return shape;
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
