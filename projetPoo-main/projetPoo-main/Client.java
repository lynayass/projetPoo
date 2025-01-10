import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    private String id;
    private String name;
    private String type; // "Acheteur", "Locataire", "Vendeur", "Bailleur"
    private List<String> interactions;
    private Map<String, String> preferences;

    public Client(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.interactions = new ArrayList<>();
        this.preferences = new HashMap<>();
    }

    public void addInteraction(String interaction) {
        interactions.add(interaction);
    }

    public void setPreference(String key, String value) {
        preferences.put(key, value);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getInteractions() {
        return interactions;
    }

    public Map<String, String> getPreferences() {
        return preferences;
    }

    public String getPhoneNumber() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPhoneNumber'");
    }
}
