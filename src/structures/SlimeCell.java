package structures;

public class SlimeCell extends Cell {
	
	private Double sniffThreshold;
	
	public SlimeCell(int cell_num, int cell_state, Double sniffThreshold) {
		super(cell_num, cell_state);
		this.sniffThreshold = sniffThreshold;
	}
	
	
	public Double getProbCatch() {
		return sniffThreshold;
	}

}
