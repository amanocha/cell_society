package engine.neighbors;

import java.util.List;
import java.util.ArrayList;
import structures.Grid;
import structures.cell.Cell;

/**
 * This is the HexagonalNeighbor class, which contains the methods needed to determine the different types neighbors of a
 * cell when the cell shape is set to be hexagonal.
 * 
 * @author Aninda Manocha
 */

public class HexagonalNeighbors extends Neighbor{
	
	/**
	 * Constructor that takes in a Grid object in order to inherit the grid dimensions and cell list from the parent class.
	 * @param grid - the grid
	 * @param wrapping - the grid wrapping style
	 */
	public HexagonalNeighbors(Grid grid, String wrapping) {
		super(grid, wrapping);
	}
	
	/**
	 * Determines the cell number of the cell in the grid located north relative to a given cell.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located northeast relative to a given cell.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located northwest relative to a given cell.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located south relative to a given cell.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located southeast relative to a given cell.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located southwest relative to a given cell.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Gets all of the six immediate neighbors of a given cell. These neighbors are located north, northeast, southeast,
	 * south, southwest, and northwest relative to the given cell. If the grid wrapping is normal, then edge cells will
	 * have fewer than six neighbors.
	 * @param cell - the cell whose neighbors are to be determined
	 * @return a list of cells that are immediate neighbors
	 */
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
	
	/**
	 * Overrides the getDiagonalNeighbors() method to return an empty array because hexagonal cells only have six immediate
	 * neighbors and no diagonal neighbors.
	 * @return an empty list
	 */
	@Override
	public List<Cell> getDiagonalNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}
	
	/**
	 * Overrides the getSurroundingNeighbors() method to return an empty array because this method only applies to square-
	 * shaped cells that have cells in the four compass directions.
	 * @return an empty list
	 */
	@Override
	public List<Cell> getSurroundingNeighbors(Cell cell, int vision) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}
	
	/**
	 * Creates an ordered list of neighbors of a given cell in order to map the location of neighbors relative to a cell.
	 * The following mapping:
	 * 0 = north
	 * 1 = northeast
	 * 2 = southeast
	 * 3 = south
	 * 4 = southwest
	 * 5 = northwest
	 * 
	 * creates the configuration:
	 *   0
	 * 5   1
	 * 4   2
	 *   3
	 * @param cell - the given cell
	 * @return the ordered list of neighboring cells
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