package animation.simulation.shape;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import animation.simulation.color.CellColor;
import javafx.scene.layout.Pane;
import structures.Grid;

/**
 * This is the abstract GridShape class, which each type of shape extends in order to draw the different types of grids.
 * 
 * @author Hannah Fuchshuber
 */


public abstract class GridShape {
	
	private Pane screen;
	private CellColor myColor;
	private Map<Integer, Integer> statesList;
	
	
	/**
	 * Sets up the instance variables
	 */
	public GridShape() {
		this.screen = new Pane();
		statesList = new HashMap<Integer, Integer>();
	}
	
	/**
	 * Sets up the pane for drawing the grid
	 */
	public Pane drawGrid(Grid grid, int w, int h) {
		statesList = new HashMap<Integer, Integer>();
		screen.getChildren().clear();
		screen.setMaxWidth(w); //adjust for grid outline
		screen.setMaxHeight(h); //adjust for grid outline
		return screen;
	}
	
	/**
	 * Makes an array list of all the states 
	 */
	public void insertStatesList(int state) {
		if (statesList.get(state) == null) {
			statesList.put(state, 1);
		} else {
			statesList.put(state, statesList.get(state) + 1);
		}
	}
	
	/**
	 * Iterator for the cell List to create the line chart
	 * @return
	 */
	public Iterator<Integer> iterator() {
        Iterator<Integer> iterator = new Iterator<Integer>() {

        	private int currentIndex = 0;

            @Override
            public boolean hasNext() {
            	return currentIndex < statesList.size() && statesList.get(currentIndex) != null;
            }

            @Override
            public Integer next() {
                return statesList.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }

	/**
	 * Getter for the cell color
	 * @return
	 */
	public CellColor getColor() {
		return myColor;
	}
	
	/**
	 * Gets the main screen
	 */
	public Pane getScreen() {
		return screen;
	}
	
	/**
	 * Set the color to the Cellcolor
	 */
	public void setColor(CellColor color) {
		myColor = color;
	}
	

}

	
	
	