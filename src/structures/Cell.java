package structures;

public class Cell {
	private int number;
	private State previous_state;
	private State current_state;
	private State next_state;
	
	public Cell() {
		number = 0;
		previous_state = new State();
		current_state = new State();
		next_state = new State();
	}
	
	public Cell(int cell_num, State cell_state) {
		number = cell_num;
		previous_state = new State();
		current_state = cell_state;
		next_state = current_state;
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
	
	public State getNextState() {
		return next_state;
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
	
	public void setNextState(State new_next) {
		next_state = new_next;
	}
}