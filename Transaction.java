package java;


import java.io.*;
import java.util.Date;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    private String transactionId;
    private String propertyId;
    private String clientId;
    private Date transactionDate;
    private double amount;
    private String type; // Sale or Rent
    private String status; // Pending, Completed, Cancelled

    public Transaction(String transactionId, String propertyId, String clientId, Date transactionDate, double amount, String type, String status) {
        this.transactionId = transactionId;
        this.propertyId = propertyId;
        this.clientId = clientId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.type = type;
        this.status = status;
    }

    // Getters and setters
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Method to update status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Method to print transaction details
    public void printTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Property ID: " + propertyId);
        System.out.println("Client ID: " + clientId);
        System.out.println("Date: " + transactionDate);
        System.out.println("Amount: $" + amount);
        System.out.println("Type: " + type);
        System.out.println("Status: " + status);
    }

    // Method to save (sauvegarder) the transaction to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Transaction saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving transaction to file: " + e.getMessage());
        }
    }

    // Method to load (charger) a transaction from a file
    public static Transaction loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Transaction) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading transaction from file: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Create a new transaction
        Transaction transaction = new Transaction("T123", "P456", "C789", new Date(), 150000.00, "Sale", "Pending");

        // Save the transaction to a file
        transaction.saveToFile("transaction.dat");

        // Load the transaction from the file
        Transaction loadedTransaction = Transaction.loadFromFile("transaction.dat");
        if (loadedTransaction != null) {
            loadedTransaction.printTransactionDetails();
        }
    }
}
