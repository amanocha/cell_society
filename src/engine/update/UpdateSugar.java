package engine.update;

import java.util.ArrayList;
import java.util.List;

import engine.neighbors.Neighbor;
import structures.cell.Cell;
import structures.cell.SugarCell;
import structures.Grid;

public class UpdateSugar extends Update {
	private Grid grid;
	private Neighbor neighbor;
	private int sugarGrowBackTime;
	private int vision;
	private int sugarMetabolism;
	
	public UpdateSugar(Grid newGrid, Neighbor newNeighbors) {
		super(newGrid, newNeighbors);
		grid = newGrid;
		neighbor = newNeighbors;
		sugarGrowBackTime = ((SugarCell) newGrid.getCellList().get(0)).getSugarGrowBackTime();
		vision = ((SugarCell) newGrid.getCellList().get(0)).getVision();
		sugarMetabolism = ((SugarCell) newGrid.getCellList().get(0)).getSugarMetabolism();
	}
	
	@Override
	public List<Cell> getNeighbors(Cell cell) {
		List<Cell> neighbors = neighbor.getSurroundingNeighbors(cell, vision);
		return neighbors;
	}
	
	
	public Cell getMaxSugarCell(ArrayList<Cell> patches) {
		int max = 0, currentSugar;
		Cell maxSugarCell = patches.get(0);
		for (Cell patch : patches) {
			currentSugar = ((SugarCell) patch).getPatchSugar();
			if (currentSugar > max) {
				max = currentSugar;
				maxSugarCell = patch;
			}
		}
		return maxSugarCell;
	}
	
	public void swap(SugarCell sugar1, SugarCell sugar2) { //sugar1 becomes patch, sugar2 becomes agent
		int sugar1State = sugar1.getCurrentState();
		sugar1.setNextState(sugar2.getCurrentState());
		sugar2.setNextState(sugar1State);
		sugar2.setSugar(sugar1.getSugar() + sugar2.getPatchSugar());
		sugar1.setSugar(0);
		sugar1.setPatchSugar(0); //previous agent should already have 0 patch sugar
		sugar2.setPatchSugar(0);
		sugar1.setSugarTime(0); //previous agent should already have 0 sugar time
		sugar2.setSugarTime(0);
	}
	
	/**
	 * (0 = 0 sugar, 1 = 1 sugar, 2 = 2 sugar, 3 = 3 sugar, 4 = 4 sugar, 5 = 5 sugar, 6 = agent)
	 */
	@Override
	public void determineUpdates() {
		ArrayList<Cell> agents = new ArrayList<Cell>();
		ArrayList<Cell> patches = new ArrayList<Cell>();
		for(Cell cell : grid.getCellList()) {
			if (cell.getCurrentState() == 6) { //agent
				agents.add(cell);
			} else { //patch
				agents.add(cell);
			}
		}
		determinePatchUpdates(patches);
		displayCells(5);
		determineAgentUpdates(agents);
		displayCells(6);
	}
	
	public void determinePatchUpdates(ArrayList<Cell> patches) {
		for (Cell patch : patches) {
			if (((SugarCell) patch).getSugarTime() == sugarGrowBackTime) {
				int capacity = ((SugarCell) patch).getPatchSugarCapacity();
				if (((SugarCell) patch).getPatchSugar() < capacity) {
					((SugarCell) patch).setPatchSugar(((SugarCell) patch).getPatchSugar() + 1);
					patch.setNextState(patch.getCurrentState() + 1);
					((SugarCell) patch).setSugarTime(-1);
				}
			}
		}
	}
	
	public void determineAgentUpdates(ArrayList<Cell> agents) {
		ArrayList<Cell> patches = new ArrayList<Cell>();
		for (Cell agent : agents) {
			for (Cell neighbor : getNeighbors(agent)) {
				if (neighbor.getCurrentState() != 6) {
					patches.add(neighbor);
				}
			}
			if (patches.size() != 0) {
				Cell maxSugarCell = getMaxSugarCell(patches);
				swap((SugarCell) agent, (SugarCell) maxSugarCell); //move agent to patch
				((SugarCell)maxSugarCell).setSugar(((SugarCell)maxSugarCell).getSugar() - sugarMetabolism);
				if (((SugarCell)maxSugarCell).getSugar() <= 0) {
					maxSugarCell.setNextState(0);
				}
			} else {
				((SugarCell)agent).setSugar(((SugarCell)agent).getSugar() - sugarMetabolism);
				if (((SugarCell)agent).getSugar() <= 0) {
					agent.setNextState(0);
				}
			}
		}
	}
	
	public void displayCells(int sugarState) {
		for(Cell cell : grid.getCellList()) {
			cell.setPreviousState(cell.getCurrentState());
			cell.setCurrentState(cell.getNextState());
			SugarCell sugar = (SugarCell) cell; 
			if (sugarState != 6) {
				sugar.setSugarTime(sugar.getSugarTime() + 1);
			}
		}
	}
	/**
	 * Depends on cellList (in Grid.java) containing Animal objects.
	 */
	@Override
	public void updateCells() {
		// there are separate methods for updating agents and patches individually, so this method is blank
	}
}