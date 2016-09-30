package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public class SquareNeighbors implements Neighbors{
	private Grid grid;
	
	public SquareNeighbors(Grid grid) {
		this.grid = grid;
	}
	
	/**
	 * Determines the immediate neighbors (north, east, south, west) of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of the immediate neighbors
	 */
	@Override
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
	@Override
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
}