package structures;

public class FireCell extends Cell {
	
	private Double probsCatch;
	
	public FireCell(int cell_num, int cell_state, Double prob) {
		super(cell_num, cell_state);
		probsCatch = prob;
	}
	
	
	public Double getProbCatch() {
		return probsCatch;
	}

}
