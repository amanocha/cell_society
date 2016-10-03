package engine.update;

import java.util.List;
import engine.Neighbor;
import engine.SquareNeighbors;
import structures.AntCell;
import structures.Cell;
import structures.Grid;

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
	
	private void antReturnToNest(AntCell antCell) {
		int bestOrientation = -1;
		
		
		// else if ant is at food source, just pick a neighbor
		if (antCell.getCurrentState() == 2) {
			bestOrientation = findNeighborWithMaxHomePheromones(antCell);
			if (bestOrientation != -1) {
				antCell.setOrientation(bestOrientation);
			}
		}
		else {
			bestOrientation = findForwardNeighborWithMaxHomePheromones(antCell);
			if (bestOrientation == -1) {
				bestOrientation = findNeighborWithMaxHomePheromones(antCell);
			}
			if (bestOrientation != -1) {
				//drop food pheromone at curr cell
				antCell.setNumFoodPheromones(antCell.getNumFoodPheromones()+1);
				
				//set orientation to best orientation
				antCell.setOrientation(bestOrientation);
				
				
				//move to next cell by swapping 
				// if ant located at nest then drop food item
				if (antCell.getCurrentState() == 3) {
					antCell.setHasFoodItem(false);
				} 
				
			} else {
				// don't move from current cell
			}
		}
		
		
	}
	
	private int findNeighborWithMaxFoodPheromones(AntCell antCell) {
		return findNeighborWithMaxPheromones(antCell, "food");
	}

	private int findForwardNeighborWithMaxPheromones(AntCell antCell, String pheromoneType) {
		SquareNeighbors neighbor = new SquareNeighbors(getGrid(), wrapping);
		List<Cell> neighbors = neighbor.getOrderedNeighbors(antCell);
		int forward = antCell.getOrientation();
		int forwardRight = forward==7 ? 0 : forward + 1;
		int forwardLeft = forward==0 ? 7 : forward - 1;
		
		//Figure out the forward neighbor with max home pheromones
		
		//First set max orientation as the current orientation
		int maxOrientation = -1;
		int maxHomePheromones = -1;
		
		//define ant cells for each forward orientation
		AntCell acCurr = (AntCell)neighbors.get(maxOrientation);
		AntCell acRight = (AntCell)neighbors.get(forwardRight);
		AntCell acLeft = (AntCell)neighbors.get(forwardLeft);
		
		//
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
		
		//Find max orientation out of the 3 forward orientations
		
		//Only consider orientation if 10 or less ants or if not blocked
		// CHECK IF SHOULD USE CURRENT STATE OR NEXT STATE...
		if (acCurr.getNumAnts() <= 10 && acCurr.getCurrentState() != 2) {
			if (currOrientationPheromones > maxHomePheromones) {
				maxOrientation = forward;
			}
		}
		if (rightOrientationPheromones <= 10 && acRight.getCurrentState() != 2) {
			if (acRight.getNumHomePheromones() > maxHomePheromones) {
				maxOrientation = forwardRight;
			}
		}
		if (leftOrientationPheromones <= 10 && acLeft.getCurrentState() != 2) {
			if (acLeft.getNumHomePheromones() > maxHomePheromones) {
				maxOrientation = forwardLeft;
			}
		}
		return maxOrientation;
	}
	
	/**
	 * Finds the neighbor in a "forward direction" that has the most home pheromones
	 * @param Ant Cell
	 * @return Orientation of forward neighbor with most home pheromones, or -1 if no valid orientation
	 */
	private int findForwardNeighborWithMaxHomePheromones(AntCell antCell) {
		return findForwardNeighborWithMaxPheromones(antCell, "home");
	}
	
	private int findForwardNeighborWithMaxFoodPheromones(AntCell antCell) {
		return findForwardNeighborWithMaxPheromones(antCell, "food");
	}
	
	/**
	 * 	Orientations are as follows:  
	 *  0 1 2
	 *  7 a 3
	 *  6 5 4
	 * @param antCell
	 * @return -1 if no valid neighbors, else return neighbor orientation with most home pheromones
	 */
	private int findNeighborWithMaxPheromones(AntCell antCell, String pheromoneType) {
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
	
	private int findNeighborWithMaxHomePheromones(AntCell antCell) {
		return findNeighborWithMaxPheromones(antCell, "home");
	}

	private void antFindFoodSource(AntCell antCell) {
		int orientation = -1;
		if (antCell.getCurrentState() == 2) {
			orientation = findNeighborWithMaxFoodPheromones(antCell);
		}
		
		int x = findForwardNeighborWithMaxHomePheromones(antCell);
		if (x==-1) {
			x = findNeighborWithMaxHomePheromones(antCell);
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
	 * Determines the next state of each cell based on the rules of the Ant Foraging model. Each cell is one of
	 * three states (0 = empty, 1 = ants, 2 = food, 3 = nest/home, 4 = blocked).
	 */
	@Override
	public void determineUpdates() {
		for(Cell cell: getGrid().getCellList()) {
			AntCell antCell = (AntCell)cell;
			// if cell has ants then ant forage algorithm
			if (antCell.getCurrentState()==1) {
				if (antCell.hasFoodItem()) {
					antReturnToNest(antCell);
				} else {
					antFindFoodSource(antCell);
				}
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