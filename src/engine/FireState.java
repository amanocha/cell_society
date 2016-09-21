public class FireState extends State {
	public FireState() {
		super();
	}
	
	public void setPossibleStates() {
		super.getPossibleStates().add("empty");
		super.getPossibleStates().add("tree");
		super.getPossibleStates().add("burning");
	}
}