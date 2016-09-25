package engine;

import java.util.ArrayList;
import java.util.Random;
import structures.Animal;
import structures.Cell;
import structures.Grid;

public class UpdatePredatorPrey extends Update {
	private Grid grid;
	private int fishTime;
	private int sharkTime;
	
	public UpdatePredatorPrey(Grid newGrid) {
		super(newGrid);
		grid = newGrid;
		fishTime = 0;
		sharkTime = 0;
	}
	
	public UpdatePredatorPrey(Grid newGrid, int newFishTime, int newSharkTime) {
		super(newGrid);
		grid = newGrid;
		fishTime = newFishTime;
		sharkTime = newSharkTime;
	}
	
	public Cell move(Cell cell, boolean reproduce) {
		ArrayList<Cell> neighbors = super.getImmediateNeighbors(cell);
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		for(Cell neighbor : neighbors) {
			if(neighbor.getCurrentState() == 0 && neighbor.getNextState() == 0) {
				emptyCells.add(neighbor);
			}
		}
		if (emptyCells.size() > 0) {
			int emptyCellIndex = selectCell(emptyCells);
			Cell emptyCell = grid.getCellList().get(emptyCellIndex);
			swap((Animal) cell, (Animal) emptyCell);
			if(reproduce) {
				reproduce(cell, emptyCell);
			}
			return emptyCell;
		}
		return cell;
	}
	
	public Cell eat(Animal shark, boolean reproduce) {
		ArrayList<Cell> neighbors = super.getImmediateNeighbors(shark);
		ArrayList<Cell> fishCells = new ArrayList<Cell>();
		for(Cell neighbor : neighbors) {
			if (neighbor.getCurrentState() == 1 && neighbor.getNextState() == 1) {
				fishCells.add(neighbor);
			}
		}
		if(fishCells.size() > 0) {
			shark.setEnergy(shark.getEnergy() + 1);
			int fishCellIndex = selectCell(fishCells);
			Cell fishCell = grid.getCellList().get(fishCellIndex);
			swap(shark, (Animal) fishCell);
			if(reproduce) {
				reproduce(shark, fishCell);
			} else {
				shark.setNextState(0);
			}
			return fishCell; 
		}
		shark.setEnergy(shark.getEnergy() - 1);
		return move(shark, reproduce);
	}
	
	public int selectCell(ArrayList<Cell> cells) {
		Random random = new Random();
		int neighborCellsIndex = random.nextInt(cells.size());
		int newIndex = cells.get(neighborCellsIndex).getNumber(); //new cell number to indicate new location
		return newIndex;
	}
	
	public void swap(Animal animal1, Animal animal2) {
		int animal1State = animal1.getCurrentState();
		animal1.setNextState(animal2.getCurrentState());
		animal2.setNextState(animal1State);
		int animal1Time = animal1.getTime();
		animal1.setTime(animal2.getTime());
		animal2.setTime(animal1Time);
		int animal1Energy = animal1.getEnergy();
		animal1.setEnergy(animal2.getEnergy());
		animal2.setEnergy(animal1Energy);
	}
	
	public void reproduce(Cell cell1, Cell cell2) {
		cell1.setNextState(cell1.getCurrentState());
		((Animal)cell1).setTime(-1);
		((Animal)cell2).setTime(-1);
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Wator Predator-Prey model. Each cell is one of
	 * three states (0 = empty, 1 = fish, 2 = shark). If a cell is empty, then it remains empty unless a fish or shark
	 * moves to it.
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell : grid.getCellList()) {
			if (cell.getCurrentState() == 1) { //fish
				boolean reproduce = ((Animal)cell).getTime() >= fishTime;
				cell = move(cell, reproduce);
			} else if (cell.getCurrentState() == 2) { //shark
				boolean reproduce = ((Animal)cell).getTime() >= sharkTime;
				cell = eat((Animal)cell, reproduce);
				if (((Animal)cell).getEnergy() == 0) {
					cell.setNextState(0);
				}
			}
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