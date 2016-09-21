package animation.Screen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Screen {
	
	private Stage myStage;
	private Group root;
	private Scene myScene;
	

	public Scene init(Stage s, int width, int height) {
		myStage = s;
    	root = new Group();
    	myScene = new Scene(root, width, height, Color.BLACK);
        myStage.setScene(myScene);
        myStage.show();
        return myScene;
	}

}
