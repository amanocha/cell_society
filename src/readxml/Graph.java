package readxml;
import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
	private int num_vertices;
	private int num_edges;
	private ArrayList<ArrayList<Cell>> cell_neighbors;
	
	public Graph() {
		num_vertices = 0;
		num_edges = 0;
		cell_neighbors = new ArrayList<ArrayList<Cell>>();
	}
	
	public void addEdge(Cell cell1, Cell cell2) {
		cell_neighbors.get(cell1.getNumber()).add(cell2);
		cell_neighbors.get(cell2.getNumber()).add(cell1);
	}
	
	/**
	 * Adds a new cell to the graph that does not have any neighbors and therefore is not connected to any other cells.
	 * This method assumes that the cell's number was set properly to be one more than the number of cells in the graph 
	 * (as cells are created, they are added to the graph), so that its index is correct.
	 * @param cell - the new cell
	 */
	public void addCell(Cell cell) {
		cell_neighbors.add(new ArrayList<Cell>());
		num_vertices++;
	}
	
	/**
	 * Adds a new cell to the graph and connects it to each of the cells in a list of specific cells
	 * @param cell - the new cell
	 * @param neighbors - the list of specific cells to connect to the new cell
	 */
	public void addCell(Cell cell, ArrayList<Cell> neighbors) {
		cell_neighbors.add(new ArrayList<Cell>());
		for (Cell neighbor: neighbors) {
			addEdge(cell, neighbor);
		}
	}
	
	/**
	 * Gets the neighbors (cells) of a specified cell.
	 * @param cell - the specified cell
	 * @return list of the neighbors of the cell
	 */
	public ArrayList<Cell> getNeighbors(Cell cell) {
		return cell_neighbors.get(cell.getNumber());
	}
	
	/**
	 * Determines the degree (number of neighbors) of a specified cell. 
	 * @param cell - the specified cell
	 * @return the degree of the cell (number of neighbors)
	 */
	public int getDegree(Cell cell) {
		return getNeighbors(cell).size();
	}
	
	/*****GETTERS*****/
	
	/**
	 * Gets the number of vertices (cells) in the graph.
	 * @return the number of vertices 
	 */
	public int getNumVertices() {
		return num_vertices;
	}
	
	/**
	 * Gets the number of edges in the graph.
	 * @return the number of edges
	 */
	public int getNumEdges() {
		return num_edges;
	}
	
	public Iterator<Cell> iterator() {
		return null;
	}
	
	/**
	 * Gets the graph (as a list of lists of neighbors).
	 * @return the list of lists
	 */
	public ArrayList<ArrayList<Cell>> getGraph() {
		return cell_neighbors;
	}
}