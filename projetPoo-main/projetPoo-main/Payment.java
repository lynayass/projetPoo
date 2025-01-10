


import java.io.*;
import java.util.Date;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    private String paymentId;
    private String transactionId;
    private Date paymentDate;
    private double amount;
    private String status; // Paid, Unpaid, Pending

    public Payment(String paymentId, String transactionId, Date paymentDate, double amount, String status) {
        this.paymentId = paymentId;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.status = status;
    }

    // Getters and setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Method to update payment status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Method to print payment details
    public void printPaymentDetails() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Amount: $" + amount);
        System.out.println("Status: " + status);
    }

    // Method to save (sauvegarder) the payment to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Payment saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving payment to file: " + e.getMessage());
        }
    }

    // Method to load (charger) a payment from a file
    public static Payment loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Payment) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading payment from file: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Create a new payment
        Payment payment = new Payment("P123", "T123", new Date(), 1500.00, "Pending");

        // Save the payment to a file
        payment.saveToFile("payment.dat");

        // Load the payment from the file
        Payment loadedPayment = Payment.loadFromFile("payment.dat");
        if (loadedPayment != null) {
            loadedPayment.printPaymentDetails();
        }
    }
}
