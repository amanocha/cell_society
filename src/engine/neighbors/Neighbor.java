package engine.neighbors;

import java.util.List;

import structures.Grid;
import structures.cell.Cell;

public abstract class Neighbor {
	private Grid grid;
	private int gridSize;
	private int gridWidth;
	private String wrapping;
	
	public Neighbor(Grid grid, String wrapping) {
		this.grid = grid;
		this.gridSize = grid.getNumCells();
		this.gridWidth = grid.getColumns();
		this.wrapping = wrapping;
	}
	
	public abstract List<Cell> getImmediateNeighbors(Cell cell);
	
	public abstract List<Cell> getDiagonalNeighbors(Cell cell);
	
	public abstract List<Cell> getSurroundingNeighbors(Cell cell, int vision);
	
	public abstract List<Cell> getOrderedNeighbors(Cell cell);
	
	public boolean isToroidal() {
		return (wrapping.equals("toroidal"));
	}
	
	public boolean isInfinite() {
		return (wrapping.equals("infinite"));
	}
	
	/*****GETTERS*****/
	
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
