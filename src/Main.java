import animation.menu.Navigation;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 550;
	private static final String TITLE = "Cellular Automata";

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	Navigation nav = new Navigation(s, WIDTH, HEIGHT);
        s.setTitle(TITLE);
        s.setResizable(false);
        nav.mainMenu();
	}
    
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
	
}