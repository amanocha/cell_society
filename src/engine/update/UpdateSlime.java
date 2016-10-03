package engine.update;

import java.util.Random;

import engine.neighbors.Neighbor;
import structures.cell.Cell;
import structures.Grid;
import structures.cell.SlimeCell;

public class UpdateSlime extends Update {
	private Grid grid;
	
	public UpdateSlime(Grid newGrid, Neighbor newNeighbors) {
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
		
	}
}