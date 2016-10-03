package engine.update;

import java.util.ArrayList;
import java.util.List;

import engine.neighbors.Neighbor;
import engine.neighbors.SquareNeighbors;
import structures.Grid;
import structures.cell.Ant;
import structures.cell.AntCell;
import structures.cell.Cell;

/*
 * three states (0 = empty, 1 = ants, 2 = food, 3 = nest/home, 4 = blocked)
 */
public class UpdateAnts extends Update {
	private String shape;
	private String wrapping;
	
	public UpdateAnts(Grid newGrid, Neighbor newNeighbors, String shape, String wrapping) {
		super(newGrid, newNeighbors);
		this.shape = shape;
		this.wrapping = wrapping;
	}
	
	/**
	 * Determines the next state of each cell based on the rules of the Ant Foraging model. Each cell is one of
	 * three states (0 = empty, 1 = ants, 2 = food, 3 = nest/home, 4 = blocked).
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell: getGrid().getCellList()) {
			AntCell antCell = (AntCell)cell;
			// if cell has ants then ant forage algorithm for each ant
			if (antCell.getCurrentState()==1) {
				// for each ant in the cell, move ant to next location & update grid
				for(int i = 0; i < antCell.getAntsList().size(); i++) {
					Ant ant = antCell.getAntsList().get(i);
					
					if (ant.hasFoodItem()) {
						setGrid(antReturnToNest(antCell, i));
					} else {
						setGrid(antFindFoodSource(antCell, i));
					}
					
				}
			}
		}
	}
	/**
	 * 
	 * @param antCell
	 * @param antIndex
	 * @return Updated grid for changes as a result of updating one ant cell
	 */
	private Grid antReturnToNest(AntCell antCell, int antIndex) {
		int orientation = -1;
		if (antCell.getCurrentState()==2) {
			orientation = selectNeighborWithMaxHomePheromones(antCell);
		}
		int x = selectForwardNeighborWithMaxHomePheromones(antCell, antIndex);
		if (x == -1) {
			x = selectNeighborWithMaxHomePheromones(antCell);
		}
		if (x != -1) {
			antCell = dropFoodPheromones(antCell);
			orientation = x;
		}
//		
//		int bestOrientation = -1;
//		
//		
//		// else if ant is at food source, just pick a neighbor
//		if (antCell.getCurrentState() == 2) {
//			bestOrientation = findNeighborWithMaxHomePheromones(antCell);
//			if (bestOrientation != -1) {
//				antCell.setOrientation(bestOrientation);
//			}
//		}
//		else {
//			bestOrientation = findForwardNeighborWithMaxHomePheromones(antCell);
//			if (bestOrientation == -1) {
//				bestOrientation = findNeighborWithMaxHomePheromones(antCell);
//			}
//			if (bestOrientation != -1) {
//				//drop food pheromone at curr cell
//				antCell.setNumFoodPheromones(antCell.getNumFoodPheromones()+1);
//				
//				//set orientation to best orientation
//				antCell.setOrientation(bestOrientation);
//				
//				
//				//move to next cell by swapping 
//				// if ant located at nest then drop food item
//				if (antCell.getCurrentState() == 3) {
//					antCell.setHasFoodItem(false);
//				} 
//				
//			} else {
//				// don't move from current cell
//			}
//		}
		
		
	}
	
	private AntCell dropFoodPheromones(AntCell antCell) {
		// TODO Auto-generated method stub
		return null;
	}

	private int selectNeighborWithMaxFoodPheromones(AntCell antCell) {
		return selectNeighborWithMaxPheromones(antCell, "food");
	}

	private int selectForwardNeighbor(AntCell antCell, int antIndex, String pheromoneType) {
		SquareNeighbors neighbor = new SquareNeighbors(getGrid(), wrapping);
		List<Cell> neighbors = neighbor.getOrderedNeighbors(antCell);
		List<Integer> possibleLocations = new ArrayList<Integer>();
		List<Integer> possibleLocationsNumPheromones = new ArrayList<Integer>();
		
		//Get forward possibilities based on current ant orientation
		int forward = antCell.getAnt(antIndex).getOrientation();
		int forwardRight = forward==7 ? 0 : forward + 1;
		int forwardLeft = forward==0 ? 7 : forward - 1;
		
		//define ant cells for each forward orientation
		AntCell acCurr = (AntCell)neighbors.get(forward);
		AntCell acRight = (AntCell)neighbors.get(forwardRight);
		AntCell acLeft = (AntCell)neighbors.get(forwardLeft);
		
		//get pheromones at each forward ant cell
		int currOrientationPheromones = 0;
		int leftOrientationPheromones = 0;
		int rightOrientationPheromones = 0;
		
		if (pheromoneType.equals("home")) {
			currOrientationPheromones = acCurr.getNumHomePheromones();
			rightOrientationPheromones = acRight.getNumHomePheromones();
			leftOrientationPheromones = acLeft.getNumHomePheromones();
		} else { // food
			currOrientationPheromones = acCurr.getNumFoodPheromones();
			rightOrientationPheromones = acRight.getNumFoodPheromones();
			leftOrientationPheromones = acLeft.getNumFoodPheromones();
		}
		
		//Only consider orientation if 10 or less ants or if not blocked
		if (acCurr.getNumAnts() <= 10 && acCurr.getCurrentState() != 2) {
			possibleLocations.add(forward);
			possibleLocationsNumPheromones.add(currOrientationPheromones);
		}
		if (acRight.getNumAnts() <= 10 && acRight.getCurrentState() != 2) {
			possibleLocations.add(forwardRight);
			possibleLocationsNumPheromones.add(rightOrientationPheromones);
		}
		if (acLeft.getNumAnts() <= 10 && acLeft.getCurrentState() != 2) {
			possibleLocations.add(forwardLeft);
			possibleLocationsNumPheromones.add(leftOrientationPheromones);
		}
		
		return pheromoneType.equals("home") ? selectLocationByMax(possibleLocations, possibleLocationsNumPheromones) : selectLocationByProbability(possibleLocations, possibleLocationsNumPheromones);
		
	}
	
