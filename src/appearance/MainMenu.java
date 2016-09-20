package appearance;



import appearance.pane.GeneralPane;
import javafx.scene.Group;

import javafx.scene.Scene;


import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainMenu {
	
	private Scene myScene;
	private Group root;
	private Stage myStage;
	
	public Scene init(Stage s, int width, int height){
    	myStage = s;
    	root = new Group();
    	myScene = new Scene(root, width, height, Color.DIMGRAY);
    	GeneralPane myPane = new GeneralPane(width, height);
    	root.getChildren().add(myPane.getMainPane());
    	myStage.setScene(myScene);
        myStage.show();
        setScreen();
        return myScene;
	}
	
    private void setScreen() {

    }
	
}
