package engine.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import engine.neighbors.Neighbor;
import structures.Grid;
import structures.cell.AnimalCell;
import structures.cell.Cell;

/**
 * This is the UpdatePredatorPrey class, which extends the Update class and contains methods needed to implement the logic 
 * of updating the grid with new states in each iteration of the Wator simulation.
 * 
 * @author Aninda Manocha
 */

public class UpdatePredatorPrey extends Update {
	private Grid grid;
	private int energy;
	private int fishTime;
	private int sharkTime;
	
	/**
	 * Constructor that determines the energy, fish reproduction time, and shark reproduction time based on the properties
	 * of a cell
	 * @param newGrid - the grid
	 * @param newNeighbor - the Neighbor object that gives access to methods that can calculate neighbors of a given cell
	 */
	public UpdatePredatorPrey(Grid newGrid, Neighbor newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
		energy = ((AnimalCell) newGrid.getCellList().get(0)).getEnergy();
		fishTime = ((AnimalCell) newGrid.getCellList().get(0)).getFishTime();
		sharkTime = ((AnimalCell) newGrid.getCellList().get(0)).getSharkTime();
	}
	
	/**
	 * Simulations either a shark or fish moving.
	 * @param cell - the animal that wants to move
	 * @param reproduce - whether the animal can reproduce
	 * @return empty (if the animal moved), or the animal (if it didn't move)
	 */
	public Cell move(Cell cell, boolean reproduce) {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		for(Cell neighbor : getNeighbors(cell)) {
			if(neighbor.getCurrentState() == 0 && neighbor.getNextState() == 0) {
				emptyCells.add(neighbor);
			}
		}
		if (emptyCells.size() > 0) {
			int emptyCellIndex = selectCell(emptyCells);
			Cell emptyCell = grid.getCellList().get(emptyCellIndex);
			if (cell.getCurrentState() == 1) {
			}
			swap((AnimalCell) cell, (AnimalCell) emptyCell); //cell's next state is empty, empty cell's next state is fish/shark
			if(reproduce) {
				reproduce(cell, emptyCell); //change cell's state from empty to fish/shark
			}
			return emptyCell;
		}
		return cell;
	}
	
	/**
	 * Simulates a shark moving and eating a fish.
	 * @param shark - the shark that wants to eat 
	 * @param reproduce - whether the shark can reproduce
	 * @return fish (if the shark ate), empty (if the shark moved but didn't eat), or shark (if it couldn't move)
	 */
	public Cell eat(AnimalCell shark, boolean reproduce) {
		List<Cell> neighbors = getNeighbors(shark);
		List<Cell> fishCells = new ArrayList<Cell>();
		for(Cell neighbor : neighbors) {
			if (neighbor.getCurrentState() == 1 && neighbor.getNextState() == 1) {
				fishCells.add(neighbor);
			}
		}
		if(fishCells.size() > 0) {
			shark.setEnergy(shark.getEnergy() + 1);
			int fishCellIndex = selectCell(fishCells);
			Cell fishCell = grid.getCellList().get(fishCellIndex);
			swap(shark, (AnimalCell) fishCell); //shark's next state is fish, fish's next state is shark
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
	
	/**
	 * Randomly selects a cell from a list of cells.
	 * @param fishCells - list of cells to choose from
	 * @return the index of the selected cell in the list
	 */
	public int selectCell(List<Cell> fishCells) {
		Random random = new Random();
		int neighborCellsIndex = random.nextInt(fishCells.size());
		int newIndex = fishCells.get(neighborCellsIndex).getNumber(); //new cell number to indicate new location
		return newIndex;
	}
	
	/**
	 * Swaps the information of two animal cells in order to simulate cell movement.
	 * @param animal1 - the first animal cell
	 * @param animal2 - the second animal cell
	 */
	public void swap(AnimalCell animal1, AnimalCell animal2) {
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
	
	/**
	 * Simulates an animal moving and reproducing.
	 * @param cell1 - the animal that was reproduced
	 * @param cell2 - the animal that is reproducing
	 */
	public void reproduce(Cell cell1, Cell cell2) {
		cell1.setNextState(cell2.getNextState());
		((AnimalCell)cell1).setEnergy(energy);
		((AnimalCell)cell1).setTime(-1);
		((AnimalCell)cell2).setTime(-1);
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Wator Predator-Prey model. Each cell is one of
	 * three states (0 = empty, 1 = fish, 2 = shark). If a cell is empty, then it remains empty unless a fish or shark
	 * moves to it. The fish are updated first (so they can move before getting eaten), and then the shark are updated.
	 */
	@Override
	public void determineUpdates() {
		ArrayList<Cell> fishes = new ArrayList<Cell>();
		ArrayList<Cell> sharks = new ArrayList<Cell>();
		for(Cell cell : grid.getCellList()) {
			if (cell.getCurrentState() == 1) { //fish
				fishes.add(cell);
			} else if (cell.getCurrentState() == 2) { //shark
				sharks.add(cell);
			}
		}
		determineFishUpdates(fishes);
		displayCells(1);
		determineSharkUpdates(sharks);
		displayCells(2);
	}
	
	/**
	 * Determines which fish cells need their states updated.
	 * @param fishes - the list of fish cells
	 */
	public void determineFishUpdates(ArrayList<Cell> fishes) {
		for(Cell fish : fishes) {
			boolean reproduce = ((AnimalCell)fish).getTime() >= fishTime;
			fish = move(fish, reproduce);
		}
	}
	
	/**
	 * Determines which shark cells need their states updated.
	 * @param fishes - the list of shark cells
	 */
	public void determineSharkUpdates(ArrayList<Cell> sharks) {
		for(Cell shark : sharks) {
			boolean reproduce = ((AnimalCell)shark).getTime() >= sharkTime;
			shark = eat((AnimalCell)shark, reproduce);
			if (((AnimalCell)shark).getEnergy() == 0) {
				shark.setNextState(0);
			}
		}
	}
	
	/**
	 * Updates all cell states and updates the time of cells according to whichever animal was most recently updated.
	 */
	public void displayCells(int animalState) {
		for(Cell cell : grid.getCellList()) {
			cell.setPreviousState(cell.getCurrentState());
			cell.setCurrentState(cell.getNextState());
			AnimalCell animal = (AnimalCell) cell; 
			if (cell.getCurrentState() == animalState) {
				animal.setTime(animal.getTime() + 1);
			}
		}
	}
	
	/**
	 * Depends on cellList (in Grid.java) containing Animal objects.
	 */
	@Override
	public void updateCells() {
		// there are separate methods for updating fish and sharks individually, so this method is blank
	}
}