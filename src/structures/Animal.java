package structures;

public class Animal extends Cell {
	private int time;
	private int energy;
	private int sharkTime;
	private int fishTime;
	
	public Animal() {
		super();
		time = 0;
		energy = 0;
	}
	
	public Animal(int cell_num, int cell_state, int energy, int shark, int fish) {
		super(cell_num, cell_state);
		time = 0;
		this.energy = energy;
		this.sharkTime = shark;
		this.fishTime = fish;
	}
	
	public int getfishTime() {
		return fishTime;
	}
	
	public int getSharkTime() {
		return sharkTime;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
