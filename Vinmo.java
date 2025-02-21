import edu.macalester.graphics.*;
import java.awt.Color;

/**
 * The main application class for the Vinmo App.
 * 
 * This class initializes the graphical user interface (GUI), including the canvas and menu, and
 * integrates it with the `UserGraph` to simulate the Vinmo application.
 */
public class Vinmo {

    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private static CanvasWindow canvas;
    private static Menu menu;
    public static UserGraph userGraph;
    
        public Vinmo() {
            // Create the canvas and set the background
            userGraph = new UserGraph();
            canvas = new CanvasWindow("Vinmo", CANVAS_WIDTH, CANVAS_HEIGHT);
            canvas.setBackground(Color.LIGHT_GRAY);
            menu = new Menu(canvas, userGraph);
            canvas.add(menu); 
        }
    
        public static void main(String[] args) {
            new Vinmo();
    }
}
