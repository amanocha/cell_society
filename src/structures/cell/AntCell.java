package structures.cell;

import java.util.ArrayList;
import java.util.List;

public class AntCell extends Cell {
	
	private int numHomePheromones;
	private int numFoodPheromones;
	private List<Ant> ants;
	
	public AntCell(Integer cellIndex, Integer cellState){
		super(cellIndex, cellState);
		this.numHomePheromones = 0;
		this.numFoodPheromones = 0;
		this.ants = new ArrayList<Ant>();
	}
	
	public Ant getAnt(int index) {
		return ants.get(index);
	}
	public List<Ant> getAntsList() {
		return ants;
	}
	public int getNumAnts() {
		return ants.size();
	}
	public int getNumHomePheromones() {
		return numHomePheromones;
	}
	public void setNumHomePheromones(int numHomePheromones) {
		this.numHomePheromones = numHomePheromones;
	}
	public int getNumFoodPheromones() {
		return numFoodPheromones;
	}
	public void setNumFoodPheromones(int numFoodPheromones) {
		this.numFoodPheromones = numFoodPheromones;
	}
}