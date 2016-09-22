package animation.controls;


import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

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

	private Pane createMainMenuPane() {
		GridPane grid = new GridPane();
		grid.prefHeightProperty().bind(myScene.heightProperty());
        grid.prefWidthProperty().bind(myScene.widthProperty());
		for (int i = 0; i < 3; i++) {
			int num = 10;
			RowConstraints row = new RowConstraints();
			if (i == 0) {
				num = 30;
			}
			row.setPercentHeight(num);
			grid.getRowConstraints().add(row);
		}
		grid.setVgap(10);
		for (int i = 0; i < 2; i++) {
			int num = 25;
			ColumnConstraints column = new ColumnConstraints();
			if (i == 1) {
				num = 50;
			}
			column.setPercentWidth(num);
			grid.getColumnConstraints().add(column);
		}
		return grid;
	}
	
	public GridPane getMainMenuPane() {
		return (GridPane) myMainMenu;
	}
	
	private Pane createSimulationMenuPane() {
		StackPane pane = new StackPane();
		pane.prefHeightProperty().bind(myScene.heightProperty());
        pane.prefWidthProperty().bind(myScene.widthProperty());
		return pane;
	}
	
	public StackPane getSimulationMenuPane() {
		return (StackPane) mySimulationMenu;
	}
	
	public StackPane createSimulationPane() {
		StackPane pane = new StackPane();
		pane.setStyle("-fx-background-color: black;");
		pane.setMaxSize((myScene.getWidth() * 3) / 4, (myScene.getHeight() * 3) / 4);
		return pane; 
	}
	
	private Pane createXMLMenuPane() {
		return null;
	}
	
	public Pane getXMLMenuPane() {
		return xmlMenuPane;
	}
	
}