	// Pick location/orientation by max num of home pheromones
	public int selectLocationByMax(List<Integer> possibleLocations, List<Integer> possibleLocationsNumPheromones) {
		int max = 0;
		int maxIndex = 0;
		for(int i = 0; i < possibleLocationsNumPheromones.size(); i++) {
			int curr = possibleLocationsNumPheromones.get(i);
			if (curr > max) {
				max = curr;
				maxIndex = i;
			}
		}
		return possibleLocations.get(maxIndex);
	}
	
	//  Pick random location/orientation based on num of food pheromones
	public int selectLocationByProbability(List<Integer> possibleLocations, List<Integer> possibleLocationsNumPheromones) {
		//error check, sizes should be same
		if (possibleLocations.size() != possibleLocationsNumPheromones.size()) {
			System.out.println("Error in UpdateAnts.java: sizes of lists are not the same.");
		}
		
		// CHOOSES FROM POSSIBLE LOCATIONS COMPLETELY RANDOMLY (need to implement better heuristic)
		int random = (int) Math.random()*possibleLocations.size();
		return possibleLocations.get(random);
	}
	
	/**
	 * Finds the neighbor in a "forward direction" that has the most home pheromones
	 * @param Ant Cell
	 * @return Orientation of forward neighbor with most home pheromones, or -1 if no valid orientation
	 */
	private int selectForwardNeighborWithMaxHomePheromones(AntCell antCell, int index) {
		return selectForwardNeighbor(antCell, index, "home");
	}
	
	private int selectForwardNeighborWithMaxFoodPheromones(AntCell antCell, int index) {
		return selectForwardNeighbor(antCell, index, "food");
	}
	
	/**
	 * 	Orientations are as follows:  
	 *  0 1 2
	 *  7 a 3
	 *  6 5 4
	 * @param antCell
	 * @return -1 if no valid neighbors, else return neighbor orientation with most home pheromones
	 */
	private int selectNeighborWithMaxPheromones(AntCell antCell, String pheromoneType) {
		int maxNumHomePheromones = 0;
		int maxIndex = -1;
		SquareNeighbors neighbor = new SquareNeighbors(getGrid(), wrapping);
		List<Cell> neighbors = neighbor.getOrderedNeighbors(antCell);
		for(int i = 0; i < neighbors.size(); i++) {
			AntCell ac = (AntCell) neighbors.get(i);
			int numPheromones = 0;
			if(pheromoneType.equals("home")) {
				numPheromones = ac.getNumHomePheromones();
			} else {
				numPheromones = ac.getNumFoodPheromones();
			}
					
			// Check if neighbor position has less than or equal to 10 ants and that it isn't a blocked position
			if (ac.getNumAnts() <= 10 && ac.getCurrentState() != 2) {
				if (numPheromones > maxNumHomePheromones) {
					maxNumHomePheromones = ac.getNumHomePheromones();
					maxIndex = i;
				}
			}
		}
		return maxIndex;
	}
	
	private int selectNeighborWithMaxHomePheromones(AntCell antCell) {
		return selectNeighborWithMaxPheromones(antCell, "home");
	}

	private void antFindFoodSource(AntCell antCell) {
		int orientation = -1;
		if (antCell.getCurrentState() == 2) {
			orientation = selectNeighborWithMaxFoodPheromones(antCell);
		}
		
		int x = selectForwardNeighborWithMaxHomePheromones(antCell);
		if (x==-1) {
			x = selectNeighborWithMaxHomePheromones(antCell);
		}
		if (x!=-1) {
			//drop home pheromones
			//orientation = heading to X from current location
			//move to x
			//if ant located at food source
				//pick up food item
				//has food item = true
		}
		
		if (orientation == -1) {
			// don't move
			antCell.setNextState(antCell.getCurrentState());
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