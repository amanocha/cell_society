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
			if (cell.getCurrentState() == 1) {
				//System.out.println(cell.getNumber() + " moved to " + emptyCell.getNumber());
			}
			swap((Animal) cell, (Animal) emptyCell); //cell's next state is empty, empty cell's next state is fish/shark
			if(reproduce) {
				reproduce(cell, emptyCell); //change cell's state from empty to fish/shark
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
			//System.out.println(shark.getNumber() + " ate " + fishCell.getNumber());
			swap(shark, (Animal) fishCell); //shark's next state is fish, fish's next state is shark
			if(reproduce) {
				reproduce(shark, fishCell);
			} else {
				shark.setNextState(0);
			}
			return fishCell; 
		}
		shark.setEnergy(shark.getEnergy() - 1);
		System.out.println(shark.getNumber() + ": " + shark.getEnergy());
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
		cell1.setNextState(cell2.getNextState());
		((Animal)cell1).setEnergy(Integer.parseInt(grid.getGlobalsMap().get("energy")));
		//System.out.println(cell1.getNumber() + " state = " + cell1.getNextState() + " gave birth to " + cell2.getNumber() + " state = " + cell2.getNextState());
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
			boolean reproduce = ((Animal)fish).getTime() >= fishTime;
			//System.out.println(fish.getNumber() + ": " + ((Animal)fish).getTime());
			//System.out.println("MOVING FISH " + fish.getNumber());
			fish = move(fish, reproduce);
		}
	}
	
	public void determineSharkUpdates(ArrayList<Cell> sharks) {
		for(Cell shark : sharks) {
			boolean reproduce = ((Animal)shark).getTime() >= sharkTime;
			//System.out.println(reproduce);
			//System.out.print(shark.getNumber() + ": " + ((Animal)shark).getTime() + ", ");
			shark = eat((Animal)shark, reproduce);
			if (((Animal)shark).getEnergy() == 0) {
				shark.setNextState(0);
			}
		}
	}
	
	public void displayCells(int animalState) {
		for(Cell cell : grid.getCellList()) {
			cell.setPreviousState(cell.getCurrentState());
			cell.setCurrentState(cell.getNextState());
			Animal animal = (Animal) cell; 
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
		/*for(Cell cell : grid.getCellList()) {
			cell.setPreviousState(cell.getCurrentState());
			cell.setCurrentState(cell.getNextState());
			Animal animal = (Animal) cell; 
			animal.setTime(animal.getTime() + 1);
		}*/
	}
}