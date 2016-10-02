package animation.simulation.shape;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import animation.simulation.color.CellColor;
import javafx.scene.layout.Pane;
import structures.Grid;


public abstract class GridShape {
	

	private Pane screen;
	private CellColor myColor;
	private Map<Integer, Integer> statesList;
	
	public GridShape() {
		this.screen = new Pane();
		statesList = new HashMap<Integer, Integer>();
	}
	
	
	public Pane drawGrid(Grid grid, int w, int h) {
		statesList = new HashMap<Integer, Integer>();
		screen.getChildren().clear();
		screen.setMaxWidth(w); //adjust for grid outline
		screen.setMaxHeight(h); //adjust for grid outline
		return screen;
	}
	
	public Pane getScreen() {
		return screen;
	}
	
	public void setColor(CellColor color) {
		myColor = color;
	}
	
	public void insertStatesList(int state) {
		if (statesList.get(state) == null) {
			statesList.put(state, 1);
		} else {
			statesList.put(state, statesList.get(state) + 1);
		}
	}
	
	public int numberOfStates() {
		return statesList.size();
	}
	
	public CellColor getColor() {
		return myColor;
	}
	
	public Iterator<Integer> iterator() {
        Iterator<Integer> iterator = new Iterator<Integer>() {

        	private int currentIndex = 0;

            @Override
            public boolean hasNext() {
            	System.out.println(statesList);
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

	

}

	
	
	