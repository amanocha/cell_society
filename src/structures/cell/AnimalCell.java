package structures.cell;

/**
 * This is the AnimalCell class which extends the Cell class to include all of the cell properties as well as the extra
 * properties of a cell in the Wator simulation.
 * 
 * @author Aninda Manocha
 */

public class AnimalCell extends Cell {
	private int time;
	private int energy;
	private int fishTime;
	private int sharkTime;
	
	/**
	 * Default constructor
	 */
	public AnimalCell() {
		super();
		time = 0;
	}
	
	/**
	 * Constructor
	 * @param cell_num - the cell number
	 * @param cell_state - the cell's current (initial) state
	 * @param initEnergy - the initial amount of energy a shark has
	 * @param fish - the number of ticks before a fish reproduces
	 * @param shark - the number of ticks before a shark reproduces
	 */
	public AnimalCell(int cell_num, int cell_state, int initEnergy, int fish, int shark) {
		super(cell_num, cell_state);
		time = 0;
		energy = initEnergy;
		this.fishTime = fish;
		this.sharkTime = shark;
	}
	
	/*****GETTERS*****/
	
	public int getTime() {
		return time;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getFishTime() {
		return fishTime;
	}
	
	public int getSharkTime() {
		return sharkTime;
	}
	
	/*****SETTERS*****/
	//These variables are the ones that vary with each cell; the other variables stay constant within a simulation.
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
