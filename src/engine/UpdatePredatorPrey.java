package engine;

import java.util.ArrayList;

import structures.Animal;
import structures.Cell;
import structures.Grid;

public class UpdatePredatorPrey extends Update {
	private Grid grid;
	private int fishTime;
	private int sharkTime;
	
	public UpdatePredatorPrey(Grid newGrid) {
		super(newGrid);
		fishTime = 0;
		sharkTime = 0;
	}
	
	public UpdatePredatorPrey(Grid newGrid, int newFishTime, int newSharkTime) {
		super(newGrid);
		fishTime = newFishTime;
		sharkTime = newSharkTime;
	}
	
	public void moveFish(Cell cell) {
		ArrayList<Cell> neighbors = super.getImmediateNeighbors(cell);
		for(Cell neighbor : neighbors) {
			if(((Animal) neighbor).isEmpty()) {
				
			}
		}
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Wator Predator-Prey model. Each cell is one of
	 * three states (0 = empty, 1 = fish, 2 = shark). If a cell is empty, then it remains empty unless a fish or shark
	 * moves to it.
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell : grid.getCellList()) {
			
			
		}
	}
	
	/**
	 * Depends on cellList (in Grid.java) containing Animal objects.
	 */
	@Override
	public void updateCells() {
		for(Cell cell : grid.getCellList()) {
			cell.setPreviousState(cell.getCurrentState());
			cell.setCurrentState(cell.getNextState());
			Animal animal = (Animal) cell; 
			animal.setTime(animal.getTime() + 1);
		}
	}
}