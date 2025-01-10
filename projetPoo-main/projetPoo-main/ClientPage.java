import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientPage extends JFrame {

    private ClientManager clientManager;

    public ClientPage(ClientManager clientManager) {
        this.clientManager = clientManager;
        setTitle("Client Management");
        setSize(400, 300); // Taille de la fenêtre
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer les boutons
        JButton btnModifier = new JButton("Modifier le Client");
        JButton btnSupprimer = new JButton("Supprimer un Client");
        JButton btnAfficher = new JButton("Afficher un Client");
        JButton btnCreer = new JButton("Créer un Client");
        JButton btnAfficherTous = new JButton("Afficher Tous Les Clients");

        // Définir la même taille pour tous les boutons
        Dimension buttonSize = new Dimension(200, 30);
        btnModifier.setPreferredSize(buttonSize);
        btnSupprimer.setPreferredSize(buttonSize);
        btnAfficher.setPreferredSize(buttonSize);
        btnCreer.setPreferredSize(buttonSize);
        btnAfficherTous.setPreferredSize(buttonSize);

        // Utiliser BoxLayout pour placer les boutons au milieu
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        btnModifier.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSupprimer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAfficher.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCreer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAfficherTous.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(btnModifier);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement vertical
        panel.add(btnSupprimer);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnAfficher);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnCreer);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnAfficherTous);
        panel.add(Box.createVerticalGlue());

        // Ajouter le panneau à la fenêtre
        add(panel);

        // ActionListener pour "Modifier le Client"
        btnModifier.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Entrez l'ID du client à modifier:");
            Client client = clientManager.getClientById(id);
            if (client != null) {
                String newName = JOptionPane.showInputDialog("Entrez le nouveau nom du client:");
                String newType = JOptionPane.showInputDialog("Entrez le nouveau type du client:");
                String newPhoneNumber = JOptionPane.showInputDialog("Entrez le nouveau numéro de téléphone:");
                clientManager.modifyClient(id, newName, newType);
                JOptionPane.showMessageDialog(this, "Client modifié avec succès.");
            } else {
                JOptionPane.showMessageDialog(this, "Ce client n'existe pas.");
            }
        });

        // ActionListener pour "Supprimer un Client"
        btnSupprimer.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Entrez l'ID du client à supprimer:");
            Client client = clientManager.getClientById(id);
            if (client != null) {
                clientManager.addClient(client);
                JOptionPane.showMessageDialog(this, "Client supprimé avec succès.");
            } else {
                JOptionPane.showMessageDialog(this, "Ce client n'existe pas.");
            }
        });

        // ActionListener pour "Afficher un Client"
        btnAfficher.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Entrez l'ID du client à afficher:");
            Client client = clientManager.getClientById(id);
            if (client != null) {
                String message = "Nom: " + client.getName() + "\n" +
                                 "Type: " + client.getType() + "\n" +
                                 "Téléphone: " + client.getPhoneNumber() + "\n" +
                                 "Préférences: " + client.getPreferences() + "\n" +
                                 "Historique: " + client.getInteractions();
                JOptionPane.showMessageDialog(this, message);
            } else {
                JOptionPane.showMessageDialog(this, "Ce client n'existe pas.");
            }
        });

        // ActionListener pour "Créer un Client"
        btnCreer.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Entrez l'ID du nouveau client:");
            String name = JOptionPane.showInputDialog("Entrez le nom du client:");
            String type = JOptionPane.showInputDialog("Entrez le type du client (Acheteur, Locataire, Vendeur, Bailleur):");
            String phoneNumber = JOptionPane.showInputDialog("Entrez le numéro de téléphone du client:");
            Client newClient = new Client(id, name, type);
            clientManager.addClient(newClient);   

            int response = JOptionPane.showConfirmDialog(this, "Voulez-vous ajouter des préférences pour ce client?", "Ajouter des Préférences", JOptionPane.YES_NO_OPTION);
            while (response == JOptionPane.YES_OPTION) {
                String key = JOptionPane.showInputDialog("Entrez le type de préférence (ex: Couleur, Taille, etc.):");
                String value = JOptionPane.showInputDialog("Entrez la préférence:");
                newClient.setPreference(key, value);
                response = JOptionPane.showConfirmDialog(this, "Voulez-vous ajouter une autre préférence?", "Ajouter des Préférences", JOptionPane.YES_NO_OPTION);
            }

            JOptionPane.showMessageDialog(this, "Client créé avec succès.");
        });

        // ActionListener pour "Afficher Tous Les Clients"
        btnAfficherTous.addActionListener(e -> {
            JFrame tableFrame = new JFrame("Liste des Clients");
            tableFrame.setSize(600, 400);
            tableFrame.setLocationRelativeTo(null);

            String[] columns = {"ID", "Nom", "Type", "Téléphone"};
            List<Client> clients = clientManager.getAllClients();
            String[][] data = new String[clients.size()][4];
            for (int i = 0; i < clients.size(); i++) {
                Client client = clients.get(i);
                data[i][0] = client.getId();
                data[i][1] = client.getName();
                data[i][2] = client.getType();
                data[i][3] = client.getPhoneNumber(); // Afficher le numéro de téléphone
            }

            JTable table = new JTable(new DefaultTableModel(data, columns));
            JScrollPane scrollPane = new JScrollPane(table);
            tableFrame.add(scrollPane, BorderLayout.CENTER);

            tableFrame.setVisible(true);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        ClientManager clientManager = new ClientManager("Title1", "Title2", "Title3", "Title4");
        ClientPage clientPage = new ClientPage(clientManager);
        clientPage.setVisible(true);
    }
}
