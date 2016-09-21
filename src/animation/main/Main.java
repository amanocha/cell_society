package animation.main;

import animation.Screen.Screen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SIZE = 400;

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	Screen mainmenu = new Screen();
        s.setTitle("Cellular Automata");
        mainmenu.init(s, SIZE, SIZE);
	}
    
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
	
}
