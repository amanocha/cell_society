package structures.cell;
/*
 * Segregation cell for segregation simulation
 */
public class SegregationCell extends Cell { 
	
	private double satisfaction;
	
	public SegregationCell(int num, int state, double satisfaction) {
		super(num, state);
		this.satisfaction = satisfaction;
	}
	
	public double getSatisfaction() {
		return satisfaction;
	}

}
