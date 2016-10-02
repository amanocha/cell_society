package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import animation.controls.button.ButtonString;
import animation.controls.label.Header;
import animation.controls.pane.PaneGenerator;
import animation.navigation.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;

public class XmlSelection {
	
	private static ObservableList<String> SIMULATIONS = 
		    FXCollections.observableArrayList(
		    		"SEGREGATION", 
		    		"GAME OF LIFE", 
		    		"FIRE", 
		    		"WA-TOR" );

	private static ObservableList<String> SHAPES = FXCollections.observableArrayList(
			"SQUARE",
			"HEXAGON",
			"TRIANGLE");
	
	private static ObservableList<String> GRID = FXCollections.observableArrayList(
			"NORMAL",
			"TORODIAL",
			"INFINITE" );
	
	private PaneGenerator myPane;
	private ResourceBundle myResource;
	private Navigator myNav;
	private Scene myScene;
	private Pane myScreen;
	private ComboBox<String> mySelector;
	private String myShape;
	private String gridType;
	private int myCellNumber;

	public XmlSelection(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		myPane = new PaneGenerator(scene);
		myResource = resource;
		myNav = new Navigator(scene, r, info);
		myScene = scene;
		myScreen = generateXMLScreen();
		System.out.println(myScreen);
	}
	
	public Pane generateXMLScreen() {
		myScreen = myPane.getXMLMenuPane();
		Slider slider = createCellNumberSlider();
		slider.setOnDragDone(e -> myCellNumber = (int) slider.getValue());
		myCellNumber = (int) slider.getValue();
		mySelector = makeGameSelectionCombo();
		mySelector.setOnAction(e -> {
			myNav.createXmlMenu(mySelector.getValue().toString());
		});
		myScreen.getChildren().add(slider);
		myScreen.getChildren().add(mySelector);
		myScreen.getChildren().add(makeXmlTitle());
		return myScreen;
	}
	
	public void addGridOptions() {
		ComboBox<String> combo = createShapeComboBox(); 
		combo.setOnAction(e -> myShape = combo.getValue());
		myScreen.getChildren().add(combo);
		ComboBox<String> combo1 = createGridTypeComboBox();
		combo.setOnAction(e -> gridType = combo1.getValue());
		myScreen.getChildren().add(combo1);
	}
	
	public Pane getScreen() {
		return myScreen;
	}
	
	public String getShape() {
		return myShape;
	}
	
	public ComboBox<String> getSimulationCombo() {
		return mySelector;
	}
	
	public Navigator getNavigator() {
		return myNav;
	}
	
	public String getGridType() {
		return gridType;
	}
	
	public int getCellNumber() {
		return myCellNumber * myCellNumber;
	}

	public void setComboBox(String str) {
		mySelector.setValue(str);
	}
	
	public Slider createProbSlider() {
		return createGeneralSlider(0, 1, 0.5, 0.1, myScene.getWidth() * .30, myScene.getHeight() * .8, myScene.getWidth() * .25);
	}

	public ComboBox<String> createShapeComboBox() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .5, myScene.getWidth() * .25, SHAPES);
	}
	
	public ComboBox<String> createGridTypeComboBox() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .6, myScene.getWidth() * .25, GRID);
	}
	
	private Label makeXmlTitle() {
		Label title = (new Header(myResource.getString("SimulationSelection"))).getLabel();
		title.setLayoutX(myScene.getWidth() * .34);
		title.setLayoutY(myScene.getHeight() * .1);
		return title;
	}
	
	
	protected Button makeMainMenuButton() {
		Button button = (new ButtonString(myResource.getString("MainMenu"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .25);
		button.setLayoutX(myScene.getWidth() * .38);
		button.setLayoutY(myScene.getHeight() * .9);
		return button;
	}
	
	
	private ComboBox<String> makeGameSelectionCombo() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .3, myScene.getWidth() * .25, SIMULATIONS);
	}
	
	private Slider createCellNumberSlider() {
		return createGeneralSlider(0, 100, 50, 10, myScene.getWidth() * .30, myScene.getHeight() * .7, myScene.getWidth() * .25);
	}
	
	public Slider createGeneralSlider(int start, int end, double position, double tickunit, double x, double y, double minwidth) {
		Slider slider = new Slider(start, end, position);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(tickunit);
		slider.setMinorTickCount(1);
		//slider.setBlockIncrement(10);
		slider.setLayoutX(x);
		slider.setLayoutY(y);
		slider.setMinWidth(minwidth);
		return slider;
	}
	
	public ComboBox<String> createGeneralComboBox(double x, double y, double minwidth, ObservableList<String> list) {
		ComboBox<String> combo = new ComboBox<String>();
		combo.setItems(list);
		combo.setLayoutX(x);
		combo.setLayoutY(y);
		combo.setMinWidth(minwidth);
		return combo;
	}

	
}
