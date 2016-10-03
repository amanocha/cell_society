package engine.update;

import java.util.List;
import engine.neighbors.Neighbor;
import structures.Grid;
import structures.cell.Cell;

/**
 * This is the Update class, which serves as an abstract super class that contains methods needed to implement the logic of
 * updating the grid with new states in each iteration of a simulation.
 * 
 * @author Aninda Manocha
 */

public abstract class Update {
	private Grid grid;
	private Neighbor neighbor;
	
	/**
	 * Constructor
	 * @param grid - the grid
	 * @param neighbor - the Neighbor object that gives access to methods that can calculate neighbors of a given cell
	 */
	public Update(Grid grid, Neighbor neighbor) {
		this.grid = grid;
		this.neighbor = neighbor;
	}
	
	/**Determines the neighbors of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of all neighbors
	 */
	public List<Cell> getNeighbors(Cell cell){
		return neighbor.getImmediateNeighbors(cell);
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