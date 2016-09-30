package engine;

import java.util.ArrayList;
import java.util.List;

import structures.Cell;
import structures.Grid;

public class TriangleNeighbors extends Neighbor implements NeighborInterface{
	
	public TriangleNeighbors(Grid grid) {
		super(grid);
	}
	
	@Override
	public List<Cell> getImmediateNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}
	
	@Override
	public List<Cell> getDiagonalNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<Cell>();
		return neighbors;
	}

	@Override
	public List<Cell> getToroidalImmediateNeighbors(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cell> getToroidalDiagonalNeighbors(Cell cell) {
		// TODO Auto-generated method stub
		return null;
	}
}