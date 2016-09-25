package structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Grid implements Iterable<Cell>{
	
	private String cellShape;
	private List<Cell> cellList;
	private int rows;
	private int columns;
	Map<String, String> globalsMap;
	
	public Grid() {
		
	}
	
	public Grid(List<Cell> cellList, int rows, int columns, Map<String, String> globalsMap) {
		this.cellList = new ArrayList<Cell>();
		this.cellList.addAll(cellList);
		this.rows = rows;
		this.columns = columns;
		this.globalsMap = new HashMap<String, String>();
		for (String str : globalsMap.keySet()) {
			this.globalsMap.put(str, globalsMap.get(str));
		}
	}
	
	public int getNumCells() {
		return cellList.size();
	}

	
	public String getCellShape() {
		return cellShape;
	}

	public void setCellShape(String cellShape) {
		this.cellShape = cellShape;
	}

	public List<Cell> getCellList() {
		return cellList;
	}

	public void setCellList(List<Cell> cellList) {
		this.cellList = cellList;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Map<String, String> getGlobalsMap() {
		return globalsMap;
	}
	
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
}