package appearance.pane;

import java.util.HashMap;

import appearance.buttons.GeneralButton;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GeneralPane {
	
	private HashMap<String, GridPane> map  = new HashMap<String, GridPane>();
	private double width;
	private double height;
	
	public GeneralPane(double w, double h) {
		this.width = w;
		this.height = h;
		createPane();
	}
	
	public GridPane getMainPane() {
		return map.get("MainMenu");
	}
	
	
	
	private void createPane() {
		GridPane pane = new GridPane();
    	pane.setPrefSize(width, height);
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        column1.setPercentWidth(20);
        column2.setPercentWidth(60);
        column3.setPercentWidth(20);
        pane.getColumnConstraints().addAll(column1, column2, column3);
    	RowConstraints row1 = new RowConstraints(); 
    	RowConstraints row2 = new RowConstraints(); 
    	RowConstraints row3 = new RowConstraints(); 
    	RowConstraints row4 = new RowConstraints(); 
    	row1.setPercentHeight(30);
    	row2.setPercentHeight(10);
    	row3.setPercentHeight(10);
    	row4.setPercentHeight(10);
    	pane.getRowConstraints().addAll(row1, row2, row3, row4);
    	pane.setVgap(10);
        GeneralButton button = new GeneralButton();
        button.createButton1("START", width * .6);
        button.createButton1("CHANGE PARAMETERS", width * .6);
    	pane.add(button.getButton1("START"), 1, 1);
    	pane.add(button.getButton1("CHANGE PARAMETERS"), 1, 2);
    	map.put("MainMenu", pane);
	}
}
