package structures.cell;

/**
 * This is the SugarCell class which extends the Cell class to include all of the cell properties as well as the extra
 * properties of a cell in the Sugar simulation.
 * 
 * @author Aninda Manocha
 */

public class SugarCell extends Cell {
	private int sugar;
	private int sugarTime;
	private int sugarGrowBackTime;
	private int vision;
	private int sugarMetabolism;
	private int patchSugar;
	private int patchSugarCapacity;
	
	/**
	 * Default constructor
	 */
	public SugarCell() {
		super();
		sugar = 10;
		sugarTime = 0;
	}
	
	/**
	 * Constructor
	 * @param cell_num - the cell number
	 * @param cell_state - the cell's current (initial) state
	 * @param initSugar - the initial amount of sugar an agent has
	 * @param sugarGrowBackTime - the number of ticks before a patch gains sugar
	 * @param vision - upper bound on the distance between a given cell and its surrounding neighbor
	 * @param sugarMetabolism - the amount of sugar an agent loses each tick
	 */
	public SugarCell(int cell_num, int cell_state, int initSugar, int sugarGrowBackTime, int vision, int sugarMetabolism) {
		super(cell_num, cell_state);
		sugar = initSugar;
		sugarTime = 0;
		this.sugarGrowBackTime = sugarGrowBackTime;
		this.vision = vision;
		this.sugarMetabolism = sugarMetabolism;
		patchSugar = cell_state;
		this.patchSugarCapacity = cell_state;
	}
	
	/*****GETTERS*****/
	
	public int getSugar() {
		return sugar;
	}
	
	public int getSugarTime() {
		return sugarTime;
	}
	
	public int getSugarGrowBackTime() {
		return sugarGrowBackTime;
	}
	
	public int getVision() {
		return vision;
	}
	
	public int getSugarMetabolism() {
		return sugarMetabolism;
	}
	
	public int getPatchSugar() {
		return patchSugar;
	}
	
	public int getPatchSugarCapacity() {
		return patchSugarCapacity;
	}
	
	/*****SETTERS*****/
	//These variables are the ones that vary with each cell; the other variables stay constant within a simulation.
	
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}
	
	public void setSugarTime(int sugarTime) {
		this.sugarTime = sugarTime;
	}
	
	public void setPatchSugar(int patchSugar) {
		this.patchSugar = patchSugar;
	}
}
