package engine;

import java.util.List;

import structures.Cell;

public interface NeighborInterface {
	public List<Cell> getImmediateNeighbors(Cell cell);
	
	public List<Cell> getDiagonalNeighbors(Cell cell);
	
	public List<Cell> getToroidalImmediateNeighbors(Cell cell);
	
	public List<Cell> getToroidalDiagonalNeighbors(Cell cell);
}