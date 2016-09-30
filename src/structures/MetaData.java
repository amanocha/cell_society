package structures;

import animation.simulation.color.CellColor;
import animation.simulation.color.FireColor;
import animation.simulation.color.GameOfLifeColor;
import animation.simulation.color.PredatorPreyColor;
import animation.simulation.color.SegregationColor;
import engine.Update;
import engine.UpdateFire;
import engine.UpdateGameOfLife;
import engine.UpdatePredatorPrey;
import engine.UpdateSegregation;

public class MetaData {

	public String SimulationName;
	public String Shape; 
	public Update myUpdate;
	private CellColor mySimulation;
	private String myFile;
	

	public void setSimulationName(String name, Grid grid) {
		this.SimulationName = name;
		if (name.equals("fire")) {
			myUpdate = new UpdateFire(grid); 
			mySimulation = new FireColor();
		}
		if (name.equals("predator prey")) {
			myUpdate = new UpdatePredatorPrey(grid);
			mySimulation = new PredatorPreyColor();
		}
		if (name.equals("game of life")) {
			myUpdate = new UpdateGameOfLife(grid);
			mySimulation = new GameOfLifeColor();
		}
		if (name.equals("segregation")) {
			myUpdate = new UpdateSegregation(grid);
			mySimulation = new SegregationColor();
		}
	}
	
	public String getFileName() {
		return myFile;
	}
	
	public void setFileName(String file) {
		myFile = file;
	}
	
	public CellColor getColor() {
		return mySimulation;
	}
	
	public Update getUpdate() {
		return myUpdate;
	}
	
	public void setShape(String shape) {
		this.Shape = shape;
	}

	public String getShape() {
		return Shape;
	}
	
	public String getSimulationName() {
		return SimulationName;
	}
	
	
}
