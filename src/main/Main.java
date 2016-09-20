package main;


import appearance.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main program, it is a boilerplate to create
 * a Stage.
 * 
 * @author Hannah Fuchshuber
 */

public class Main extends Application {
	
    private static final int SIZE = 400;


    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage s) {
    	MainMenu menu = new MainMenu();
    	s.setTitle("Cellular Automata");
    	Application.setUserAgentStylesheet(getClass().getResource("practice.css")
    			.toExternalForm());
        // attach game to the stage and display it
        menu.init(s, SIZE, SIZE);
	}
    
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}