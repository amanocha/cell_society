package engine.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.neighbors.Neighbor;
import structures.Grid;
import structures.cell.AnimalCell;
import structures.cell.Cell;

public class UpdatePredatorPrey extends Update {
	private Grid grid;
	private int energy;
	private int fishTime;
	private int sharkTime;
	
	public UpdatePredatorPrey(Grid newGrid, Neighbor newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
		energy = ((AnimalCell) newGrid.getCellList().get(0)).getEnergy();
		fishTime = ((AnimalCell) newGrid.getCellList().get(0)).getFishTime();
		sharkTime = ((AnimalCell) newGrid.getCellList().get(0)).getSharkTime();
	}
	
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
				//System.out.println(cell.getNumber() + " moved to " + emptyCell.getNumber());
			}
			swap((AnimalCell) cell, (AnimalCell) emptyCell); //cell's next state is empty, empty cell's next state is fish/shark
			if(reproduce) {
				reproduce(cell, emptyCell); //change cell's state from empty to fish/shark
			}
			return emptyCell;
		}
		return cell;
	}
	
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
			//System.out.println(shark.getNumber() + " ate " + fishCell.getNumber());
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
	
	public int selectCell(List<Cell> fishCells) {
		Random random = new Random();
		int neighborCellsIndex = random.nextInt(fishCells.size());
		int newIndex = fishCells.get(neighborCellsIndex).getNumber(); //new cell number to indicate new location
		return newIndex;
	}
	
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
	
	public void reproduce(Cell cell1, Cell cell2) {
		cell1.setNextState(cell2.getNextState());
		((AnimalCell)cell1).setEnergy(energy);
		//System.out.println(cell1.getNumber() + " state = " + cell1.getNextState() + " gave birth to " + cell2.getNumber() + " state = " + cell2.getNextState());
		((AnimalCell)cell1).setTime(-1);
		((AnimalCell)cell2).setTime(-1);
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Wator Predator-Prey model. Each cell is one of
	 * three states (0 = empty, 1 = fish, 2 = shark). If a cell is empty, then it remains empty unless a fish or shark
	 * moves to it.
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
	
	public void determineFishUpdates(ArrayList<Cell> fishes) {
		for(Cell fish : fishes) {
			boolean reproduce = ((AnimalCell)fish).getTime() >= fishTime;
			fish = move(fish, reproduce);
		}
	}
	
	public void determineSharkUpdates(ArrayList<Cell> sharks) {
		for(Cell shark : sharks) {
			boolean reproduce = ((AnimalCell)shark).getTime() >= sharkTime;
			shark = eat((AnimalCell)shark, reproduce);
			if (((AnimalCell)shark).getEnergy() == 0) {
				shark.setNextState(0);
			}
		}
	}
	
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