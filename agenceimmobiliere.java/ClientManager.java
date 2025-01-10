import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManager {
    private Map<String, Client> clients;

    public ClientManager() {
        clients = new HashMap<>();
    }

    // Ajouter un client
    public void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    // Modifier un client
    public void modifyClient(String id, String name, String type, String phoneNumber) {
        Client client = clients.get(id);
        if (client != null) {
            // Mise à jour des informations du client
            client = new Client(id, name, type, phoneNumber);
            clients.put(id, client);
        }
    }

    // Supprimer un client
    public void removeClient(String id) {
        clients.remove(id);
    }

    // Obtenir un client par ID
    public Client getClient(String id) {
        return clients.get(id);
    }

    // Obtenir tous les clients
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }
}
