

public class Transaction {
	public int vCredit, vDebit; // vCredit is the persion who's getting the money returned. vDebit is person returning the money.
    public double val;
    public String description;


    // every box filled in the UI is one transaction object
    // the way this transaction class works is that every box filled for one thing (ie receipt) equals one transaction

    /**
     * Constructor for transaction object
     * @param vCredit person paying in this transaction
     * @param vDebit person being paid for (owes money) in this transaction
     * @param val value of transaction
     * @param description description of transaction ie pizza
     */
    public Transaction (int vCredit, int vDebit, double val, String description){
        if (val >= 0){
            this.vCredit = vCredit;
            this.vDebit = vDebit;
            this.val = val;
            this.description = description;
        }
    }

    /**
     * Getter for the vertex number of the person lending the money
     * @return int creditor vertex number
     */
    public int getVCredit() {
        return vCredit;
    }
    
    /**
     * Getter for the vertex number of the person owing the money
     * @return int debitor vertex number
     */
    public int VDebit() {
        return vDebit;
    }

    /**
     * Getter for the value of the transaction
     * @return int value
     */
    public double getVal() {
        return val;
    }

    /**
     * Getter for transaction description
     * @return String description
     */
    public String getDescription() {
        return description;
    }
}

