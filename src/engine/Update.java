package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public abstract class Update {
	private Grid grid;
	private Neighbors neighborsObject;
	
	public Update(Grid grid, Neighbors neighbors) {
		this.grid = grid;
		this.neighborsObject = neighbors;
	}
	
	/**Determines the neighbors of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of all neighbors
	 */
	public ArrayList<Cell> getNeighbors(Cell cell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		neighbors.addAll(neighborsObject.getImmediateNeighbors(cell));
		return neighbors;
	}
	
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