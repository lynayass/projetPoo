import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManager {
    private Map<String, Client> clients;

    public ClientManager() {
        clients = new HashMap<>();
    }

    public void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    public void modifyClient(String id, String name, String type) {
        Client client = clients.get(id);
        if (client != null) {
            client = new Client(id, name, type);
            clients.put(id, client);
        }
    }

    public void removeClient(String id) {
        clients.remove(id);
    }

    public Client getClient(String id) {
        return clients.get(id);
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }
}
