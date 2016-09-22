package structures;

import java.util.List;

public class Grid {
	
	private String cellShape;
	private List<Cell> cellList;
	
	public Grid(List<Cell> cellList, String shape) {
		this.setCellList(cellList);
		this.setCellShape(shape);
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
	
	
}