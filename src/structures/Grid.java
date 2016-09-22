package structures;

import java.util.List;

public class Grid {
	
	private String cellShape;
	private List<Cell> cellList;
	private int rows;
	private int columns;
	
	public Grid(List<Cell> cellList, String shape, int rows, int columns) {
		this.setCellList(cellList);
		this.setCellShape(shape);
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