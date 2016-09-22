package engine;

import java.util.ArrayList;
import java.util.Random;
import structures.Cell;
import structures.Grid;
import structures.State;

public class UpdateFire extends Update {
	private Grid grid;
	private double probCatch;
	
	public UpdateFire(Grid newGrid) {
		super(newGrid);
		probCatch = parseDouble(grid.getGlobalsMap().get("probCatch"));
	}
	
	@Override
	public ArrayList<Cell> getNeighbors(Cell cell) {
		int gridSize = grid.getNumCells();
		int gridWidth = grid.getColumns();
		int cellNumber = cell.getNumber();
		int north, east, south, west;
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		if (cellNumber >= gridWidth) {
			north = cellNumber - gridWidth;
			neighbors.add(grid.getCellList().get(north));
		}
		
		if (cellNumber % gridWidth != (gridWidth-1)) {
			east = cellNumber + 1;
			neighbors.add(grid.getCellList().get(east));
		}
		
		if (cellNumber < gridSize - gridWidth) {
			south = cellNumber + gridWidth;
			neighbors.add(grid.getCellList().get(south));
		}
		
		if (cellNumber % gridWidth != 0) {
			west = cellNumber - 1;
			neighbors.add(grid.getCellList().get(west));
		}
		
		return neighbors;
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
			if(cell.getCurrentState().getStateIndex() == 1) { 
				for(Cell neighbor : getNeighbors(cell)) {
					if(neighbor.getCurrentState().getStateIndex() == 2) {
						checkProb = true;
						break;
					}
				}
				if(checkProb) {
					if(random.nextDouble() <= probCatch) {
						cell.setNextState(new State(2));
					}
				}
			} else if (cell.getCurrentState().getStateIndex() == 2) {
				cell.setNextState(new State(0));
			}
		}
	}
}