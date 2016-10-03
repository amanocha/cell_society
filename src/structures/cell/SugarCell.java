package structures.cell;

public class SugarCell extends Cell {
	private int sugar;
	private int sugarTime;
	private int sugarGrowBackTime;
	private int vision;
	private int sugarMetabolism;
	private int patchSugar;
	private int patchSugarCapacity;
	
	public SugarCell() {
		super();
		sugar = 10;
		sugarTime = 0;
	}
	
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
