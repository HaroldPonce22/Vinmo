import java.util.List;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;

/**
 * Represents the "Friends" tab in the user interface. This tab allows users to add new members to a
 * user group and displays the current user list.
 */
public class FriendsTab extends ParentTab {
    private TextField newUserField;
    public List<String> users;
    public UserGraph userGraph;
    private Button addUserButton;
    private GraphicsText text;

    /**
     * Creates a new FriendsTab with functionality to add users and display the user list.
     *
     * @param onBackButtonClick Callback handler for when the back button is clicked.
     * @param userGraph         The user graph managing the relationships between users.
     */
    public FriendsTab(BackButtonHandler onBackButtonClick, UserGraph userGraph) {
        super("Friends", onBackButtonClick);
        this.userGraph = userGraph;

        // Initialize and set up the input field for adding users
        newUserField = new TextField();
        newUserField.setCenter(130, 180);
        newUserField.setText("Add user");
        add(newUserField);

        // Initialize and set up the button for adding users
        addUserButton = new Button("Add to group");
        addUserButton.setCenter(450, 180);
        addUserButton.onClick(this::handleAddUser);
        add(addUserButton);

        // Initialize and set up the text field for displaying the user list
        text = new GraphicsText();
        text.setCenter(80, 300);
        text.setText(userGraph.getUserList().toString());
        add(text);
    }

    /**
     * Handles the action of adding a new user when the "Add to group" button is clicked. This method
     * validates the input, adds the user to the `UserGraph`, and updates the displayed user list.
     *
     * @throws IllegalArgumentException If the input is invalid (e.g., empty or default text).
     */
    private void handleAddUser() {
        // Get input from the text field
        String userName = newUserField.getText();

        if (userName.isEmpty() || userName.equals("Add user")) {
            throw new IllegalArgumentException("Invalid User Name");
        } else {
            // Add the user to the graph and update the display
            userGraph.addUser(userName);
            text.setText(userGraph.getUserList().toString());
        }
    }
}
