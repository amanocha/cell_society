package structures;

public class Cell {
	private int number;
	private int previous_state;
	private int current_state;
	private int next_state;
	
	public Cell() {
		number = 0;
		previous_state = -1;
		current_state = -1;
		next_state = -1;
	}
	
	public Cell(int cell_num, int cell_state) {
		number = cell_num;
		previous_state = -1;
		current_state = cell_state;
		next_state = current_state;
	}
	
	/*****GETTERS*****/
	
	public int getNumber() {
		return number;
	}
	
	public int getPreviousState() {
		return previous_state;
	}
	
	public int getCurrentState() {
		return current_state;
	}
	
	public int getNextState() {
		return next_state;
	}
	
	/*****SETTERS*****/
	
	public void setNumber(int new_number) {
		number = new_number;
	}
	
	public void setPreviousState(int new_previous) {
		previous_state = new_previous;
	}
	
	public void setCurrentState(int new_current) {
		current_state = new_current;
	}
	
	public void setNextState(int new_next) {
		next_state = new_next;
	}
}