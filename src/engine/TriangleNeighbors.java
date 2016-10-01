package engine;

import java.util.ArrayList;
import java.util.List;

import structures.Cell;
import structures.Grid;

public class TriangleNeighbors extends Neighbor implements NeighborInterface{
	
	public TriangleNeighbors(Grid grid) {
		super(grid);
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
	
	private int getLeftCell(Cell cell) {
		int gridWidth = getGridWidth();
		int cellNumber = cell.getNumber();

		if (getOrientation(cell).equals("up")) {
			if (cellNumber % 2*gridWidth != 3*gridWidth/4) {
				if (cellNumber % (2*gridWidth) < gridWidth) {
					return (cellNumber - gridWidth/2);
				} else {
					return (cellNumber - gridWidth/2 - 1);
				}
			}
		} else {
			if (cellNumber % 2*gridWidth != 0) {
				if (cellNumber % (2*gridWidth) < gridWidth) {
					return (cellNumber + gridWidth/2 - 1);
				} else {
					return (cellNumber + gridWidth/2);
				}
			}
		}
		return -1;
	}
	
	private int getRightCell(Cell cell) {
		int gridWidth = getGridWidth();
		int cellNumber = cell.getNumber();

		if (getOrientation(cell).equals("up")) {
			if (cellNumber % 2*gridWidth != gridWidth/2 - 1) {
				if (cellNumber % (2*gridWidth) < gridWidth) {
					return (cellNumber - gridWidth/2 + 1);
				} else {
					return (cellNumber - gridWidth/2);
				}
			}
		} else {
			if (cellNumber % 2*gridWidth != 3*gridWidth/4 - 1) {
				if (cellNumber % (2*gridWidth) < gridWidth) {
					return (cellNumber + gridWidth/2);
				} else {
					return (cellNumber + gridWidth/2 + 1);
				}
			}
		}
		return -1;
	}
	
	private int getBelowCell(Cell cell) {
		int cellNumber = cell.getNumber();

		if (getOrientation(cell).equals("up")) {
			if (cellNumber < getGridSize() - getGridWidth()) {
				return (cellNumber + getGridWidth());
			}
		}
		return -1;
	}
	
	private int getAboveCell(Cell cell) {
		int gridSize = getGridSize();
		int gridWidth = getGridWidth();
		int cellNumber = cell.getNumber();
		
		if (getOrientation(cell).equals("down")) {
			if (cellNumber < gridSize - gridWidth/2) {
				return (cellNumber - gridWidth);
			}
		}
		return -1;
	}
	
	@Override
	public List<Cell> getImmediateNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		int left, right, below, above;
		
		left = getLeftCell(cell);
		if (left != -1) {
			neighbors.add(getCellList().get(left));
		}
		
		right = getRightCell(cell);
		if (right != -1) {
			neighbors.add(getCellList().get(right));
		}
		
		below = getBelowCell(cell);
		if (below != -1) {
			neighbors.add(getCellList().get(below));
		}
		
		above = getAboveCell(cell);
		if (above != -1) {
			neighbors.add(getCellList().get(above));
		}
		
		return neighbors;
	}
	
	@Override
	public List<Cell> getDiagonalNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}

	@Override
	public List<Cell> getToroidalImmediateNeighbors(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cell> getToroidalDiagonalNeighbors(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}
}