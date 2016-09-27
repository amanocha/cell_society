package structures;

import animation.simulation.AbstractDraw;
import animation.simulation.FireSimulation;
import animation.simulation.GameOfLifeSimulation;
import animation.simulation.PredatorPreySimulation;
import animation.simulation.SegregationSimulation;
import engine.Update;
import engine.UpdateFire;
import engine.UpdateGameOfLife;
import engine.UpdatePredatorPrey;
import engine.UpdateSegregation;

public class MetaData {

	public String SimulationName;
	public String Shape; 
	public Update myUpdate;
	private AbstractDraw mySimulation;
	private int Energy;
	

	public void setSimulationName(String name, Grid grid) {
		this.SimulationName = name;
		if (name.equals("fire")) {
			myUpdate = new UpdateFire(grid); 
			mySimulation = new FireSimulation();
		}
		if (name.equals("predator prey")) {
			myUpdate = new UpdatePredatorPrey(grid);
			mySimulation = new PredatorPreySimulation();
		}
		if (name.equals("game of life")) {
			myUpdate = new UpdateGameOfLife(grid);
			mySimulation = new GameOfLifeSimulation();
		}
		if (name.equals("segregation")) {
			myUpdate = new UpdateSegregation(grid);
			mySimulation = new SegregationSimulation();
		}
	}
	
	public AbstractDraw getSimulation() {
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

	public void setEnergy(int integer) {
		Energy = integer;
	}
	
	public int getEnergy() {
		return Energy;
	}
	
	
}
