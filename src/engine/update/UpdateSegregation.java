package engine.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.NeighborInterface;
import structures.SegregationCell;
import structures.Cell;
import structures.Grid;

public class UpdateSegregation extends Update {
	private NeighborInterface neighbor;
	private ArrayList<Cell> emptyCells;
	private double satisfaction;
	
	public UpdateSegregation(Grid newGrid, NeighborInterface newNeighbors) {
		super(newGrid, newNeighbors);
		neighbor = newNeighbors;
		emptyCells = new ArrayList<Cell>();
		for(Cell cell : getGrid().getCellList()) {
			if (cell.getCurrentState() == 0) {
				emptyCells.add(cell);
			}
		}
		satisfaction = ((SegregationCell) newGrid.getCellList().get(0)).getSatisfaction();
	}
	
	public List<Cell> getNeighbors(Cell cell) {
		List<Cell> neighbors = super.getNeighbors(cell);
		neighbors.addAll(neighbor.getDiagonalNeighbors(cell));
		return neighbors;
	}
	
	public boolean isSatisfied(Cell cell) {
		int numAgents = 0;
		int same = 0;
		for (Cell neighbor : getNeighbors(cell)) {
			if (neighbor.getCurrentState() != 0) {
				numAgents++;
				if (neighbor.getCurrentState() == cell.getCurrentState()) {
					same++;
				}
			}
		}
		double ratio = (double)same/numAgents;
		return (ratio >= satisfaction);
	}
	
	public void move(Cell cell) {
		Random random = new Random();
		int emptyCellIndex = random.nextInt(emptyCells.size());
		Cell emptyCell = getGrid().getCellList().get(emptyCellIndex);
		emptyCells.add(cell);
		emptyCells.remove(emptyCell);
		swap(cell, emptyCell);
	}
	
	public void swap(Cell cell1, Cell cell2) {
		cell1.setNextState(cell2.getCurrentState()); //agent cell becomes empty
		cell2.setNextState(cell1.getCurrentState()); //empty cell becomes agent
	}
	
	@Override
	public void determineUpdates() {
		for(Cell cell : getGrid().getCellList()) {
			if (cell.getCurrentState() != 0) {
				if(!isSatisfied(cell)) {
					move(cell);
				}
			}
		}
	}
}