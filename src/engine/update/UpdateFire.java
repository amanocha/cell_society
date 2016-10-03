package engine.update;

import java.util.Random;
import engine.neighbors.Neighbor;
import structures.Grid;
import structures.cell.Cell;
import structures.cell.FireCell;

/**
 * This is the UpdateFire class, which extends the Update class and contains methods needed to implement the logic of
 * updating the grid with new states in each iteration of the Spreading Fire simulation.
 * 
 * @author Aninda Manocha
 */

public class UpdateFire extends Update {
	private Grid grid;
	
	/**
	 * Constructor
	 * @param newGrid - the grid
	 * @param newNeighbor - the Neighbor object that gives access to methods that can calculate neighbors of a given cell
	 */
	public UpdateFire(Grid newGrid, Neighbor newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
	}
	
	/**
	 * Determines the new states of each cell for the fire simulation. Each cell is one of three states (0 = empty, 1 = 
	 * tree, 2 = burning).
	 */
	@Override
	public void determineUpdates() {
		Random random = new Random();
		for(Cell cell : grid.getCellList()) {
			boolean checkProb = false;
			if(cell.getCurrentState() == 1) { 
				for(Cell neighbor : getNeighbors(cell)) {
					if(neighbor.getCurrentState() == 2) {
						checkProb = true;
						break;
					}
				}
				if(checkProb) {
					double probability = random.nextDouble();
					if(probability <= ((FireCell) cell).getProbCatch()) {
						cell.setNextState(2);
					}
				}
			} else if (cell.getCurrentState() == 2) {
				cell.setNextState(0);
			}
		}
	}
}