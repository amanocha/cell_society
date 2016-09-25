package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public class UpdateGameOfLife extends Update {
	private Grid grid;
	
	public UpdateGameOfLife(Grid newGrid) {
		super(newGrid);
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
			ArrayList<Cell> neighbors = super.getImmediateNeighbors(cell);
			neighbors.addAll(super.getDiagonalNeighbors(cell));
			int numLive = 0;
			for(Cell neighbor : neighbors) {
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