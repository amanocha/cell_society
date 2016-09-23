package animation.controls;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class GeneralPane {
	
	private Pane myMainMenu;
	private Pane mySimulationMenu;
	private Pane xmlMenuPane;
	private Scene myScene;
	
	public GeneralPane(Scene scene) {
		this.myScene = scene;
		myMainMenu = createMainMenuPane();
		mySimulationMenu = createSimulationMenuPane();
		xmlMenuPane = createXMLMenuPane();
	}
	
	private GridPane generateGrid(int col, int row, int colsize, int rowsize) {
		GridPane grid = new GridPane();
		grid.prefHeightProperty().bind(myScene.heightProperty());
        grid.prefWidthProperty().bind(myScene.widthProperty());
        for (int i = 1; i < row; i++) {
			int num = rowsize;
			RowConstraints roww = new RowConstraints();
			if (i == 1) {
				num = 30;
			}
			roww.setPercentHeight(num);
			grid.getRowConstraints().add(roww);
		}
		grid.setVgap(10);
		for (int i = 1; i < col; i++) {
			int num = colsize;
			ColumnConstraints column = new ColumnConstraints();
			if (i == 2) {
				num = 50;
			}
			column.setPercentWidth(num);
			grid.getColumnConstraints().add(column);
		}
		return grid;
		
	}

	private Pane createMainMenuPane() {
		return generateGrid(3, 4, 25, 10);
	}
	
	public GridPane getMainMenuPane() {
		return (GridPane) myMainMenu;
	}
	
	public Pane createSimulationMenuPane() {
		StackPane pane = new StackPane();
		TilePane baby = createSimulationPane();
		pane.setPrefHeight(myScene.getHeight());
        pane.setPrefWidth(myScene.getWidth());
        double left = myScene.getWidth() * .1;
        double top = myScene.getHeight() * .65;
        double other = myScene.getHeight() * .1;
        StackPane.setMargin(baby, new Insets(left, top, other, other));
        pane.getChildren().add(baby);
        pane.setMouseTransparent(true);
		return pane; 
	}
	
	public StackPane getSimulationMenuPane() {
		return (StackPane) mySimulationMenu;
	}
	
	public TilePane createSimulationPane() {
		TilePane pane = new TilePane();
		pane.setStyle("-fx-background-color: #000000;");
		pane.setMaxSize((myScene.getWidth() * 3) / 4, (myScene.getHeight() * 3) / 4);
		pane.setPrefHeight((myScene.getWidth() * 3) / 4);
		pane.setPrefWidth((myScene.getWidth() * 3) / 4);
		return pane; 
	}
	
	private Pane createXMLMenuPane() {
		return generateGrid(0, 6, 0, 10);
	}
	
	public GridPane getXMLMenuPane() {
		return (GridPane) xmlMenuPane;
	}
	
}
