package structures;

public class Animal extends Cell {
	private int time;
	private boolean empty;
	
	public Animal() {
		super();
		time = 0;
		empty = true;
	}
	
	public Animal(int cell_num, int cell_state) {
		super(cell_num, cell_state);
		time = 0;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int new_time) {
		time = new_time;
	}
}
