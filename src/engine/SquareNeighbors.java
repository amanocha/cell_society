package engine;

import java.util.ArrayList;
import java.util.List;

import structures.Cell;
import structures.Grid;

public class SquareNeighbors extends Neighbor {
	public SquareNeighbors(Grid grid, String wrapping) {
		super(grid, wrapping);
	}
	
	private int getNorthCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber >= getGridWidth()) {
			return (cellNumber - getGridWidth());
		} else {
			if (isToroidal()) {
				return (getGridSize() - getGridWidth() + cellNumber);
			}
		}
		return -1;
	}
	
	private int getEastCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber % getGridWidth() == (getGridWidth()-1)) {
			return (cellNumber + 1);
		} else {
			if (isToroidal()) {
				return (cellNumber - getGridWidth() + 1);
			}
		}
		return -1;
	}
	
	private int getSouthCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber < getGridSize() - getGridWidth()) {
			return (cellNumber + getGridWidth());
		} else {
			if (isToroidal()) {
				return (cellNumber % getGridWidth());
			}
		}
		return -1;
	}
	
	private int getWestCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber % getGridWidth() != 0) {
			return (cellNumber - 1);
		} else {
			if (isToroidal()) {
				return (cellNumber + getGridWidth() - 1);
			}
		}
		return -1;
	}
	
	/**
	 * Determines the immediate neighbors (north, east, south, west) of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of the immediate neighbors
	 */
	@Override
	public List<Cell> getImmediateNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int north, east, south, west;
		
		north = getNorthCell(cell);
		east = getEastCell(cell);
		south = getSouthCell(cell);
		west = getWestCell(cell);
		
		if (north != -1) { neighbors.add(getCellList().get(north)); }
		if (east != -1) { neighbors.add(getCellList().get(east)); }
		if (south != -1) { neighbors.add(getCellList().get(south)); }
		if (west != -1) { neighbors.add(getCellList().get(west)); }
		
		return neighbors;
	}
	
	private int getNorthEastCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber >= getGridWidth()) && (cellNumber % getGridWidth() != (getGridWidth()-1))) {
			return (cellNumber - getGridWidth() + 1);
		} else {
			if (isToroidal()) {
				if (cellNumber >= getGridWidth()) { //move east, then north
					return (cellNumber - 2*getGridWidth() + 1);
				}
				if (cellNumber % getGridWidth() != (getGridWidth()-1)) { //move north, then east
					return (getGridSize() - getGridWidth() + cellNumber + 1);
				}
				return (getGridSize() - getGridWidth()); //upper right corner
			}
		}
		return -1;
	}
	
	private int getNorthWestCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber >= getGridWidth()) && (cellNumber % getGridWidth() != 0)) {
			return (cellNumber - getGridWidth() - 1);
		} else {
			if (isToroidal()) {
				if (cellNumber >= getGridWidth()) { //move west, then north
					return (cellNumber - 1);
				}
				if (cellNumber % getGridWidth() != 0) { //move north, then west
					return (getGridSize() - getGridWidth() + cellNumber - 1);
				}
				return (getGridSize() - 1); //upper left corner
			}
		}
		return -1;
	}
	
	private int getSouthEastCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber < getGridSize() - getGridWidth()) && (cellNumber % getGridWidth() != (getGridWidth()-1))) {
			return (cellNumber + getGridWidth() + 1);
		} else {
			if (isToroidal()) {
				if (cellNumber < getGridSize() - getGridWidth()) { //move east, then south
					return (cellNumber + 1);
				}
				if (cellNumber % getGridWidth() != (getGridWidth()-1)) { //move south, then east
					return (cellNumber % getGridWidth() + 1);
				}
				return (0); //lower right corner
			}
		}
		return -1;
	}
	
	private int getSouthWestCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber < getGridSize() - getGridWidth()) && (cellNumber % getGridWidth() != 0)) {
			return (cellNumber + getGridWidth() - 1);
		} else {
			if (isToroidal()) {
				if (cellNumber < getGridSize() - getGridWidth()) { //move west, then south
					return (cellNumber + 2*getGridWidth() - 1);
				}
				if (cellNumber % getGridWidth() != (getGridWidth()-1)) { //move south, then west
					return (cellNumber % getGridWidth() - 1);
				}
				return (0); //lower left corner
			}
		}
		return -1;
	}
	
	/**
	 * Determines the diagonal neighbors (northeast, northwest, southeast, southwest) of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of the diagonal neighbors
	 */
	@Override
	public List<Cell> getDiagonalNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int northEast, northWest, southEast, southWest;
		
		northEast = getNorthEastCell(cell);
		northWest = getNorthWestCell(cell);
		southEast = getSouthEastCell(cell);
		southWest = getSouthWestCell(cell);
		
		if (northEast != -1) { neighbors.add(getCellList().get(northEast)); }
		if (northWest != -1) { neighbors.add(getCellList().get(northWest)); }
		if (southEast != -1) { neighbors.add(getCellList().get(southEast)); }
		if (southWest != -1) { neighbors.add(getCellList().get(southWest)); }
		
		return neighbors;
	}
	
	@Override
	public List<Cell> getSurroundingNeighbors(Cell cell, int vision) {
		List<Cell> neighbors = new ArrayList<Cell>();
		Cell northCell = cell, eastCell = cell, southCell = cell, westCell = cell;
		int north, east, south, west;
		for (int i = 1; i <= vision; i++) {
			north = getNorthCell(northCell);
			east = getEastCell(eastCell);
			south = getSouthCell(southCell);
			west = getWestCell(westCell);
			if (north != -1) { 
				northCell = getCellList().get(north);
				neighbors.add(northCell); }
			if (east != -1) { 
				eastCell = getCellList().get(east);
				neighbors.add(eastCell); }
			if (south != -1) { 
				southCell = getCellList().get(south);
				neighbors.add(getCellList().get(south)); }
			if (west != -1) { 
				westCell = getCellList().get(west);
				neighbors.add(getCellList().get(west)); }
		}
		return neighbors;
	}
	
	/**
	 * 0 = northwest
	 * 1 = north
	 * 2 = northeast
	 * 3 = east
	 * 4 = southeast
	 * 5 = south
	 * 6 = southwest
	 * 7 = west
	 * @param cell
	 * @return
	 */
	@Override
	public List<Cell> getOrderedNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int northwest, north, northeast, east, southeast, south, southwest, west;
		
		northwest = getNorthWestCell(cell);
		north = getNorthCell(cell);
		northeast = getNorthEastCell(cell);
		east = getEastCell(cell);
		southeast = getSouthEastCell(cell);
		south = getSouthCell(cell);
		southwest = getSouthWestCell(cell);
		west = getWestCell(cell);
		
		neighbors.add(getCellList().get(northwest));
		neighbors.add(getCellList().get(north));
		neighbors.add(getCellList().get(northeast));
		neighbors.add(getCellList().get(east));
		neighbors.add(getCellList().get(southeast));
		neighbors.add(getCellList().get(south));
		neighbors.add(getCellList().get(southwest));
		neighbors.add(getCellList().get(west));
		
		return neighbors;
	}
}