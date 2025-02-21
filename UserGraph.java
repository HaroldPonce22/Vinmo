import java.util.*;

/**
 * A class representing a graph with an adjacency matrix implementation to track financial
 * transactions between users.
 */
public class UserGraph {

    // need to set the starting limit at five and expand if necessary to 10. Cap it there tho
    // need to find a way to discern user amount / V is a placeholder for that in getNetAmt
    // and prob other methods but it's not right

    private static final int LIMIT = 10;

    private int V;
    double[][] matrix;

    private List<String> userList = new ArrayList<String>();

    public Map<Integer, String> IntegerStringMap = new TreeMap<>();
    public Map<String, Integer> StringIntegerMap = new TreeMap<>();

    /**
     * Constructs a new UserGraph with a fixed matrix size. Initializes the graph with a default user
     * "Me".
     */
    public UserGraph() {

        // initialize the matrix
        double[][] matrix = new double[LIMIT][LIMIT];
        for (int i = 0; i < LIMIT; i++) {
            for (int j = 0; j < LIMIT; j++) {
                matrix[i][j] = 0;
            }
        }

        this.matrix = matrix;
        addUser("Me");
        matrixPrint(matrix);
    }

    /**
     * Returns the maximum size limit of the adjacency matrix.
     *
     * @return the size limit.
     */
    public int getLimit() {
        return LIMIT;
    }

    /**
     * Returns the number of users (vertices) in the graph.
     *
     * @return the number of vertices.
     */
    public int V() {
        return V;
    }

    /**
     * Validates that a vertex index is within valid bounds.
     *
     * @param v the vertex index to validate.
     * @throws IllegalArgumentException if the vertex index is out of bounds.
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Returns the list of user names in the graph.
     *
     * @return a list of user names.
     */
    public List<String> getUserList() {
        List<String> resList = new ArrayList<>();

        Iterator<String> iter = userList.iterator();
        while (iter.hasNext()) {
            resList.add(iter.next());
        }
        return resList;
    }


    /**
     * Adds a user to the graph. Each user is assigned a vertex index, and mappings between user names
     * and vertex indices are updated.
     *
     * @param name the name of the user to add.
     */
    public void addUser(String name) {
        userList.add(name);
        IntegerStringMap.put(V, name);
        StringIntegerMap.put(name, V);
        V += 1;
    }

    /**
     * Updates the edge values (financial transaction) between two users.
     *
     * @param transac the transaction object containing credit, debit, and value details.
     */
    public void updateEdge(Transaction transac) {
        validateVertex(transac.vCredit);
        validateVertex(transac.vDebit);

        double val = transac.val;

        // scenario: vDebit already owed some money beforehand or nobody owed anybody

        if (matrix[transac.vDebit][transac.vCredit] >= 0 && matrix[transac.vCredit][transac.vDebit] == 0) {
            matrix[transac.vDebit][transac.vCredit] += transac.val;

        }

        // scenario: vDebit used to lend money to vCredit but now owes money i.e. directed graph switches
        // directions
        else if (matrix[transac.vDebit][transac.vCredit] == 0 && matrix[transac.vCredit][transac.vDebit] > 0) {

            // scenario: new value bigger or equal to previous balance so it erases previous balance
            if (val >= matrix[transac.vCredit][transac.vDebit]) {
                val -= matrix[transac.vCredit][transac.vDebit];
                matrix[transac.vCredit][transac.vDebit] = 0;
                matrix[transac.vDebit][transac.vCredit] = val;
            }

            // scenario: new value smaller than previous balance so it reduces previous balance
            else if (val < matrix[transac.vCredit][transac.vDebit]) {
                matrix[transac.vCredit][transac.vDebit] -= val;
            }
        }
    }

    /**
     * Calculates the net balance for a specific user (vertex). The net balance is the total credit
     * minus the total debit for that user.
     *
     * @param vNum the vertex number representing the user.
     * @return the net balance of the user.
     */
    public double getNetAmt(int vNum) {
        double credit = 0, debit = 0;
        for (int i = 0; i < V; i++) {
            credit += matrix[i][vNum];
        }
        for (int j = 0; j < V; j++) {
            debit += matrix[vNum][j];
        }

        double netAmt = credit - debit;
        return netAmt;
    }

    /**
     * Minimizes cash flow between users by calculating the optimal set of transactions to settle all
     * balances. Each transaction is represented as a string and added to the result list.
     *
     * @return a string representing the different minimized cash flow repayments.
     */
    public String cashFlowMin() {
        ArrayList<Double> balancesList = new ArrayList<Double>(V);
        String resString = new String();

        // find net balances for every vertex
        for (int i = 0; i < V; i++) {
            double amt = getNetAmt(i);
            balancesList.add(amt);
        }

        while (true) {
            double max = getMax(balancesList);
            double min = getMin(balancesList);

            if (max == 0 && min == 0) {
                break;
            }

            int vMax = balancesList.indexOf(max);
            int vMin = balancesList.indexOf(min);


            // find the smaller absolute value
            double x = Math.min(Math.abs(max), Math.abs(min));

            balancesList.set(vMin, min += x);
            balancesList.set(vMax, max -= x);

            if (vMin == 0){
                resString+= "I should pay " + IntegerStringMap.get(vMax) + " $" + x + "\n";
            }
            
            else {resString+= IntegerStringMap.get(vMin) + " should pay " + IntegerStringMap.get(vMax) + " $" + x + "\n";}
        }

        return resString;
    }

    /**
     * Returns the maximum balance from a list of user balances.
     *
     * @param balancesList the list of user balances.
     * @return the maximum balance.
     */
    private double getMax(ArrayList<Double> balancesList) {
        double max = 0;
        for (int i = 0; i < balancesList.size(); i++) {
            if (balancesList.get(i) > max) {
                max = balancesList.get(i);
            }
        }
        return max;
    }

    /**
     * Returns the minimum balance from a list of user balances.
     *
     * @param balancesList the list of user balances.
     * @return the minimum balance.
     */
    private double getMin(ArrayList<Double> balancesList) {
        double min = 0;
        for (int i = 0; i < balancesList.size(); i++) {
            if (balancesList.get(i) < min) {
                min = balancesList.get(i);
            }
        }
        return min;
    }

    /**
     * Prints the adjacency matrix for debugging purposes.
     *
     * @param matrix the adjacency matrix to print.
     */
    public static void matrixPrint(double matrix[][]) {
        StringBuilder sj = new StringBuilder();
        for (double[] row : matrix) {
            for (double col : row) {
                sj.append(col);
                sj.append(", ");
            }
            sj.append("\n");
        }
        System.out.println(sj.toString());
    }
}
