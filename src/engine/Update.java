package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public abstract class Update {
	private Grid grid;
	
	public Update(Grid newGrid) {
		grid = newGrid;
	}
	
	/**
	 * Determines the immediate neighbors (north, east, south, west) of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of the immediate neighbors
	 */
	public ArrayList<Cell> getImmediateNeighbors(Cell cell) {
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
	 * Determines the diagonal neighbors (northeast, northwest, southeast, southwest) of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of the diagonal neighbors
	 */
	public ArrayList<Cell> getDiagonalNeighbors(Cell cell) {
		int gridSize = grid.getNumCells();
		int gridWidth = grid.getColumns();
		int cellNumber = cell.getNumber();
		int northEast, northWest, southEast, southWest;
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		if ((cellNumber >= gridWidth) && (cellNumber % gridWidth != (gridWidth-1))) {
			northEast = cellNumber - gridWidth + 1;
			neighbors.add(grid.getCellList().get(northEast));
		}
		
		if ((cellNumber >= gridWidth) && (cellNumber % gridWidth != 0)) {
			northWest = cellNumber - gridWidth - 1;
			neighbors.add(grid.getCellList().get(northWest));
		}
		
		if ((cellNumber < gridSize - gridWidth) && (cellNumber % gridWidth != (gridWidth-1))) {
			southEast = cellNumber + gridWidth + 1;
			neighbors.add(grid.getCellList().get(southEast));
		}
		
		if ((cellNumber < gridSize - gridWidth) && (cellNumber % gridWidth != 0)) {
			southWest = cellNumber + gridWidth - 1;
			neighbors.add(grid.getCellList().get(southWest));
		}
		
		return neighbors;
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