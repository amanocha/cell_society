package engine;

import java.util.List;

import structures.Cell;
import structures.Grid;

public class Neighbor {
	private Grid grid;
	private int gridSize;
	private int gridWidth;
	
	public Neighbor(Grid grid) {
		this.grid = grid;
		this.gridSize = grid.getNumCells();
		this.gridWidth = grid.getColumns();
	}
	
	public List<Cell> getCellList() {
		return grid.getCellList();
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public int getGridSize() {
		return gridSize;
	}
	
	public int getGridWidth() {
		return gridWidth;
	}
}
