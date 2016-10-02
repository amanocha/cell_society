package engine.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.NeighborInterface;
import structures.Animal;
import structures.AntCell;
import structures.Cell;
import structures.Grid;

public class UpdateAnts extends Update {
	
	public UpdateAnts(Grid newGrid, NeighborInterface newNeighbors) {
		super(newGrid, newNeighbors);
	}
	
	private void antReturnToNest(AntCell antCell) {
		if (antCell.getCurrentState()==2) {
			antCell.setOrientation(findNeighborWithMaxHomePheromones(antCell));
		}
	}
	
	/** Orientations are as follows:  
	 *  0 1 2
	 *  7   3
	 *  6 5 4
	 */
	private int findNeighborWithMaxHomePheromones(AntCell antCell) {
		int maxNumHomePheromones = 0;
		int maxIndex = 0;
		List<Cell> neighbors = getOrderedNeighbors(antCell);
		for(int i = 0; i < neighbors.size(); i++) {
			AntCell ac = (AntCell) neighbors.get(i);
			if (ac.getNumHomePheromones() > maxNumHomePheromones) {
				maxNumHomePheromones = ac.getNumHomePheromones();
			}
		}
		return maxIndex;
	}

	private void antFindFoodSource(AntCell antCell) {
		return;
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Ant Foraging model. Each cell is one of
	 * three states (0 = empty, 1 = ants, 2 = food, 3 = nest/home, 4 = blocked).
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell: getGrid().getCellList()) {
			AntCell antCell = (AntCell)cell;
			// if cell has ants then ant forage algorithm
			if (antCell.getCurrentState()==1) {
				if (antCell.hasFoodItem()) {
					antReturnToNest(antCell);
				} else {
					antFindFoodSource(antCell);
				}
			}
		}
	}
	
	/**
	 * Depends on cellList (in Grid.java) containing Animal objects.
	 */
	@Override
	public void updateCells() {
		// there are separate methods for updating fish and sharks individually, so this method is blank
	}
}