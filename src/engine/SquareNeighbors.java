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
		
		List<Cell> neighbors = new ArrayList<Cell>();
		
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
		List<Cell> neighbors = new ArrayList<Cell>();
		
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
		int cellNumber = cell.getNumber();
		int north, east, south, west;
		
		List<Cell> neighbors = new ArrayList<Cell>();
		
		// Find north neighbor
		if (inTopRow(cellNumber)) {
			north = (getGridSize() - 1) + cellNumber - getGridWidth();
		} else {
			north = cellNumber - getGridWidth();
		}
		
		// Find south neighbor
		if (inBottomRow(cellNumber)) {
			south = cellNumber % getGridWidth();
		} else {
			south = cellNumber + getGridWidth();
		}
		
		// Find east neighbor
		if (inLeftColumn(cellNumber)) {
			east = cellNumber + getGridWidth() - 1;
		} else {
			east = cellNumber + 1;
		}
		
		// Find west neighbor
		if (inRightColumn(cellNumber)) {
			west = cellNumber - getGridWidth() + 1;
		} else {
			west = cellNumber - 1;
		}
		neighbors.add(getCellList().get(north));
		neighbors.add(getCellList().get(south));
		neighbors.add(getCellList().get(east));
		neighbors.add(getCellList().get(west));
		return neighbors;
	}

	@Override
	public List<Cell> getToroidalDiagonalNeighbors(Cell cell) {
		return null;
	}
}