


import java.io.*;
import java.util.Date;

public class Contract implements Serializable {
    private static final long serialVersionUID = 1L; // Unique ID for serialization

    private String contractId;
    private String transactionId;
    private Date startDate;
    private Date endDate;
    private String terms;

    public Contract(String contractId, String transactionId, Date startDate, Date endDate, String terms) {
        this.contractId = contractId;
        this.transactionId = transactionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.terms = terms;
    }

    // Method to generate contract terms automatically
    public void generateContractTerms() {
        this.terms = "Contract Terms for transaction " + transactionId + " starting on " + startDate + " and ending on " + endDate;
    }

    // Getters and setters
    public String getContractId() { return contractId; }
    public void setContractId(String contractId) { this.contractId = contractId; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public String getTerms() { return terms; }
    public void setTerms(String terms) { this.terms = terms; }

    // Method to print contract details
    public void printContractDetails() {
        System.out.println("Contract ID: " + contractId);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Terms: " + terms);
    }

    // Method to save (sauvegarder) the contract to a file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Contract saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving contract to file: " + e.getMessage());
        }
    }

    // Method to load (charger) a contract from a file
    public static Contract loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Contract) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading contract from file: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // Create a new contract
        Contract contract = new Contract("C123", "T123", new Date(), new Date(), "Initial Terms");

        // Generate contract terms
        contract.generateContractTerms();

        // Save the contract to a file
        contract.saveToFile("contract.dat");

        // Load the contract from the file
        Contract loadedContract = Contract.loadFromFile("contract.dat");
        if (loadedContract != null) {
            loadedContract.printContractDetails();
        }
    }
}
