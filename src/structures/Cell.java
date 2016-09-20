package structures;

public class Cell {
	private int x;
	private int y;
	private State prevState;
	private State currState;
	
	public Cell(int x, int y, State prevState, State currState) {
		this.x = x;
		this.y = y;
		this.prevState = prevState;
		this.currState = currState;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void changeState(State newState) {
		this.prevState = this.currState;
		this.currState = newState;
	}
}
