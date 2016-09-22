package structures;

import java.util.ArrayList;
import java.util.List;

public class State {
	private List<State> possible_states;
	private int state_index;
	
	public State() {
		possible_states = new ArrayList<State>();
	}
	
	public State(int state_index) {
		possible_states = new ArrayList<State>();
		this.state_index = state_index;
	}
	
	public List<State> getPossibleStates() {
		return possible_states;
	}
	
	public Object getState() {
		return possible_states.get(state_index);
	}
	
	public void setPossibleStates() {}

}