package animation.Screen;

import animation.controls.GeneralPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Screen {
	
	private Stage myStage;
	private Group root;
	private Scene myScene;
	private GeneralPane myPane;
	

	public Scene init(Stage s, int width, int height) {
		myStage = s;
    	root = new Group();
    	myScene = new Scene(root, width, height, Color.DIMGRAY);
    	myScene.getStylesheets().add(this.getClass()
    			.getResource("practice.css").toExternalForm());
    	myPane = new GeneralPane(width, height);
    	root.getChildren().add(myPane.getMainPage());
        myStage.setScene(myScene);
        myStage.show();
        return myScene;
	}

}
