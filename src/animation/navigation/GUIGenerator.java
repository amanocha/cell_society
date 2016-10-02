package animation.navigation;


import java.util.ResourceBundle;
import animation.controls.button.ButtonString;
import animation.controls.label.Header;
import animation.controls.label.Message;
import animation.controls.pane.PaneGenerator;
import animation.navigation.menu.Menu;
import engine.Loop;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import readxml.XmlMapper;



public class GUIGenerator {

	
	private PaneGenerator myPane;
	private Menu myMenu;
	private Scene myScene;
	private ResourceBundle myResource;
	private XmlMapper myInfo;
	private Navigator myNav;
	private Group root;
	
	public GUIGenerator(Scene scene, Group r, ResourceBundle resource, XmlMapper info) {
		this.myScene = scene;
		this.myResource = resource;
		myPane = new PaneGenerator(scene);
		myResource = resource;
		myInfo = info;
		myNav = new Navigator(scene, r, info);
		root = r;
	}


	public Pane generateMainScreen() {
		GridPane grid = myPane.getMainMenuPane();
		Label header = (new Header(myResource.getString("Title"))).getLabel(); 
		grid.add(header, 1, 0);
		grid.add(startButton(), 1, 1);
		grid.add(parameterButton(), 1, 2);
		Label text = (new Message(myResource.getString("CurrentSimulation"))).getLabel();
		text.setPrefWidth(myScene.getWidth() * .5);
		grid.add(text, 1, 4);
		return grid;
	}
	
	public Button generateSimulationScreenMainButton(Loop loop) {
		Button main = (new ButtonString(myResource.getString("MainMenu"))).getButton();
		main.setOnAction(e -> {
			myNav.createMainMenu();
			loop.getSimulationGUI().stopAnimation();
		});
		main.setPrefWidth(myScene.getWidth() * .20);
		main.setLayoutX(myScene.getWidth() * .73);
		main.setLayoutY(myScene.getHeight() * .8);
		return main;
	}

/*
	public Pane generateXMLScreen() {
		Pane grid = myPane.getXMLMenuPane();
		//Slider slider = createCellNumberSlider();
		ComboBox<String> combo = makeGameSelectionCombo();
		combo.setOnAction(e -> {
			myNav.createXmlMenu();
		});
		Button button = makeMainMenuButton();
		button.setOnAction(e -> {
			//UserInputToXML input = new UserInputToXML(slider.getValue());
			myNav.createMainMenu();
		});
		grid.getChildren().add(slider);
		grid.getChildren().add(combo);
		grid.getChildren().add(makeXmlTitle());
		grid.getChildren().add(button);
		return grid;
	}
	*/
	public Node generateSimulationScreenLabel() {
		Label label = (new Header(myResource.getString("SimulationLabel"))).getLabel();
		label.setLayoutX(myScene.getWidth() * .07);
		label.setLayoutY(myScene.getHeight() * .06);
		return label;
	}
	
	
	
	private Button startButton() {
		Button button = (new ButtonString(myResource.getString("Start"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> {
			myInfo.mapXml(myInfo.getMeta().getFileName());
			myNav.createSimluationMenu();
		});
		return button;
	}
	
/*	private Label makeXmlTitle() {
		Label title = (new Header(myResource.getString("SimulationSelection"))).getLabel();
		title.setLayoutX(myScene.getWidth() * .34);
		title.setLayoutY(myScene.getHeight() * .1);
		return title;
	}*/
	
	private Button parameterButton() {
		Button button = (new ButtonString(myResource.getString("ParameterButton"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> myNav.createXmlMenu(null));
		return button;
	}
	/*
	private Button makeMainMenuButton() {
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
	*/
/*	
	private Label makeXmlTitle() {
		Label title = (new Header(myResource.getString("SimulationSelection"))).getLabel();
		title.setLayoutX(myScene.getWidth() * .34);
		title.setLayoutY(myScene.getHeight() * .2);
		return title;
	}
	
	private Button makeMainMenuButton() {
		Button button = (new ButtonString(myResource.getString("MainMenu"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .25);
		button.setLayoutX(myScene.getWidth() * .38);
		button.setLayoutY(myScene.getHeight() * .6);
		return button;
	}
	
	
	private ComboBox<String> makeGameSelectionCombo() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .3, myScene.getWidth() * .25, SIMULATIONS);
	}
	
	private Slider makeSlider() {
		return createGeneralSlider(0, 100, 50, 10, myScene.getWidth() * .30, myScene.getHeight() * .7, myScene.getWidth() * .25);
	}
	
	private Button startButton() {
		Button button = (new ButtonString(myResource.getString("Start"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> {
			myInfo.mapXml(myInfo.getMeta().getFileName());
			myNav.createSimluationMenu();
		});
		return button;
	}
	
	private Button parameterButton() {
		Button button = (new ButtonString(myResource.getString("ParameterButton"))).getButton();
		button.setPrefWidth(myScene.getWidth() * .5);
		button.setOnAction(e -> myNav.createXmlMenu());
		return button;
	}
	
	private Slider createProbSlider() {
		return createGeneralSlider(0, 1, 0.5, 0.1, myScene.getWidth() * .30, myScene.getHeight() * .8, myScene.getWidth() * .25);
	}
	
	private Slider createEnergySlider() {
		return createGeneralSlider(0, 50, 22, 5, myScene.getWidth() * .30, myScene.getHeight() * .9, myScene.getWidth() * .25);
	}
	
	private Slider createReproductiveSlider() {
		return createGeneralSlider(3, 20, 8, 5, myScene.getWidth() * .30, myScene.getHeight() * .2, myScene.getWidth() * .25);
	}
	
	private ComboBox createShapeComboBox() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .5, myScene.getWidth() * .25, SHAPES);
	}
	
	private ComboBox createGridTypeComboBox() {
		return createGeneralComboBox(myScene.getWidth() * .38, myScene.getHeight() * .6, myScene.getWidth() * .25, GRID);
	}
	
	private Slider createGeneralSlider(int start, int end, double position, double tickunit, double x, double y, double minwidth) {
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
	
	private ComboBox<String> createGeneralComboBox(double x, double y, double minwidth, ObservableList<String> list) {
		ComboBox<String> combo = new ComboBox<String>();
		combo.setItems(list);
		combo.setLayoutX(x);
		combo.setLayoutY(y);
		combo.setMinWidth(minwidth);
		return combo;
	}*/

	
}
