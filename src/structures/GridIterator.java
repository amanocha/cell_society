package structures;

import java.util.ArrayList;
import java.util.Iterator;

public class GridIterator<Cell> implements Iterable<Cell> {

    private ArrayList<Cell> cellList;
    private int currentSize;

    public GridIterator(ArrayList<Cell> cellList) {
        this.cellList = cellList;
        this.currentSize = cellList.size();
    }

    @Override
    public Iterator<Cell> iterator() {
        Iterator<Cell> it = new Iterator<Cell>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && cellList.get(currentIndex) != null;
            }

            @Override
            public Cell next() {
                return cellList.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}