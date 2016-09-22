package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;
import structures.State;

public class UpdateGameOfLife extends Update {
	private Grid grid;
	
	public UpdateGameOfLife(Grid newGrid) {
		super(newGrid);
	}
	
	/**
	 * 0 = dead
	 * 1 = live
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell : grid.getCellList()) {
			ArrayList<Cell> neighbors = super.getImmediateNeighbors(cell);
			neighbors.addAll(super.getDiagonalNeighbors(cell));
			int numLive = 0;
			for(Cell neighbor : neighbors) {
				if(neighbor.getCurrentState().getStateIndex() == 1) {
					numLive++;
				}
			}
			if(cell.getCurrentState().getStateIndex() == 1) { 
				if(numLive < 2 || numLive > 3) {
					cell.setNextState(new State(0));
				}
			} else {
				if(numLive == 3)
				cell.setNextState(new State(1));
			}
		}
	}
}