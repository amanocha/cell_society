package engine.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import engine.neighbors.Neighbor;
import structures.cell.Cell;
import structures.cell.SegregationCell;
import structures.Grid;

/**
 * This is the UpdateSegregation class, which extends the Update class and contains methods needed to implement the logic 
 * of updating the grid with new states in each iteration of the Segregation simulation.
 * 
 * @author Aninda Manocha
 */

public class UpdateSegregation extends Update {
	private Grid grid;
	private Neighbor neighbor;
	private ArrayList<Cell> emptyCells;
	private ArrayList<Cell> newEmptyCells;
	private double satisfaction;
	
	/**
	 * Constructor that creates a list of empty cells and gets the satisfaction
	 * @param newGrid - the grid
	 * @param newNeighbor - the Neighbor object that gives access to methods that can calculate neighbors of a given cell
	 */
	public UpdateSegregation(Grid newGrid, Neighbor newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
		neighbor = newNeighbors;
		emptyCells = new ArrayList<Cell>();
		for(Cell cell : grid.getCellList()) {
			if (cell.getCurrentState() == 0) {
				emptyCells.add(cell);
			}
		}
		newEmptyCells = new ArrayList<Cell>();
		satisfaction = ((SegregationCell) newGrid.getCellList().get(0)).getSatisfaction();
	}
	
	/**
	 * Overrides the getNeighbors() method in order to consider both the immediate and diagonal neighbors of a given cell.
	 * @param cell - the given cell
	 * @return ArrayList of all neighbors
	 */
	@Override
	public List<Cell> getNeighbors(Cell cell) {
		List<Cell> neighbors = super.getNeighbors(cell);
		neighbors.addAll(neighbor.getDiagonalNeighbors(cell));
		return neighbors;
	}
	
	/**
	 * Determines whether a given cell is satisfied based on a satisfaction amount (determines the percent of neighbors 
	 * that need to match the given cell).
	 * @param cell - the given cell
	 * @return whether the cell is satisfied
	 */
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
	
	/**
	 * Simulates the movement of a given cell by finding a random empty cell.
	 * @param cell - the given cell
	 */
	public void move(Cell cell) {
		Random random = new Random();
		int emptyCellIndex = random.nextInt(emptyCells.size());
		Cell emptyCell = emptyCells.get(emptyCellIndex);
		swap(cell, emptyCell);
		newEmptyCells.add(cell);
		emptyCells.remove(emptyCell);
	}
	
	/**
	 * Swaps an agent cell and empty cell to simulate cell movement.
	 * @param cell1 - the agent cell
	 * @param cell2 - the empty cell
	 */
	public void swap(Cell cell1, Cell cell2) {
		cell1.setNextState(cell2.getCurrentState()); //agent cell becomes empty
		cell2.setNextState(cell1.getCurrentState()); //empty cell becomes agent
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Segregation model. Each cell is one of three states
	 * (0 = empty, 1 = red, 2 = blue).
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell : grid.getCellList()) {
			if (cell.getCurrentState() != 0) {
				if(!isSatisfied(cell)) {
					move(cell);
				}
			}
		}
		emptyCells.addAll(newEmptyCells);
		newEmptyCells.clear();
	}
}