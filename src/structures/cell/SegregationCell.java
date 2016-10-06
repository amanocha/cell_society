package structures.cell;

/**
 * This is the SegregationCell class which extends the Cell class to include all of the cell properties as well as the extra
 * properties of a cell in the Segregation simulation.
 * 
 * @author Aninda Manocha
 */

public class SegregationCell extends Cell { 
	
	private double satisfaction;
	
	/**
	 * Constructor
	 * @param num - the cell number
	 * @param state - the cell's current (initial) state
	 * @param satisfaction - the proportion of neighbors that must be similar in order for a cell to be satisfied
	 */
	public SegregationCell(int num, int state, double satisfaction) {
		super(num, state);
		this.satisfaction = satisfaction;
	}
	
	/**
	 * Gets the satisfaction amount
	 * @return the satisfaction
	 */
	public double getSatisfaction() {
		return satisfaction;
	}

}
