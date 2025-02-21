import java.awt.Color;
import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;

/**
 * Represents an abstract parent class for tabs in the Vinmo application.
 * Provides a common structure for creating tabs with a title, a back button, 
 * and a standardized layout.
 */
public abstract class ParentTab extends GraphicsGroup {

    private static final int MENU_WIDTH = 525;
    private static final int MENU_HEIGHT = 725;
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private static final int BORDER_WIDTH = (CANVAS_WIDTH - MENU_WIDTH) / 2;
    private static final int BORDER_HEIGHT = (CANVAS_HEIGHT - MENU_HEIGHT) / 2;

    protected Button backButton;
    public CanvasWindow canvas; // Keep a reference to the CanvasWindow

    /**
     * Constructs a ParentTab object with a title and a back button.
     *
     * @param titleText         The title text to display at the top of the tab.
     * @param onBackButtonClick A functional interface to handle back button clicks.
     */
    public ParentTab(String titleText, BackButtonHandler onBackButtonClick) {

        // Background for the tab
        Rectangle background = new Rectangle(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        background.setFillColor(Color.DARK_GRAY);
        add(background);

        // Content box within the tab
        Rectangle contentBox = new Rectangle(BORDER_WIDTH, BORDER_HEIGHT, MENU_WIDTH, MENU_HEIGHT);
        contentBox.setFillColor(Color.WHITE);
        contentBox.setStrokeColor(Color.BLACK);
        contentBox.setStrokeWidth(3);
        add(contentBox);

        // Back button
        backButton = new Button("back");
        backButton.setCenter(BORDER_WIDTH + MENU_WIDTH / 2, BORDER_HEIGHT + 600);
        backButton.onClick(onBackButtonClick::onBackButtonClick);
        add(backButton);

        // Title text
        GraphicsText title = new GraphicsText(titleText);
        title.setFont(FontStyle.BOLD, 20);
        title.setCenter(CANVAS_WIDTH / 2, 100);
        add(title);
    }

    /**
     * Provides access to the back button for external use.
     *
     * @return The back button instance.
     */
    public Button getBackButton() {
        return backButton;
    }
}
