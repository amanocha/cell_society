package engine;

import java.util.ArrayList;
import structures.Cell;

public interface Neighbors {
	public ArrayList<Cell> getImmediateNeighbors(Cell cell);
	
	public ArrayList<Cell> getDiagonalNeighbors(Cell cell);
}