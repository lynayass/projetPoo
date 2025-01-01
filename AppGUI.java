import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AppGUI extends JFrame {
    private static final ArrayList<Agents> agents = new ArrayList<>();
    private static final ArrayList<Bien> biens = new ArrayList<>();
    private ArrayList<RendezVous> rendezVousList = new ArrayList<>();
    
    public AppGUI() {
        setTitle("Agence Immobilière");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
                
                // Ajouter un fond d'écran à la fenêtre
                String imagePath = "D:\\dev\\BienImmobilier\\BienImmobilier\\agence immo.jpg"; 
                BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath); 
                setContentPane(backgroundPanel); 
        
        // Créer un panneau pour les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Layout en colonne

        // Créer des boutons
        JButton creerButton = new JButton("Créer un Bien");
        JButton modifierButton = new JButton("Modifier un Bien");
        JButton afficherButton = new JButton("Afficher les Biens");
        JButton supprimerButton = new JButton("Supprimer un Bien");
        JButton rechercherButton = new JButton("Rechercher un Bien");
        JButton affecterButton = new JButton("Affecter un Bien"); 
        JButton prendreRendezVousButton = new JButton("Prendre Rendez-Vous");
        JButton afficherRendezVousButton = new JButton("Afficher Rendez-Vous");
        JButton quitterButton = new JButton("Quitter");
        

        // Add buttons to the frame
        panel.add(prendreRendezVousButton);
        panel.add(afficherRendezVousButton);
        // Ajouter des ActionListeners aux boutons
        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerBiens();
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierBien();
            }
        });

        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (biens.isEmpty()) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Aucun bien disponible.");
                } else {
                    StringBuilder biensList = new StringBuilder("Les biens : \n");
                    for (int i = 0; i < biens.size(); i++) {
                        biensList.append(i + 1).append(": ").append(biens.get(i)).append("\n");
                    }
                    JOptionPane.showMessageDialog(AppGUI.this, biensList.toString());
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (biens.isEmpty()) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Aucun bien disponible à supprimer.");
                    return;
                }

                StringBuilder biensList = new StringBuilder("Sélectionnez le bien à supprimer :\n");
                for (int i = 0; i < biens.size(); i++) {
                    biensList.append(i + 1).append(": ").append(biens.get(i)).append("\n");
                }

                String input = JOptionPane.showInputDialog(biensList.toString());
                int index;
                try {
                    index = Integer.parseInt(input) - 1; // Convertir l'entrée en index
                    if (index < 0 || index >= biens.size()) {
                        JOptionPane.showMessageDialog(AppGUI.this, "Index invalide !");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Veuillez entrer un nombre valide.");
                    return;
                }

                biens.remove(index);
                JOptionPane.showMessageDialog(AppGUI.this, "Bien supprimé.");
            }
        });

        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (biens.isEmpty()) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Aucun bien disponible.");
                    return;
                }

                String localisation = JOptionPane.showInputDialog("Entrez la localisation :");
                Double superficie = null;
                try {
                    String superficieInput = JOptionPane.showInputDialog("Entrez la superficie :");
                    if (!superficieInput.isEmpty()) {
                        superficie = Double.parseDouble(superficieInput);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Veuillez entrer un nombre valide.");
                    return;
                }

                Double prix = null;
                try {
                    String prixInput = JOptionPane.showInputDialog("Entrez le prix :");
                    if (!prixInput.isEmpty()) {
                        prix = Double.parseDouble(prixInput);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Veuillez entrer un nombre valide.");
                    return;
                }

                ArrayList<Bien> biensTrouves = new ArrayList<>();
                for (Bien bien : biens) {
                    boolean matches = true; // Flag to check if the bien matches the search criteria

                    if (!localisation.equalsIgnoreCase("") && !bien.getLocalisation().equalsIgnoreCase(localisation)) {
                        matches = false; // Does not match localisation
                    }
                    if (prix != null && !bien.getPrix().equals(prix)) {
                        matches = false; // Does not match price
                    }
                    if (superficie != null && !bien.getSuperficie().equals(superficie)) {
                        matches = false; // Does not match superficie
                    }

                    if (matches) {
                        biensTrouves.add(bien); // Add to found list if it matches all criteria
                    }
                }

                if (biensTrouves.isEmpty()) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Aucun bien trouvé.");
                } else {
                    StringBuilder biensList = new StringBuilder("Biens trouvés : \n");
                    for (int i = 0; i < biensTrouves.size(); i++) {
                        biensList.append(i + 1).append(": ").append(biensTrouves.get(i)).append("\n");
                    }
                    JOptionPane.showMessageDialog(AppGUI.this, biensList.toString());
                }
            }
        });

        affecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (biens.isEmpty()) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Aucun bien disponible à affecter.");
                    return;
                }

                String nom = JOptionPane.showInputDialog("Entrez le nom de l'agent :");
                String prenom = JOptionPane.showInputDialog("Entrez le prénom de l'agent :");
                Agents agent = new Agents(nom, prenom);
                agents.add(agent);
                JOptionPane.showMessageDialog(AppGUI.this, "Agent ajouté.");

                StringBuilder biensList = new StringBuilder("Entrez la position du bien à affecter :\n");
                for (int i = 0; i < biens.size(); i++) {
                    biensList.append(i + 1).append(": ").append(biens.get(i)).append("\n");
                }

                int position;
                try {
                    position = Integer.parseInt(JOptionPane.showInputDialog(biensList.toString())) - 1;
                    if (position < 0 || position >= biens.size()) {
                        JOptionPane.showMessageDialog(AppGUI.this, "Position invalide !");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Veuillez entrer un nombre valide.");
                    return;
                }

                Bien bien = biens.get(position);
                agent.ajouterBien(bien);
                JOptionPane.showMessageDialog(AppGUI.this, "Bien affecté.\nLes biens de l'agent : " + agent.getNom() + " " + agent.getPrenom());
            }
        });

        prendreRendezVousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agentNom = JOptionPane.showInputDialog("Entrez le nom de l'agent :");
                String agentPrenom = JOptionPane.showInputDialog("Entrez le prénom de l'agent :");
                String date = JOptionPane.showInputDialog("Entrez la date du rendez-vous (AAAA-MM-JJ) :");
                String heure = JOptionPane.showInputDialog("Entrez l'heure du rendez-vous (HH:MM) :");

                LocalDateTime dateHeure = LocalDateTime.parse(date + "T" + heure + ":00");
                RendezVous rendezVous = new RendezVous(agentNom, agentPrenom, dateHeure);
                rendezVousList.add(rendezVous);
                JOptionPane.showMessageDialog(AppGUI.this, "Rendez-vous pris avec " + agentNom + " " + agentPrenom + " le " + date + " à " + heure);
            }
        });
        afficherRendezVousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rendezVousList.isEmpty()) {
                    JOptionPane.showMessageDialog(AppGUI.this, "Aucun rendez-vous disponible.");
                } else {
                    StringBuilder rendezVousListStr = new StringBuilder("Rendez-vous : \n");
                    for (int i = 0; i < rendezVousList.size(); i++) {
                        rendezVousListStr.append(i + 1).append(": ").append(rendezVousList.get(i)).append("\n");
                    }
                    JOptionPane.showMessageDialog(AppGUI.this, rendezVousListStr.toString());
                }
            }
        });
        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Ajoutez des ActionListeners pour les autres boutons ici...

        // Ajouter les boutons au panneau
        panel.add(creerButton);
        panel.add(modifierButton);
        panel.add(afficherButton);
        panel.add(supprimerButton);
        panel.add(rechercherButton);
        panel.add(affecterButton);
        panel.add(prendreRendezVousButton);
        panel.add(afficherRendezVousButton);
        panel.add(quitterButton);

        // Ajouter le panneau à la fenêtre
        add(panel);
    }

    private void creerBiens() {
        // Implémentez la logique pour créer un bien ici
        // Vous pouvez utiliser JOptionPane pour obtenir des entrées de l'utilisateur
        String localisation = JOptionPane.showInputDialog("Entrez la localisation :");
        String description = JOptionPane.showInputDialog("Entrez la description :");
        Double superficie = Double.parseDouble(JOptionPane.showInputDialog("Entrez la superficie :"));
        Double prix = Double.parseDouble(JOptionPane.showInputDialog("Entrez le prix :"));

        // Choisir le type de bien
        String[] options = {"Villa", "Appartement", "Local"};
        int choixType = JOptionPane.showOptionDialog(null, "Choisissez le type de bien :", "Type de Bien",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        TypeDeBien typeDeBien = TypeDeBien.values()[choixType];

        Bien bien = new Bien(superficie, prix, localisation, description, typeDeBien);
        biens.add(bien);
        JOptionPane.showMessageDialog(this, "Bien créé : " + bien);
    }

// Ajoutez la méthode modifierBien ici
    private void modifierBien() {
        if (biens.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun bien disponible à modifier.");
            return;
        }
    
        // Afficher les biens disponibles
        StringBuilder biensList = new StringBuilder("Sélectionnez le bien à modifier :\n");
        for (int i = 0; i < biens.size(); i++) {
            biensList.append(i + 1).append(": ").append(biens.get(i)).append("\n");
        }
    
        String input = JOptionPane.showInputDialog(biensList.toString());
        int index;
        try {
            index = Integer.parseInt(input) - 1; // Convertir l'entrée en index
            if (index < 0 || index >= biens.size()) {
                JOptionPane.showMessageDialog(this, "Index invalide !");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.");
            return;
        }
    
        Bien bien = biens.get(index);
        String[] options = {"Localisation", "Superficie", "Prix"};
        int choix = JOptionPane.showOptionDialog(this, "Que souhaitez-vous modifier ?", "Modifier Bien",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    
        switch (choix) {
            case 0: // Localisation
                String nouvelleLocalisation = JOptionPane.showInputDialog("Entrez la nouvelle localisation :", bien.getLocalisation());
                bien.setLocalisation(nouvelleLocalisation);
                break;
            case 1: // Superficie
                String superficieInput = JOptionPane.showInputDialog("Entrez la nouvelle superficie :", bien.getSuperficie());
                bien.setSuperficie(Double.parseDouble(superficieInput));
                break;
            case 2: // Prix
                String prixInput = JOptionPane.showInputDialog("Entrez le nouveau prix :", bien.getPrix());
                bien.setPrix(Double.parseDouble(prixInput));
                break;
            default:
                JOptionPane.showMessageDialog(this, "Aucune modification effectuée.");
                return;
        }
    
        JOptionPane.showMessageDialog(this, "Bien modifié : " + bien);
    }

    //ajoutez la méthode supprimerBien ici
    private void supprimerBien() {
        if (biens.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun bien disponible à supprimer.");
            return;
        }

        StringBuilder biensList = new StringBuilder("Sélectionnez le bien à supprimer :\n");
        for (int i = 0; i < biens.size(); i++) {
            biensList.append(i + 1).append(": ").append(biens.get(i)).append("\n");
        }

        String input = JOptionPane.showInputDialog(biensList.toString());
        int index;
        try {
            index = Integer.parseInt(input) - 1; // Convertir l'entrée en index
            if (index < 0 || index >= biens.size()) {
                JOptionPane.showMessageDialog(this, "Index invalide !");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.");
            return;
        }

        biens.remove(index);
        JOptionPane.showMessageDialog(this, "Bien supprimé.");
    }


    // Ajoutez la méthode rechercherBien ici

    private void rechercherBien() {
        if (biens.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun bien disponible.");
            return;
        }

        String localisation = JOptionPane.showInputDialog("Entrez la localisation :");
        Double superficie = null;
        try {
            String superficieInput = JOptionPane.showInputDialog("Entrez la superficie :");
            if (!superficieInput.isEmpty()) {
                superficie = Double.parseDouble(superficieInput);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.");
            return;
        }

        Double prix = null;
        try {
            String prixInput = JOptionPane.showInputDialog("Entrez le prix :");
            if (!prixInput.isEmpty()) {
                prix = Double.parseDouble(prixInput);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.");
            return;
        }

        ArrayList<Bien> biensTrouves = new ArrayList<>();
        for (Bien bien : biens) {
            boolean matches = true; // Flag to check if the bien matches the search criteria

            if (!localisation.equalsIgnoreCase("") && !bien.getLocalisation().equalsIgnoreCase(localisation)) {
                matches = false; // Does not match localisation
            }
            if (prix != null && !bien.getPrix().equals(prix)) {
                matches = false; // Does not match price
            }
            if (superficie != null && !bien.getSuperficie().equals(superficie)) {
                matches = false; // Does not match superficie
            }

            if (matches) {
                biensTrouves.add(bien); // Add to found list if it matches all criteria
            }
        }

        if (biensTrouves.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun bien trouvé.");
        } else {
            StringBuilder biensList = new StringBuilder("Biens trouvés : \n");
            for (int i = 0; i < biensTrouves.size(); i++) {
                biensList.append(i + 1).append(": ").append(biensTrouves.get(i)).append("\n");
            }
            JOptionPane.showMessageDialog(this, biensList.toString());
        }
    }

    // Ajoutez la méthode affecterBien ici

    private void affecterBien() {
        if (biens.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun bien disponible à affecter.");
            return;
        }

        String nom = JOptionPane.showInputDialog("Entrez le nom de l'agent :");
        String prenom = JOptionPane.showInputDialog("Entrez le prénom de l'agent :");
        Agents agent = new Agents(nom, prenom);
        agents.add(agent);
        JOptionPane.showMessageDialog(this, "Agent ajouté.");

        StringBuilder biensList = new StringBuilder("Entrez la position du bien à affecter :\n");
        for (int i = 0; i < biens.size(); i++) {
            biensList.append(i + 1).append(": ").append(biens.get(i)).append("\n");
        }

        int position;
        try {
            position = Integer.parseInt(JOptionPane.showInputDialog(biensList.toString())) - 1;
            if (position < 0 || position >= biens.size()) {
                JOptionPane.showMessageDialog(this, "Position invalide !");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre valide.");
            return;
        }

        Bien bien = biens.get(position);
        agent.ajouterBien(bien);
    }

    //ajouter la methode quitter ici
    private void quitter() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppGUI app = new AppGUI();
            app.setVisible(true);
        });
    }
    class BackgroundPanel extends JPanel {
        private BufferedImage image; 
        
        public BackgroundPanel(String imagePath) {
            try {
                image = ImageIO.read(new File(imagePath)); 
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}