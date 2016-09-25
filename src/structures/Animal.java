package structures;

public class Animal extends Cell {
	private int time;
	private int energy;
	
	public Animal() {
		super();
		time = 0;
		energy = 0;
	}
	
	public Animal(int cell_num, int cell_state, int energy) {
		super(cell_num, cell_state);
		time = 0;
		this.energy = energy;
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
