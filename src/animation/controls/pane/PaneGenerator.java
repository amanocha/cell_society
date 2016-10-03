package animation.controls.pane;


import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;


/**
 * This is the Pane generator class, it makes the pane GUIs for each page - including the main page, 
 * the simulation page, and the xml page.
 * 
 * @author Hannah Fuchshuber
 */

public class PaneGenerator {
	
	private Pane myMainMenu;
	private Pane mySimulationMenu;
	private Pane xmlMenuPane;
	private Scene myScene;
	
	public PaneGenerator(Scene scene) {
		this.myScene = scene;
		myMainMenu = createMainMenuPane();
		mySimulationMenu = createSimulationMenuPane();
		xmlMenuPane = createXMLMenuPane();
	}

	/**
	 * Creates the main menu pane
	 * @return Main Menu Pane
	 */
	private Pane createMainMenuPane() {
		GridPane grid = new GridPane();
		grid.prefHeightProperty().bind(myScene.heightProperty());
        grid.prefWidthProperty().bind(myScene.widthProperty());
        for (int i = 1; i < 4; i++) {
			int num = 10;
			RowConstraints row = new RowConstraints();
			if (i == 1) {
				num = 30;
			}
			row.setPercentHeight(num);
			grid.getRowConstraints().add(row);
		}
		grid.setVgap(10);
		for (int i = 1; i < 3; i++) {
			int num = 25;
			ColumnConstraints column = new ColumnConstraints();
			if (i == 2) {
				num = 50;
			}
			column.setPercentWidth(num);
			grid.getColumnConstraints().add(column);
		}
		return grid;
	}
	
	
	/**
	 * Creates the Simulation Stack Pane
	 * @return StackPane
	 */
	public Pane createSimulationMenuPane() {
		StackPane pane = new StackPane();
		pane.setPrefHeight(myScene.getHeight());
        pane.setPrefWidth(myScene.getWidth());
        pane.setMouseTransparent(true);
		return pane; 
	}
	
	/**
	 * Creates the Xml Menu Pane
	 * @return Pane
	 */
	private Pane createXMLMenuPane() {
		return new Pane();
	}
	
	/**
	 * Getter for the Simulation Pane
	 * @return StackPane
	 */
	public StackPane getSimulationMenuPane() {
		return (StackPane) mySimulationMenu;
	}
	
	/**
	 * Getter for the main menu pane
	 * @return Pane
	 */
	public GridPane getMainMenuPane() {
		return (GridPane) myMainMenu;
	}
	
	
	/**
	 * Getter for the Xml Menu Pane
	 * @return
	 */
	public Pane getXMLMenuPane() {
		return xmlMenuPane;
	}
	
}
