package structures;

public class Cell {
	private int number;
	private State previous_state;
	private State current_state;
	private boolean update;
	
	public Cell() {
		number = 0;
		previous_state = new State(-1);
		current_state = new State(-1);
		update = false;
	}
	
	public Cell(int cell_num, State cell_state) {
		number = cell_num;
		previous_state = new State(-1);
		current_state = cell_state;
		update = false;
	}
	
	/*****GETTERS*****/
	
	public int getNumber() {
		return number;
	}
	
	public State getPreviousState() {
		return previous_state;
	}
	
	public State getCurrentState() {
		return current_state;
	}
	
	public boolean getUpdate() {
		return update;
	}
	
	/*****SETTERS*****/
	
	public void setNumber(int new_number) {
		number = new_number;
	}
	
	public void setPreviousState(State new_previous) {
		previous_state = new_previous;
	}
	
	public void setCurrentState(State new_current) {
		current_state = new_current;
	}
	
	public void setUpdate(boolean new_update) {
		update = new_update;
	}
}