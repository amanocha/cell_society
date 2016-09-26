package structures;

public class SegregationCell extends Cell { 
	
	private double satisfaction;
	
	public SegregationCell(int num, int state, double sat) {
		super(num, state);
		satisfaction = sat;
	}
	
	public double getSatisfaction() {
		return satisfaction;
	}

}
