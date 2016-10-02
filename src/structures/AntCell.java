package structures;

public class AntCell extends Cell {
	
	private int numAnts;
	private boolean hasFoodItem;
	private int orientation;
	private int numHomePheromones;
	private int numFoodPheromones;
	
	public AntCell(Integer cellIndex, Integer cellState){
		super(cellIndex, cellState);
		this.numHomePheromones = 0;
		this.numFoodPheromones = 0;
		this.hasFoodItem = false;
		this.numAnts = 0;
	}
	
	public int getNumAnts() {
		return numAnts;
	}

	public void setNumAnts(int numAnts) {
		this.numAnts = numAnts;
	}

	public boolean hasFoodItem() {
		return hasFoodItem;
	}

	public void setHasFoodItem(boolean hasFoodItem) {
		this.hasFoodItem = hasFoodItem;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
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
