package structures;

import java.util.ArrayList;

public class State {
	private ArrayList<Object> possible_states;
	private int state_index;
	
	public State(int state_index) {
		possible_states = new ArrayList<Object>();
		this.state_index = state_index;
	}
	
	public ArrayList<Object> getPossibleStates() {
		return possible_states;
	}
	
	public Object getState() {
		return possible_states.get(state_index);
	}

//	public abstract void setPossibleStates();
}