package structures;

public class State {
	// is prob a property of state? Shouldn't it be a part of the logic in determining WHICH state?
	private double probability;
	private int stateNumber;
	
	public State(double probability, int stateNumber) {
		this.probability = probability;
		this.stateNumber = stateNumber;
	}

}
