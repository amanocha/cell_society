package engine.neighbors;

import java.util.ArrayList;
import java.util.List;

import structures.Grid;
import structures.cell.Cell;

public class TriangleNeighbors extends Neighbor{
	
	public TriangleNeighbors(Grid grid, String wrapping) {
		super(grid, wrapping);
	}
	
	private String getOrientation(Cell cell) {
		int gridWidth = getGridWidth();
		int cellNumber = cell.getNumber();
		
		if (cellNumber % gridWidth < (gridWidth/2)) {
			return "down";
		} else {
			return "up";
		}
	}
	
	private int getAboveCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber >= getGridWidth()/2) {
			return (cellNumber - getGridWidth()/2);
		} else {
			if (isToroidal()) {
				return (getGridSize() - getGridWidth() + cellNumber);
			}
		}
		return -1;
	}
	
	private int getNorthEastCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber % (2*getGridWidth()) != getGridWidth()/2 - 1) {
			if (cellNumber % (2*getGridWidth()) < getGridWidth()) {
				return (cellNumber - getGridWidth()/2 + 1);
			} else {
				return (cellNumber - getGridWidth()/2);
			}
		} else {
			if (isToroidal()) {
				return (cellNumber - getGridWidth() + 1);
			}
		}
		return -1;
	}
	
	private int getNorthWestCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber % (2*getGridWidth()) != 0) {
			if (cellNumber % (2*getGridWidth()) < getGridWidth()) {
				return (cellNumber - getGridWidth()/2);
			} else {
				return (cellNumber - getGridWidth()/2 - 1);
			}
		} else {
			if (isToroidal()) {
				return (cellNumber - 1);
			}
		}
		return -1;
	}
	
	private int getBelowCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber < getGridSize() - getGridWidth()/2) {
			return (cellNumber + getGridWidth()/2);
		} else {
			if (isToroidal()) {
				return (cellNumber % getGridWidth());
			}
		}
		return -1;
	}
	
	private int getSouthEastCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if ((cellNumber % (2*getGridWidth())) != (3*getGridWidth()/2 - 1)) {
			if (cellNumber % (2*getGridWidth()) < getGridWidth()) {
				return (cellNumber + getGridWidth()/2);
			} else {
				return (cellNumber + getGridWidth()/2 + 1);
			}
		} else {
			if (isToroidal()) {
				return (cellNumber + 1);
			}
		}
		return -1;
	}
	
	private int getSouthWestCell(Cell cell) {
		int cellNumber = cell.getNumber();
		if (cellNumber % (2*getGridWidth()) != 0) {
			if (cellNumber % (2*getGridWidth()) < getGridWidth()) {
				return (cellNumber + getGridWidth()/2 - 1);
			} else {
				return (cellNumber + getGridWidth()/2);
			}
		} else {
			if (isToroidal()) {
				return (cellNumber + getGridWidth()/2 - 1);
			}
		}
		return -1;
	}
	
	@Override
	public List<Cell> getImmediateNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		
		if (getOrientation(cell).equals("up")) {
			int below, northeast, northwest;
			
			below = getBelowCell(cell);
			northeast = getNorthEastCell(cell);
			northwest = getNorthWestCell(cell);
			
			if (below != -1) { neighbors.add(getCellList().get(below)); }
			if (northeast != -1) { neighbors.add(getCellList().get(northeast)); }
			if (northwest != -1) { neighbors.add(getCellList().get(northwest)); }
		} else {
			int above, southeast, southwest;
			
			above = getAboveCell(cell);
			southeast = getSouthEastCell(cell);
			southwest = getSouthWestCell(cell);

			if (above != -1) { neighbors.add(getCellList().get(above)); }
			if (southeast != -1) { neighbors.add(getCellList().get(southeast)); }
			if (southwest != -1) { neighbors.add(getCellList().get(southwest)); }
		}
		
		return neighbors;
	}
	
	@Override
	public List<Cell> getDiagonalNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}
	
	@Override
	public List<Cell> getSurroundingNeighbors(Cell cell, int vision) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}
	
	/**
	 * Up:
	 * 0 = northwest
	 * 1 = northeast
	 * 2 = below
	 * Down:
	 * 0 = above
	 * 1 = southeast
	 * 2 = southwest
	 * @param cell
	 * @return
	 */
	@Override
	public List<Cell> getOrderedNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		
		if (getOrientation(cell).equals("up")) {
			int northwest, northeast, below;
		
			northwest = getNorthWestCell(cell);
			northeast = getNorthEastCell(cell);
			below = getBelowCell(cell);
			
			neighbors.add(getCellList().get(northwest));
			neighbors.add(getCellList().get(northeast));
			neighbors.add(getCellList().get(below));
		} else {
			int above, southeast, southwest;
			
			above = getAboveCell(cell);
			southeast = getSouthEastCell(cell);
			southwest = getSouthWestCell(cell);
			
			neighbors.add(getCellList().get(above));
			neighbors.add(getCellList().get(southeast));
			neighbors.add(getCellList().get(southwest));
		}
		
		return neighbors;
	}
}