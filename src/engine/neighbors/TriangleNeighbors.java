package engine.neighbors;

import java.util.ArrayList;
import java.util.List;
import structures.Grid;
import structures.cell.Cell;

/**
 * This is the TriangleNeighbor class, which contains the methods needed to determine the different types neighbors of a
 * cell when the cell shape is set to be triangular.
 * 
 * @author Aninda Manocha
 */

public class TriangleNeighbors extends Neighbor{
	
	/**
	 * Constructor that takes in a Grid object in order to inherit the grid dimensions and cell list from the parent class.
	 * @param grid - the grid
	 * @param wrapping - the grid wrapping style
	 */
	public TriangleNeighbors(Grid grid, String wrapping) {
		super(grid, wrapping);
	}
	
	/**
	 * Determines the orientation of a triangular cell, which can either be pointing up or down.
	 * @param cell - the given cell
	 * @return the orientation ("up" or "down")
	 */
	private String getOrientation(Cell cell) {
		int gridWidth = getGridWidth();
		int cellNumber = cell.getNumber();
		
		if (cellNumber % gridWidth < (gridWidth/2)) {
			return "down";
		} else {
			return "up";
		}
	}
	
	/**
	 * Determines the cell number of the cell in the grid located above a given cell that points downwards.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located northeast relative to a given cell that points upwards.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located northwest relative to a given cell that points upwards.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located below a given cell that points upwards.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located southeast relative to a given cell that points downwards.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Determines the cell number of the cell in the grid located southwest relative to a given cell that points downwards.
	 * @param cell - the given cell
	 * @return the cell number
	 */
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
	
	/**
	 * Gets the three immediate neighbors of a given cell. These neighbors are either located above, southeast, and 
	 * southwest relative to the given cell if it is pointing downwards, or below, northeast, and northwest if the given
	 * cell is pointing upwards.
	 * @param cell - the cell whose neighbors are to be determined
	 * @return a list of cells that are immediate neighbors
	 */
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
	
	/**
	 * Overrides the getDiagonalNeighbors() method to return an empty array because triangle cells only have three 
	 * immediate neighbors and no diagonal neighbors.
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
	 * Up:
	 * 0 = northwest
	 * 1 = northeast
	 * 2 = below
	 * Down:
	 * 0 = above
	 * 1 = southeast
	 * 2 = southwest
	 * 
	 * creates the configurations:
	 *  0
	 * 2 1
	 * 0 1
	 *  2
	 * @param cell - the given cell
	 * @return the ordered list of neighboring cells
	 */
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