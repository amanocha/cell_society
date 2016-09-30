package structures;

import animation.simulation.AbstractDraw;
import animation.simulation.FireSimulation;
import animation.simulation.GameOfLifeSimulation;
import animation.simulation.PredatorPreySimulation;
import animation.simulation.SegregationSimulation;
import engine.Neighbors;
import engine.SquareNeighbors;
import engine.TriangleNeighbors;
import engine.HexagonalNeighbors;
import engine.Update;
import engine.UpdateFire;
import engine.UpdateGameOfLife;
import engine.UpdatePredatorPrey;
import engine.UpdateSegregation;

public class MetaData {

	private String simulationName;
	private String shape; 
	private Neighbors myNeighbors;
	private Update myUpdate;
	private AbstractDraw mySimulation;

	public void setCellShape(String name, Grid grid) {
		this.shape = name;
		if (shape.equals("square")) {
			myNeighbors = new SquareNeighbors(grid);
		}
		if (shape.equals("triangle")) {
			myNeighbors = new TriangleNeighbors(grid);
		}
		if (shape.equals("hexagon")) {
			myNeighbors = new HexagonalNeighbors(grid);
		}
	}
	
	public void setSimulationName(String name, Grid grid) {
		this.simulationName = name;
		if (simulationName.equals("fire")) {
			myUpdate = new UpdateFire(grid, myNeighbors); 
			mySimulation = new FireSimulation();
		}
		if (simulationName.equals("predator prey")) {
			myUpdate = new UpdatePredatorPrey(grid, myNeighbors);
			mySimulation = new PredatorPreySimulation();
		}
		if (simulationName.equals("game of life")) {
			myUpdate = new UpdateGameOfLife(grid, myNeighbors);
			mySimulation = new GameOfLifeSimulation();
		}
		if (simulationName.equals("segregation")) {
			myUpdate = new UpdateSegregation(grid, myNeighbors);
			mySimulation = new SegregationSimulation();
		}
	}

	/*****GETTERS*****/
	
	public String getSimulationName() {
		return simulationName;
	}
	
	public String getShape() {
		return shape;
	}
	
	public Neighbors getNeighbors() {
		return myNeighbors;
	}
	
	public Update getUpdate() {
		return myUpdate;
	}
	
	public AbstractDraw getSimulation() {
		return mySimulation;
	}
	
	/*****SETTERS*****/
	
	public void setSimulationName(String simulationName) {
		this.simulationName = simulationName;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
}
