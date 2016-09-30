package engine;

import java.util.ArrayList;
import java.util.List;

import structures.Cell;
import structures.Grid;

public class SquareNeighbors extends Neighbor implements NeighborInterface{
	
	public SquareNeighbors(Grid grid) {
		super(grid);
	}
	
	/**
	 * Determines the immediate neighbors (north, east, south, west) of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of the immediate neighbors
	 */
	@Override
	public List<Cell> getImmediateNeighbors(Cell cell) {
		int cellNumber = cell.getNumber();
		int north, east, south, west;
		
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		if (cellNumber >= getGridWidth()) {
			north = cellNumber - getGridWidth();
			neighbors.add(getCellList().get(north));
		}
		
		if (cellNumber % getGridWidth() != (getGridWidth()-1)) {
			east = cellNumber + 1;
			neighbors.add(getCellList().get(east));
		}
		
		if (cellNumber < getGridSize() - getGridWidth()) {
			south = cellNumber + getGridWidth();
			neighbors.add(getCellList().get(south));
		}
		
		if (cellNumber % getGridWidth() != 0) {
			west = cellNumber - 1;
			neighbors.add(getCellList().get(west));
		}
		
		return neighbors;
	}
	
	/**
	 * Determines the diagonal neighbors (northeast, northwest, southeast, southwest) of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of the diagonal neighbors
	 */
	@Override
	public List<Cell> getDiagonalNeighbors(Cell cell) {
		int cellNumber = cell.getNumber();
		int northEast, northWest, southEast, southWest;
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		if ((cellNumber >= getGridWidth()) && (cellNumber % getGridWidth() != (getGridWidth()-1))) {
			northEast = cellNumber - getGridWidth() + 1;
			neighbors.add(getCellList().get(northEast));
		}
		
		if ((cellNumber >= getGridWidth()) && (cellNumber % getGridWidth() != 0)) {
			northWest = cellNumber - getGridWidth() - 1;
			neighbors.add(getCellList().get(northWest));
		}
		
		if ((cellNumber < getGridSize() - getGridWidth()) && (cellNumber % getGridWidth() != (getGridWidth()-1))) {
			southEast = cellNumber + getGridWidth() + 1;
			neighbors.add(getCellList().get(southEast));
		}
		
		if ((cellNumber < getGridSize() - getGridWidth()) && (cellNumber % getGridWidth() != 0)) {
			southWest = cellNumber + getGridWidth() - 1;
			neighbors.add(getCellList().get(southWest));
		}
		
		return neighbors;
	}

	@Override
	public List<Cell> getToroidalImmediateNeighbors(Cell cell) {
		
		return null;
	}

	@Override
	public List<Cell> getToroidalDiagonalNeighbors(Cell cell) {
		return null;
	}
}