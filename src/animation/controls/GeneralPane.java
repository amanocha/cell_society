package animation.controls;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GeneralPane {

	private GridPane myGrid;
	
	public GeneralPane(double width, double height) {
		myGrid = new GridPane(); 
		for (int i = 0; i < 3; i++) {
		int num = 10;
			RowConstraints row = new RowConstraints();
			if (i == 0) {
				num = 30;
			}
			row.setPercentHeight(num);
			myGrid.getRowConstraints().add(row);
		}
		myGrid.setVgap(10);
		for (int i = 0; i < 2; i++) {
			int num = 20;
			ColumnConstraints column = new ColumnConstraints();
			if (i == 1) {
				num = 60;
			}
			column.setPercentWidth(num);
			myGrid.getColumnConstraints().add(column);
		}
		GeneralButton button = new GeneralButton("START");
		button.setWidth(width * .6);
		myGrid.add(button.getStringButton(), 1, 1);
		button = new GeneralButton("CHANGE PARAMETERS");
		button.setWidth(width * .6);
		myGrid.add(button.getStringButton(), 1, 2);
	}
	
	public GridPane getMainPage() {
		return myGrid;
	}
	
}
