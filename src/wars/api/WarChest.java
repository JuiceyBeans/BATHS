package wars.api;

/**
 * Represents the Admiral's war chest holding funds for commissioning ships,
 * decommissioning them, and handling encounter prize money adjustments.
 */
public class WarChest {
    private int balance;

    /**
     * @param initialBalance the starting amount of money
     */
    public WarChest(int initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * @return the war chest balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param amount the amount to add
     */
    public void add(int amount) {
        balance += amount;
    }

    /**
     * Deducts the specified amount from the war chest.
     * 
     * Note: This method allows the balance to go negative
     * 
     * @param amount the amount to deduct
     */
    public void deduct(int amount) {
        balance -= amount;
    }

    /**
     * Provides a textual representation of the war chest.
     * @return a string displaying the current balance
     */
    @Override
    public String toString() {
        return "WarChest balance: " + balance;
    }
}
