package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public class HexagonalNeighbors implements Neighbors{
	private Grid grid;
	
	public HexagonalNeighbors(Grid grid) {
		this.grid = grid;
	}
	
	@Override
	public ArrayList<Cell> getImmediateNeighbors(Cell cell) {
		int gridSize = grid.getNumCells();
		int gridWidth = grid.getColumns();
		int cellNumber = cell.getNumber();
		int north, northeast, northwest, south, southeast, southwest;
		
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		if (cellNumber >= gridWidth) {
			north = cellNumber - gridWidth;
			neighbors.add(grid.getCellList().get(north));
		}
		
		if (cellNumber >= gridWidth/2 && cellNumber % gridWidth == (gridWidth/2 - 1)) {
			northeast = cellNumber + 1;
			neighbors.add(grid.getCellList().get(northeast));
		}
		
		if (cellNumber >= gridWidth/2 && cellNumber % gridWidth == gridWidth/2) {
			northwest = cellNumber + 1;
			neighbors.add(grid.getCellList().get(northwest));
		}
		
		if (cellNumber < gridSize - gridWidth) {
			south = cellNumber + gridWidth;
			neighbors.add(grid.getCellList().get(south));
		}
		
		if (cellNumber < gridSize - gridWidth/2 && cellNumber % gridWidth == (gridWidth/2 - 1)) {
			southeast = cellNumber + 1;
			neighbors.add(grid.getCellList().get(southeast));
		}
		
		if (cellNumber < gridSize - gridWidth/2 && cellNumber % gridWidth == gridWidth/2) {
			southwest = cellNumber - 1;
			neighbors.add(grid.getCellList().get(southwest));
		}
		
		return neighbors;
	}
	
	@Override
	public ArrayList<Cell> getDiagonalNeighbors(Cell cell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}
}