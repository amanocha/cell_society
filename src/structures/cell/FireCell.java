package structures.cell;

/**
 * This is the FireCell class which extends the Cell class to include all of the cell properties as well as the extra
 * properties of a cell in the Spread Fire simulation.
 * 
 * @author Aninda Manocha
 */
public class FireCell extends Cell {
	
	private Double probsCatch;
	
	/**
	 * Constructor
	 * @param num - the cell number
	 * @param state - the cell's current (initial) state
	 * @param prob - the probability that a tree catches on fire
	 */
	public FireCell(int cell_num, int cell_state, Double prob) {
		super(cell_num, cell_state);
		probsCatch = prob;
	}
	
	/**
	 * Gets the probability of a tree catching on fire
	 * @return the probability
	 */
	public Double getProbCatch() {
		return probsCatch;
	}

}
