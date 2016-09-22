package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public abstract class Update {
	private Grid grid;
	
	public Update(Grid newGrid) {
		grid = newGrid;
	}
	
	/**
	 * Determines the neighbors of a given cell.
	 */
	public abstract ArrayList<Cell> getNeighbors(Cell cell);
	
	/**
	 * Iterates through all cells in the grid and determines which cells need to be updated as well as what to what 
	 * state each cell needs to be updated to.
	 */
	public abstract void determineUpdates();
	
	/**
	 * Iterates through all cells in the grid and updates the state of each cell so that a cell's current state is the
	 * same as its next state.
	 */
	public void updateCells() {
		for(Cell cell : grid.getCellList()) {
			cell.setPreviousState(cell.getCurrentState());
			cell.setCurrentState(cell.getNextState());
		}
	}
}