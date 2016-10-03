package structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import structures.cell.Cell;

/**
 * This is the Grid class, which is the data structure that stores all cells and holds the necessary components to 
 * visualize a grid (for the frontend).
 * 
 * @author Aninda Manocha
 */
public class Grid implements Iterable<Cell>{
	
	private List<Cell> cellList;
	private List<Cell> initialCellList;
	private int rows;
	private int columns;
	private String cellShape;
	
	/**
	 * Constructor
	 * @param cellList - the list of cells to store in the grid
	 * @param rows - the number of rows in the grid
	 * @param columns - the number of columns in the grid
	 * @param meta - the data that contains information about grid cells
	 */
	public Grid(List<Cell> cellList, int rows, int columns, MetaData meta) {
		this.cellList = cellList;
		this.initialCellList = new ArrayList<Cell>(cellList.size()); 
		initialCellList.addAll(cellList);
		this.rows = rows;
		this.columns = columns;
		this.cellShape = meta.getShape();
	}
	
	/**
	 * Gets the number of cells in the grid (a perfect square).
	 * @return the number of cells
	 */
	public int getNumCells() {
		return cellList.size();
	}
	
	/**
	 * Resets the grid so that it returns to the initial state (before the simulation ran).
	 */
	public void reset() {
		this.cellList = this.initialCellList;
	}

	/**
	 *  Creates an iterator for the frontend to iterate through in order to access all cells and their information so that
	 *  they can be drawn according to their states.
	 */
	@Override
    public Iterator<Cell> iterator() {
        Iterator<Cell> iterator = new Iterator<Cell>() {

        	private int currentIndex = 0;

            @Override
            public boolean hasNext() {
            	return currentIndex < getNumCells() && cellList.get(currentIndex) != null;
            }

            @Override
            public Cell next() {
                return cellList.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }
	
	/*****GETTERS*****/

	public List<Cell> getCellList() {
		return cellList;
	}
	
	public List<Cell> getInitialCellList() {
		return initialCellList;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public String getCellShape() {
		return cellShape;
	}

	/*****SETTERS*****/
	
	public void setCellList(List<Cell> cellList) {
		this.cellList = cellList;
	}
	
	public void setInitialCellList(List<Cell> cellList) {
		this.initialCellList = cellList;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public void setCellShape(String cellShape) {
		this.cellShape = cellShape;
	}
	
}