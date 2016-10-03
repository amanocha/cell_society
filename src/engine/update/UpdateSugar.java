package engine.update;

import java.util.Random;

import engine.neighbors.Neighbor;
import structures.cell.Cell;
import structures.Grid;

public class UpdateSugar extends Update {
	private Grid grid;
	
	public UpdateSugar(Grid newGrid, Neighbor newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
	}
	
	@Override
	public void determineUpdates() {
		for(Cell cell : grid.getCellList()) {
			
		}
	}
}