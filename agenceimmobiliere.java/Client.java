import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    private String id;
    private String name;
    private String type; // "Acheteur", "Locataire", "Vendeur", "Bailleur"
    private String phoneNumber; // Nouveau champ
    private List<String> interactions;
    private Map<String, String> preferences;

    // Constructeur avec phoneNumber
    public Client(String id, String name, String type, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.interactions = new ArrayList<>();
        this.preferences = new HashMap<>();
    }

    // Ajouter une interaction
    public void addInteraction(String interaction) {
        interactions.add(interaction);
    }

    // Ajouter une préférence
    public void setPreference(String key, String value) {
        preferences.put(key, value);
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPhoneNumber() { // Getter pour phoneNumber
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { // Setter pour phoneNumber
        this.phoneNumber = phoneNumber;
    }

    public List<String> getInteractions() {
        return interactions;
    }

    public Map<String, String> getPreferences() {
        return preferences;
    }
}
