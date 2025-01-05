import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialisation du gestionnaire de clients
        ClientManager clientManager = new ClientManager();

        // Création et configuration de clients
        Client client1 = new Client("1", "Alice", "Acheteur");
        client1.addInteraction("Rencontre initiale le 2023-01-01");
        client1.addInteraction("Visite d'un bien le 2023-01-10");
        client1.setPreference("Location", "Paris");
        clientManager.addClient(client1);

        Client client2 = new Client("2", "Bob", "Locataire");
        client2.addInteraction("Appel téléphonique le 2023-01-15");
        client2.addInteraction("Envoi de documents le 2023-01-20");
        client2.setPreference("Budget", "1000€ par mois");
        clientManager.addClient(client2);

        // Affichage des informations de tous les clients (sans interactions)
        System.out.println("Liste des clients :");
        List<Client> allClients = clientManager.getAllClients();
        for (Client client : allClients) {
            System.out.println("Client ID: " + client.getId() + ", Nom: " + client.getName() + ", Type: " + client.getType());
            System.out.println("Préférences : " + client.getPreferences());
            System.out.println();
        }

        // Menu interactif pour consulter l'historique des interactions
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Entrez l'ID d'un client pour voir son historique (ou 'exit' pour quitter) :");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Programme terminé.");
                break;
            }

            Client client = clientManager.getClient(input);
            if (client != null) {
                System.out.println("Historique des interactions pour le client " + client.getName() + " :");
                List<String> interactions = client.getInteractions();
                if (interactions.isEmpty()) {
                    System.out.println("Aucune interaction enregistrée.");
                } else {
                    for (String interaction : interactions) {
                        System.out.println("- " + interaction);
                    }
                }
            } else {
                System.out.println("Aucun client trouvé avec l'ID : " + input);
            }
        }

        scanner.close();
    }
}
