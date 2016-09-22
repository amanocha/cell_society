package engine;

import java.util.ArrayList;
import structures.Cell;

public interface Update {
	/**
	 * Determines the neighbors of a given cell.
	 */
	public ArrayList<Cell> getNeighbors(Cell cell);
	
	/**
	 * Iterates through all cells in the grid and determines which cells need to be updated as well as what to what 
	 * state each cell needs to be updated to.
	 */
	public void determineUpdates();
	
	/**
	 * Iterates through all cells in the grid and updates the state of each cell so that a cell's current state is the
	 * same as its next state.
	 */
	public void updateCells();
}