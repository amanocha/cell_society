package engine.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.Neighbor;
import engine.NeighborInterface;
import engine.SquareNeighbors;
import structures.Animal;
import structures.AntCell;
import structures.Cell;
import structures.Grid;

public class UpdateAnts extends Update {
	private String shape;
	private String wrapping;
	
	public UpdateAnts(Grid newGrid, Neighbor newNeighbors, String shape, String wrapping) {
		super(newGrid, newNeighbors);
		this.shape = shape;
		this.wrapping = wrapping;
	}
	
	private void antReturnToNest(AntCell antCell) {
		if (antCell.getCurrentState()==2) {
			antCell.setOrientation(findNeighborWithMaxHomePheromones(antCell));
		}
	}
	
	/**
	 * Finds the neighbor in a "forward direction" that has the most home pheromones
	 * @param Ant Cell
	 * @return Orientation of forward neighbor with most home pheromones, or -1 if no valid orientation
	 */
	private int findForwardNeighborWithMaxHomePheromones(AntCell antCell) {
		SquareNeighbors neighbor = new SquareNeighbors(getGrid(), wrapping);
		List<Cell> neighbors = neighbor.getOrderedNeighbors(antCell);
		int forward = antCell.getOrientation();
		int forwardRight = forward==7 ? 0 : forward+1;
		int forwardLeft = forward==0 ? 7 : forward-1;
		
		//Figure out the forward neighbor with max home pheromones
		
		//First set max orientation as the current orientation
		int maxOrientation = -1;
		int maxHomePheromones = -1;
		
		//define ant cells for each forward orientation
		AntCell acCurr = (AntCell)neighbors.get(maxOrientation);
		AntCell acRight = (AntCell)neighbors.get(forwardRight);
		AntCell acLeft = (AntCell)neighbors.get(forwardLeft);
		
		//Find max orientation out of the 3 forward orientations
		
		//Only consider orientation if 10 or less ants or if not blocked
		// CHECK IF SHOULD USE CURRENT STATE OR NEXT STATE...
		if (acCurr.getNumAnts() <= 10 && acCurr.getCurrentState() != 2) {
			if (acCurr.getNumHomePheromones() > maxHomePheromones) {
				maxOrientation = forward;
			}
		}
		if (acRight.getNumAnts() <= 10 && acRight.getCurrentState() != 2) {
			if (acRight.getNumHomePheromones() > maxHomePheromones) {
				maxOrientation = forwardRight;
			}
		}
		if (acLeft.getNumAnts() <= 10 && acLeft.getCurrentState() != 2) {
			if (acLeft.getNumHomePheromones() > maxHomePheromones) {
				maxOrientation = forwardLeft;
			}
		}
		return maxOrientation;
	}
	
	/**
	 * 	Orientations are as follows:  
	 *  0 1 2
	 *  7 a 3
	 *  6 5 4
	 * @param antCell
	 * @return -1 if no valid neighbors, else return neighbor orientation with most home pheromones
	 */
	private int findNeighborWithMaxHomePheromones(AntCell antCell) {
		int maxNumHomePheromones = 0;
		int maxIndex = -1;
		SquareNeighbors neighbor = new SquareNeighbors(getGrid(), wrapping);
		List<Cell> neighbors = neighbor.getOrderedNeighbors(antCell);
		for(int i = 0; i < neighbors.size(); i++) {
			AntCell ac = (AntCell) neighbors.get(i);
			// Check if neighbor position has less than or equal to 10 ants and that it isn't a blocked position
			if (ac.getNumAnts() <= 10 && ac.getCurrentState() != 2) {
				if (ac.getNumHomePheromones() > maxNumHomePheromones) {
					maxNumHomePheromones = ac.getNumHomePheromones();
				}
			}
		}
		return maxIndex;
	}

	private void antFindFoodSource(AntCell antCell) {
		return;
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