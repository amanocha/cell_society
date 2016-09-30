package engine;

import java.util.Random;
import structures.Cell;
import structures.Grid;
import structures.FireCell;

public class UpdateFire extends Update {
	private Grid grid;
	
	public UpdateFire(Grid newGrid, Neighbors newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
	}
	
	/**
	 * 0 = empty
	 * 1 = tree
	 * 2 = burning
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