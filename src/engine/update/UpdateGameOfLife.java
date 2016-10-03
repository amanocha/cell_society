package engine.update;

import java.util.List;
import engine.neighbors.Neighbor;
import structures.Grid;
import structures.cell.Cell;

/**
 * This is the UpdateGameOfLife class, which extends the Update class and contains methods needed to implement the logic of
 * updating the grid with new states in each iteration of the Game of Life simulation.
 * 
 * @author Aninda Manocha
 */

public class UpdateGameOfLife extends Update {
	private Grid grid;
	private Neighbor neighbor;
	
	/**
	 * Constructor
	 * @param newGrid - the grid
	 * @param newNeighbor - the Neighbor object that gives access to methods that can calculate neighbors of a given cell
	 */
	public UpdateGameOfLife(Grid newGrid, Neighbor newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
		neighbor = newNeighbors;
	}
	
	/**
	 * Overrides the getNeighbors() method in order to consider both the immediate and diagonal neighbors of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of all neighbors
	 */
	@Override
	public List<Cell> getNeighbors(Cell cell) {
		List<Cell> neighbors = super.getNeighbors(cell);
		neighbors.addAll(neighbor.getDiagonalNeighbors(cell));
		return neighbors;
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Game of Life. Each cell is one of two states
	 * (0 = dead, 1 = live). If a cell is live and has two or three live neighbors, it remains live. If a cell is live 
	 * and has less than two or more than three live neighbors, then the cell becomes dead. If a dead cell has exactly
	 * three live neighbors, then it becomes live.
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell : grid.getCellList()) {
			int numLive = 0;
			for(Cell neighbor : getNeighbors(cell)) {
				if(neighbor.getCurrentState() == 1) {
					numLive++; //count number of live neighbors
				}
			}
			if(cell.getCurrentState() == 1) { //cell is live
				if(numLive < 2 || numLive > 3) {
					cell.setNextState(0);
				}
			} else { //cell is dead
				if(numLive == 3)
				cell.setNextState(1);
			}
		}
	}
}