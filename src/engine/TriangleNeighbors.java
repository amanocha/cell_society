package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public class TriangleNeighbors implements Neighbors{
	private Grid grid;
	
	public TriangleNeighbors(Grid grid) {
		this.grid = grid;
	}
	
	private String getOrientation(Cell cell) {
		int gridWidth = grid.getColumns();
		int cellNumber = cell.getNumber();
		
		if (cellNumber % gridWidth < (gridWidth/2)) {
			return "down";
		} else {
			return "up";
		}
	}
	
	private int getLeftCell(Cell cell) {
		int gridWidth = grid.getColumns();
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
		int gridWidth = grid.getColumns();
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
		int gridSize = grid.getNumCells();
		int gridWidth = grid.getColumns();
		int cellNumber = cell.getNumber();

		if (getOrientation(cell).equals("up")) {
			if (cellNumber < gridSize - gridWidth) {
				return (cellNumber + gridWidth);
			}
		}
		return -1;
	}
	
	private int getAboveCell(Cell cell) {
		int gridSize = grid.getNumCells();
		int gridWidth = grid.getColumns();
		int cellNumber = cell.getNumber();
		
		if (getOrientation(cell).equals("down")) {
			if (cellNumber < gridSize - gridWidth/2) {
				return (cellNumber - gridWidth);
			}
		}
		return -1;
	}
	
	@Override
	public ArrayList<Cell> getImmediateNeighbors(Cell cell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		int left, right, below, above;
		
		left = getLeftCell(cell);
		if (left != -1) {
			neighbors.add(grid.getCellList().get(left));
		}
		
		right = getRightCell(cell);
		if (right != -1) {
			neighbors.add(grid.getCellList().get(right));
		}
		
		below = getBelowCell(cell);
		if (below != -1) {
			neighbors.add(grid.getCellList().get(below));
		}
		
		above = getAboveCell(cell);
		if (above != -1) {
			neighbors.add(grid.getCellList().get(above));
		}
		
		return neighbors;
	}
	
	@Override
	public ArrayList<Cell> getDiagonalNeighbors(Cell cell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		return neighbors;
	}
}