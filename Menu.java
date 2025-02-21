import java.awt.Color;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;

/**
 * Represents the main menu of the Vinmo App.
 * This menu provides options to navigate between the "Friends" and "Expenses" tabs or quit the application.
 */
public class Menu extends GraphicsGroup {

    private static final int MENU_WIDTH = 525;
    private static final int MENU_HEIGHT = 725;
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private static final int BORDER_WIDTH = (CANVAS_WIDTH - MENU_WIDTH) / 2;
    private static final int BORDER_HEIGHT = (CANVAS_HEIGHT - MENU_HEIGHT) / 2;

    private UserGraph userGraph;
    private Button friendsTab = new Button("Friends");
    private Button transactionsTab = new Button("Expenses");
    private Button quitButton = new Button("Quit App");
    private CanvasWindow canvas;
    private GraphicsGroup graphics;

    /**
     * Constructs a Menu object and sets up the main menu interface.
     *
     * @param canvas    The canvas where the menu is displayed.
     * @param userGraph The graph containing user data and relationships.
     */
    public Menu(CanvasWindow canvas, UserGraph userGraph) {
        this.canvas = canvas;
        this.userGraph = userGraph;

        initializeListeners();

        // Background overlay
        Rectangle overlay = new Rectangle(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        overlay.setFillColor(new Color(200, 200, 200)); // Light gray background
        add(overlay);

        // Menu box
        Rectangle menuBox = new Rectangle(BORDER_WIDTH, BORDER_HEIGHT, MENU_WIDTH, MENU_HEIGHT);
        menuBox.setFillColor(Color.WHITE);
        menuBox.setStrokeColor(Color.BLACK);
        menuBox.setStrokeWidth(3);
        add(menuBox);

        // Title text
        GraphicsText title = new GraphicsText("Vinmo");
        title.setFillColor(Color.RED);
        title.setFont(FontStyle.BOLD_ITALIC, 30);
        title.setCenter(BORDER_WIDTH + MENU_WIDTH / 2, BORDER_HEIGHT + 50);
        add(title);

        // Description text
        GraphicsText description = new GraphicsText("Welcome to Vinmo! Select a Tab:");
        description.setFont(FontStyle.PLAIN, 20);
        description.setAlignment(TextAlignment.CENTER);
        description.setCenter(BORDER_WIDTH + MENU_WIDTH / 2, BORDER_HEIGHT + 100);
        add(description);

        // Add buttons
        friendsTab.setCenter(BORDER_WIDTH + MENU_WIDTH / 2, BORDER_HEIGHT + 200);
        add(friendsTab);

        transactionsTab.setCenter(BORDER_WIDTH + MENU_WIDTH / 2, BORDER_HEIGHT + 250);
        add(transactionsTab);

        quitButton.setCenter(BORDER_WIDTH + MENU_WIDTH / 2, BORDER_HEIGHT + 350);
        add(quitButton);
    }

    /**
     * Initializes button click listeners for navigating between tabs and quitting the app.
     */
    private void initializeListeners() {
        friendsTab.onClick(() -> switchToTab(new FriendsTab(this::returnToMenu, userGraph)));
        transactionsTab.onClick(() -> switchToTab(new Expenses(this::returnToMenu, userGraph)));
        quitButton.onClick(() -> System.exit(0));
    }

    /**
     * Switches the canvas to display the specified tab.
     *
     * @param tab The tab to display on the canvas.
     */
    private void switchToTab(GraphicsGroup tab) {
        canvas.removeAll(); // Clear the canvas
        canvas.add(tab);    // Add the new tab
    }

    /**
     * Returns to the main menu by clearing the canvas and re-adding this menu.
     */
    private void returnToMenu() {
        canvas.removeAll(); // Clear the canvas
        canvas.add(this);   // Re-add the menu
    }

    /**
     * Returns the quit button for external access (e.g., testing).
     *
     * @return The quit button.
     */
    public Button getQuitButton() {
        return quitButton;
    }

    /**
     * Provides access to the underlying graphics group, allowing for future extension.
     *
     * @return The graphics group.
     */
    public GraphicsGroup getGraphics() {
        return graphics;
    }
}
