public class Cell {
	private String name;
	private int number;
	private State previous_state;
	private State current_state;
	
	public Cell() {
		name = "";
		number = 0;
		previous_state = new State();
		current_state = new State();
	}
	
	public Cell(String cell_name, int cell_num, State cell_state) {
		name = cell_name;
		number = cell_num;
		previous_state = new State();
		current_state = cell_state;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public State getPreviousState() {
		return previous_state;
	}
	
	public State getCurrentState() {
		return current_state;
	}
}