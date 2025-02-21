import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;
import java.util.*;

/**
 * The Expenses class manages the "Expenses" tab in a user interface.
 * It allows users to add financial transactions, view expense history, 
 * and calculate cash flow minimization based on a user graph.
 */
public class Expenses extends ParentTab {
    private TextField nameCreditBox, descBox, nameDebitBox, valueBox;
    private GraphicsText minimizer;
    private UserGraph userGraph;
    private Button addTransactionButton;
    private List<Transaction> transactionList;

    /**
     * Constructs an Expenses tab.
     *
     * @param onBackButtonClick Handler for the back button click event.
     * @param userGraph The graph representing the relationships between users and their transactions.
     */
    public Expenses(BackButtonHandler onBackButtonClick, UserGraph userGraph) {
        super("Expenses", onBackButtonClick);
        this.userGraph = userGraph;
        setupUI();
    }

    /**
     * Sets up the user interface components for the Expenses tab.
     */
    private void setupUI() {
        nameCreditBox = new TextField();
        nameCreditBox.setCenter(120, 180);
        nameCreditBox.setText("Person paying");
        add(nameCreditBox);

        nameDebitBox = new TextField();
        nameDebitBox.setCenter(300, 180);
        nameDebitBox.setText("Person owing");
        add(nameDebitBox);
        
        valueBox = new TextField();
        valueBox.setCenter(450, 180);
        valueBox.setText("Value");
        add(valueBox);
        
        descBox = new TextField();
        descBox.setCenter(120, 280);
        descBox.setText("Description");
        add(descBox);

        addTransactionButton = new Button("Add Transaction");
        addTransactionButton.setCenter(300, 380);
        addTransactionButton.onClick(this::handleAddTransaction);
        add(addTransactionButton);

        minimizer = new GraphicsText("");
        minimizer.setCenter(150, 480);
        minimizer.setFont(FontStyle.PLAIN, 12);
        add(minimizer);

        transactionList = new ArrayList<>();
    }

    /**
     * Handles the "Add Transaction" button click event.
     * Adds a new transaction if the input is valid, updates the user graph,
     * and displays the updated cash flow minimization result.
     */
    private void handleAddTransaction() {
        String nameCredit = nameCreditBox.getText();
        String nameDebit = nameDebitBox.getText();

        if (userGraph.StringIntegerMap.containsKey(nameCredit) && userGraph.StringIntegerMap.containsKey(nameDebit)) {
            int vCredit = userGraph.StringIntegerMap.get(nameCredit);
            int vDebit = userGraph.StringIntegerMap.get(nameDebit);

            System.out.println(vCredit);
            System.out.println(vDebit);
            System.out.println(userGraph.IntegerStringMap.get(vCredit));
            System.out.println(userGraph.IntegerStringMap.get(vDebit));

            String valueText = valueBox.getText();
            String description = descBox.getText();

            if (valueText.isEmpty()) {
                return; // Invalid inputs
            }

            double value = Double.parseDouble(valueText);
            Transaction transac = new Transaction(vCredit, vDebit, value, description);
            transactionList.add(transac);
            userGraph.updateEdge(transac);
            minimizer.setText(userGraph.cashFlowMin());

            UserGraph.matrixPrint(userGraph.matrix);
        }
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }
}