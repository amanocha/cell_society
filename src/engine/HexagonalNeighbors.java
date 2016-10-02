package engine;

import java.util.List;
import java.util.ArrayList;

import structures.Cell;
import structures.Grid;

public class HexagonalNeighbors extends Neighbor{
	
	public HexagonalNeighbors(Grid grid, String wrapping) {
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
	
	private int getNorthEastCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber >= getGridWidth()) && (cellNumber % getGridWidth() != (getGridWidth()-1))) {
			if (cellNumber % getGridWidth() < getGridWidth()/2) { //upper half
				return (cellNumber - getGridWidth()/2);
			} else { //lower half
				return (cellNumber - getGridWidth()/2 + 1);
			}
		} else {
			if (isToroidal()) {
				if (cellNumber >= getGridWidth()) { //move east, then north
					return (cellNumber - getGridWidth() + 1);
				}
				if (cellNumber % getGridWidth() != (getGridWidth()-1)) { //move north, then east
					if (cellNumber % getGridWidth() < getGridWidth()/2) { //top row, upper half
						return (getGridSize() + cellNumber - getGridWidth()/2);
					} else { //top row, lower half
						return (cellNumber - getGridWidth()/2 + 1);
					}
				}
				return (0); //upper right corner -> lower left corner
			}
		}
		return -1;
	}
	
	private int getNorthWestCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber >= getGridWidth()) && (cellNumber % getGridWidth() != 0)) {
			if (cellNumber % getGridWidth() < getGridWidth()/2) { //upper half
				return (cellNumber - getGridWidth()/2 - 1);
			} else { //lower half
				return (cellNumber - getGridWidth()/2);
			}
		} else {
			if (isToroidal()) {
				if (cellNumber >= getGridWidth()) { //move west, then north
					return (cellNumber - 1);
				}
				if (cellNumber % getGridWidth() != 0) { //move north, then west
					if (cellNumber % getGridWidth() < getGridWidth()/2) { //top row, upper half
						return (getGridSize() + cellNumber - getGridWidth()/2 - 1);
					} else { //top row, lower half
						return (cellNumber - getGridWidth()/2);
					}
				}
				return (getGridSize() - 1); //upper left corner -> lower right corner
			}
		}
		return -1;
	}
	
	public int getSouthCell(Cell cell) {
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
	
	private int getSouthEastCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber < getGridSize() - getGridWidth()) && (cellNumber % getGridWidth() != (getGridWidth()-1))) {
			if (cellNumber % getGridWidth() < getGridWidth()/2) { //upper half
				return (cellNumber + getGridWidth()/2);
			} else { //lower half
				return (cellNumber + getGridWidth()/2 + 1);
			}
		} else {
			if (isToroidal()) {
				if (cellNumber < getGridSize() - getGridWidth()) { //move east, then south
					return (cellNumber + 1);
				}
				if (cellNumber % getGridWidth() != (getGridWidth()-1)) { //move south, then east
					if (cellNumber % getGridWidth() < getGridWidth()/2) { //top row, upper half
						return (cellNumber + getGridWidth()/2);
					} else { //top row, lower half
						return (cellNumber % getGridWidth() + 1);
					}
				}
				return (0); //lower right corner -> upper left corner
			}
		}
		return -1;
	}
	
	private int getSouthWestCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber < getGridSize() - getGridWidth()) && (cellNumber % getGridWidth() != 0)) {
			if (cellNumber % getGridWidth() < getGridWidth()/2) { //upper half
				return (cellNumber + getGridWidth()/2 - 1);
			} else { //lower half
				return (cellNumber + getGridWidth()/2);
			}
		} else {
			if (isToroidal()) {
				if (cellNumber < getGridSize() - getGridWidth()) { //move west, then south
					return (cellNumber + getGridWidth() - 1);
				}
				if (cellNumber % getGridWidth() != 0) { //move south, then west
					if (cellNumber % getGridWidth() < getGridWidth()/2) { //top row, upper half
						return (cellNumber + getGridWidth()/2 - 1);
					} else { //top row, lower half
						return (cellNumber % getGridWidth());
					}
				}
				return (getGridSize() - 1); //lower left corner -> upper right corner
			}
		}
		return -1;
	}
	
	@Override
	public List<Cell> getImmediateNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int north, northeast, northwest, south, southeast, southwest;
		
		north = getNorthCell(cell);
		northeast = getNorthEastCell(cell);
		northwest = getNorthWestCell(cell);
		south = getSouthCell(cell);
		southeast = getSouthEastCell(cell);
		southwest = getSouthWestCell(cell);
		
		if (north != -1) { neighbors.add(getCellList().get(north)); }
		if (northeast != -1) { neighbors.add(getCellList().get(northeast)); }
		if (northwest != -1) { neighbors.add(getCellList().get(northwest)); }
		if (south != -1) { neighbors.add(getCellList().get(south)); }
		if (southeast != -1) { neighbors.add(getCellList().get(southeast)); }
		if (southwest != -1) { neighbors.add(getCellList().get(southwest)); }
		
		return neighbors;
	}
	
	@Override
	public List<Cell> getDiagonalNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}
	
	/**
	 * 0 = north
	 * 1 = northeast
	 * 2 = southeast
	 * 3 = south
	 * 4 = southwest
	 * 5 = northwest
	 * @param cell
	 * @return
	 */
	public List<Cell> getOrderedNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int north, northeast, southeast, south, southwest, northwest;
		
		north = getNorthCell(cell);
		northeast = getNorthEastCell(cell);
		southeast = getSouthEastCell(cell);
		south = getSouthCell(cell);
		southwest = getSouthWestCell(cell);
		northwest = getNorthWestCell(cell);
		
		neighbors.add(getCellList().get(north));
		neighbors.add(getCellList().get(northeast));
		neighbors.add(getCellList().get(southeast));
		neighbors.add(getCellList().get(south));
		neighbors.add(getCellList().get(southwest));
		neighbors.add(getCellList().get(northwest));
		
		return neighbors;
	}
}