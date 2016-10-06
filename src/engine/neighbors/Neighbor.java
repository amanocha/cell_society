package engine.neighbors;

import java.util.List;
import structures.Grid;
import structures.cell.Cell;

/**
 * This is the Neighbor class, which serves as a super class for all three Neighbors classes that correspond to each of the
 * cell shapes. This class contains the general methods that each of the Neighbors classes must implement in order for the
 * simulations to update properly (as they are based on the states of neighbors).
 * 
 * @author Aninda Manocha
 */

public abstract class Neighbor {
	private Grid grid;
	private int gridSize;
	private int gridWidth;
	private String wrapping;
	
	/**
	 * Constructor that takes in a Grid object in order to access the grid dimensions and cell list. 
	 * @param grid - the grid
	 * @param wrapping - the grid wrapping style
	 */
	public Neighbor(Grid grid, String wrapping) {
		this.grid = grid;
		this.gridSize = grid.getNumCells();
		this.gridWidth = grid.getColumns();
		this.wrapping = wrapping;
	}
	
	/**
	 * Gets all of the immediate neighbors of a given cell.
	 * @param cell - the cell whose neighbors are to be determined
	 * @return a list of cells that are immediate neighbors
	 */
	public abstract List<Cell> getImmediateNeighbors(Cell cell);
	
	/**
	 * Gets all of the diagonal neighbors of a given cell.
	 * @param cell - the cell whose neighbors are to be determined
	 * @return a list of cells that are diagonal neighbors
	 */
	public abstract List<Cell> getDiagonalNeighbors(Cell cell);
	
	/**
	 * Gets all of the neighbors in the four compass directions of a given cell that are no further than a certain number
	 * of cells away.
	 * @param cell - the cell whose neighbors are to be determined
	 * @param vision - the upper bound on the number of cells (counting the given cell) between a neighbor and the given 
	 * cell
	 * @return a list of cells that are surrounding neighbors
	 */
	public abstract List<Cell> getSurroundingNeighbors(Cell cell, int vision);
	
	/**
	 * Gets all of the neighbors of a given cell in a specific order.
	 * @param cell - the given cell
	 * @return a list of ordered neighbors
	 */
	public abstract List<Cell> getOrderedNeighbors(Cell cell);
	
	/**
	 * Determines if the grid wrapping style is set to toroidal
	 * @return whether the grid wrapping is set to toroidal
	 */
	public boolean isToroidal() {
		return (wrapping.equals("toroidal"));
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