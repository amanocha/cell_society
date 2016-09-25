package engine;

import java.util.Random;
import structures.Cell;
import structures.Grid;

public class UpdateFire extends Update {
	private Grid grid;
	private double probCatch;
	
	public UpdateFire(Grid newGrid) {
		super(newGrid);
		probCatch = Double.parseDouble(grid.getGlobalsMap().get("probCatch"));
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
				for(Cell neighbor : super.getImmediateNeighbors(cell)) {
					if(neighbor.getCurrentState() == 2) {
						checkProb = true;
						break;
					}
				}
				if(checkProb) {
					if(random.nextDouble() <= probCatch) {
						cell.setNextState(2);
					}
				}
			} else if (cell.getCurrentState() == 2) {
				cell.setNextState(0);
			}
		}
	}
}