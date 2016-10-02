package animation.navigation.xmlselections;

import java.util.ResourceBundle;

import animation.controls.button.ButtonString;
import animation.controls.label.Header;
import animation.controls.label.SmallLabel;
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
import readxml.XMLGenerator.UserInputToXML;

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
			"TOROADAL" );
	
	private PaneGenerator myPane;
	private ResourceBundle myResource;
	private Navigator myNav;
	private Scene myScene;
	private Pane myScreen;
	private ComboBox<String> mySelector;
	private String myShape;
	private String gridType;
	//private int myCellNumber;
	private Slider cellNumberSlider;

	public XmlSelection(Scene scene, Group r, XmlMapper info, ResourceBundle resource) {
		myPane = new PaneGenerator(scene);
		myResource = resource;
		myNav = new Navigator(scene, r, info);
		myScene = scene;
		System.out.println(myScreen);
	}
	
	public Pane generateXMLScreen() {
		myScreen = myPane.getXMLMenuPane();
		mySelector = makeSimulationSelectionCombo();
		mySelector.setOnAction(e -> {
			myNav.createXmlMenu(mySelector.getValue().toString());
		});
		ComboBox<String> combo = createShapeComboBox(); 
		combo.setOnAction(e -> {
			myShape = combo.getValue();
			System.out.println(myShape);
		});
		myScreen.getChildren().add(createShapeLabel());
		myScreen.getChildren().add(createGridTypeLabel());
		myScreen.getChildren().add(combo);
		ComboBox<String> combo1 = createGridTypeComboBox();
		combo1.setOnAction(e -> {
			gridType = combo1.getValue();
			System.out.println(gridType);
		});
		myScreen.getChildren().add(combo1);
		myScreen.getChildren().add(createCellNumberSlider());
		myScreen.getChildren().add(mySelector);
		myScreen.getChildren().add(makeXmlTitle());
		myScreen.getChildren().add(createSimulationLabel());
		myScreen.getChildren().add(createCellNumberLabel());
		return myScreen;
	}
	
	public UserInputToXML startXMLMap() {
		UserInputToXML input = new UserInputToXML(getCellNumber(), 2);
		input.setShape(getShape());
		input.setWrapping(getWrapping());
		return input;
	}

	
	
	public String getWrapping() {
		if (gridType.equals(myResource.getString("ToroidalLabel"))) {
			return myResource.getString("Toroidalxml");
		} else if (gridType.equals(myResource.getString("NormalLabel"))) {
			return myResource.getString("Normalxml");
		}
		return myResource.getString("Normalxml");
	}
	
	public Pane getScreen() {
		return myScreen;
	}
	
	public String getShape() {
		if (myShape.equals(myResource.getString("SquareLabel"))) {
			return myResource.getString("Squarexml");
		} else if (myShape.equals(myResource.getString("HexagonLabel"))) {
			return myResource.getString("Hexagonxml");
		} else if (myShape.equals(myResource.getString("TriangleLabel"))) {
			return myResource.getString("Trianglexml");
		}
		return myResource.getString("Squarexml");
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
		return (int) cellNumberSlider.getValue() * (int) cellNumberSlider.getValue();
	}
	
	public Label createSimulationLabel() {
		return createSmallLabel(myResource.getString("SimulationLabel"), myScene.getWidth() * .22, myScene.getHeight() * .2);
	}
	
	public Label createCellNumberLabel() {
		return createSmallLabel(myResource.getString("CellNumberLabel"), myScene.getWidth() * .3, myScene.getHeight() * .31);
	}
	
	public Label createShapeLabel() {
		return createSmallLabel(myResource.getString("ShapeLabel"), myScene.getWidth() * .3, myScene.getHeight() * .4);
	}
	
	public Label createGridTypeLabel() {
		return createSmallLabel(myResource.getString("GridTypeLabel"), myScene.getWidth() * .3, myScene.getHeight() * .5);
	}

	public ComboBox<String> createShapeComboBox() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .4, myScene.getWidth() * .25, SHAPES);
	}
	
	public ComboBox<String> createGridTypeComboBox() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .5, myScene.getWidth() * .25, GRID);
	}
	
	private Label makeXmlTitle() {
		Label title = (new Header(myResource.getString("SimulationSelection"))).getLabel();
		title.setLayoutX(myScene.getWidth() * .34);
		title.setLayoutY(myScene.getHeight() * .08);
		return title;
	}
	
	
	public Button makeMainMenuButton() {
		Button button = (new ButtonString(myResource.getString("MainMenu"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .25);
		button.setLayoutX(myScene.getWidth() * .38);
		button.setLayoutY(myScene.getHeight() * .9);
		return button;
	}
	
	
	private ComboBox<String> makeSimulationSelectionCombo() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .2, myScene.getWidth() * .25, SIMULATIONS);
	}
	
	private Slider createCellNumberSlider() {
		cellNumberSlider = createGeneralSlider(0, 50, 25, 10, myScene.getWidth() * .4, myScene.getHeight() * .3, myScene.getWidth() * .25);
		return cellNumberSlider;
	}
	
	public Slider createGeneralSlider(int start, int end, double position, double tickunit, double x, double y, double minwidth) {
		Slider slider = new Slider(start, end, position);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(tickunit);
		slider.setMinorTickCount(1);
		slider.setBlockIncrement(1);
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
	
	public Label createSmallLabel(String s, double x, double y) {
		Label label = (new SmallLabel(s)).getLabel();
		label.setLayoutX(x);
		label.setLayoutY(y);
		return label;
	}

	
}
