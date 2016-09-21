package structures;

import java.util.ArrayList;

public abstract class State {
	private ArrayList<Object> possible_states;
	private int state_index;
	
	public State() {
		possible_states = new ArrayList<Object>();
		state_index = -1;
	}
	
	public ArrayList<Object> getPossibleStates() {
		return possible_states;
	}
	
	public Object getState() {
		return possible_states.get(state_index);
	}
	
	public abstract void setPossibleStates();
}