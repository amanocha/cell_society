package engine;

import java.util.ArrayList;
import structures.Cell;
import structures.Grid;

public class TriangleNeighbors implements Neighbors{
	private Grid grid;
	
	public TriangleNeighbors(Grid grid) {
		this.grid = grid;
	}
	
	@Override
	public ArrayList<Cell> getImmediateNeighbors(Cell cell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		return neighbors;
	}
	
	@Override
	public ArrayList<Cell> getDiagonalNeighbors(Cell cell) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		
		return neighbors;
	}
}