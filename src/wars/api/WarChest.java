package wars.api;

/**
 * Represents the Admiral's war chest holding funds for commissioning ships,
 * decommissioning them, and handling encounter prize money adjustments.
 */
/**
 * Enumeration class UnitState - write a description of the enum class here
 * 
 * @author Cheuk Yin Bosca Pang
 * @version 03/4/2025
 */
public class WarChest {
    private double balance;

    /**
     * @param initialBalance the starting amount of money
     */
    public WarChest(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * @return the war chest balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param amount the amount to add
     */
    public void add(double amount) {
        balance += amount;
    }

    /**
     * Deducts the specified amount from the war chest.
     * 
     * Note: This method allows the balance to go negative
     * 
     * @param amount the amount to deduct
     */
    public void deduct(double amount) {
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
