package engine;

import java.util.ArrayList;
import java.util.Random;
import structures.SegregationCell;
import structures.Cell;
import structures.Grid;

public class UpdateSegregation extends Update {
	private Grid grid;
	private Neighbors neighborsObject;
	private ArrayList<Cell> emptyCells;
	private double satisfaction;
	
	public UpdateSegregation(Grid newGrid, Neighbors newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
		neighborsObject = newNeighbors;
		emptyCells = new ArrayList<Cell>();
		for(Cell cell : grid.getCellList()) {
			if (cell.getCurrentState() == 0) {
				emptyCells.add(cell);
			}
		}
		satisfaction = ((SegregationCell) newGrid.getCellList().get(0)).getSatisfaction();
	}
	
	public ArrayList<Cell> getNeighbors(Cell cell) {
		ArrayList<Cell> neighbors = super.getNeighbors(cell);
		neighbors.addAll(neighborsObject.getDiagonalNeighbors(cell));
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
		Cell emptyCell = grid.getCellList().get(emptyCellIndex);
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
		for(Cell cell : grid.getCellList()) {
			if (cell.getCurrentState() != 0) {
				if(!isSatisfied(cell)) {
					move(cell);
				}
			}
		}
	}
}