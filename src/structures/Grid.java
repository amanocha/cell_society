package structures;

import java.util.List;
import java.util.Map;

public class Grid {
	
	private String cellShape;
	private List<Cell> cellList;
	private int rows;
	private int columns;
	Map<String, String> globalsMap;
	
	public Grid() {
		
	}
	
	public Grid(List<Cell> cellList, int rows, int columns, Map<String, String> globalsMap) {
		this.cellList = cellList;
		this.rows = rows;
		this.columns = columns;
		this.globalsMap = globalsMap;
	}
	
	public Map<String, String> getGlobalsMap() {
		return globalsMap;
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
	
	
}