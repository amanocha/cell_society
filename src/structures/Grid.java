package structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import structures.cell.Cell;

/*
 * The Grid class is the data structure that holds the necessary
 * components to visualize a grid in the frontend
 */
public class Grid implements Iterable<Cell>{
	
	private List<Cell> cellList;
	private List<Cell> initialCellList;
	private int rows;
	private int columns;
	private String cellShape;
	
	
	public Grid(List<Cell> cellList, int rows, int columns, MetaData meta) {
		this.cellList = cellList;
		this.initialCellList = new ArrayList<Cell>(cellList.size()); 
		initialCellList.addAll(cellList);
		this.rows = rows;
		this.columns = columns;
		this.cellShape = meta.getShape();
	}
	
	public int getNumCells() {
		return cellList.size();
	}
	
	// Resets drawing of grid
	public void reset() {
		this.cellList = this.initialCellList;
		System.out.println("draw grid");
		int count = 0;
		for(int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				System.out.print(initialCellList.get(count).getCurrentState() + " ");
				count++;
			}
			System.out.println();
		}
		//System.out.println();
	}

	// Iterator to easily iterate each cell for drawing in front end
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