package structures.cell;

public class SugarCell extends Cell {
	private int sugar;
	private int sugarGrowBackTime;
	private int vision;
	private int sugarMetabolism;
	
	public SugarCell() {
		super();
		sugar = 0;
	}
	
	public SugarCell(int cell_num, int cell_state, int initSugar, int sugarGrowBackTime, int vision, int sugarMetabolism) {
		super(cell_num, cell_state);
		sugar = initSugar;
		this.sugarGrowBackTime = sugarGrowBackTime;
		this.vision = vision;
		this.sugarMetabolism = sugarMetabolism;
	}
	
	public int getSugar() {
		return sugar;
	}
	
	public int getSugarGrowBackTimel() {
		return sugarGrowBackTime;
	}
	
	public int getVision() {
		return vision;
	}
	
	public int getSugarMetabolism() {
		return sugarMetabolism;
	}
	
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}
}
