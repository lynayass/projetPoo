import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextPage extends JFrame {
    public NextPage() {
        setTitle("Next Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        // URL de l'image
        String imageUrl = "https://cdn.discordapp.com/attachments/1297979194227036333/1327355811843936256/achat-immobilier-divorce-notaires-office.jpeg?ex=6782c3ee&is=6781726e&hm=59f996d06b273727e55a9988c707bc0a61b0ad0070d81e2f70bf1ac1bb09afc3&";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imageUrl);
        backgroundPanel.setLayout(new GridBagLayout());

        // Ajoutez des boutons ou d'autres composants à backgroundPanel
        JButton clientButton = new JButton("Clients");
        JButton biensButton = new JButton("Biens");
        JButton transactionButton = new JButton("Transactions");
        JButton retourButton = new JButton("Retour");

        // Agrandir les boutons
        clientButton.setPreferredSize(new Dimension(200, 50));
        biensButton.setPreferredSize(new Dimension(200, 50));
        transactionButton.setPreferredSize(new Dimension(200, 50));
        retourButton.setPreferredSize(new Dimension(200, 50));

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le bouton Clients
                ClientManager clientManager = new ClientManager(imageUrl, imageUrl, imageUrl, imageUrl);
                ClientPage clientPage = new ClientPage(clientManager);
                clientPage.setVisible(true);
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        biensButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le bouton Biens
                ConcreteWindoBien windoBien = new ConcreteWindoBien(imageUrl, getDefaultCloseOperation(), getDefaultCloseOperation());
                windoBien.setTitle("Title");
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le bouton Transactions
                TransactionPage transactionPage = new TransactionPage();
                transactionPage.setVisible(true);
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le bouton Retour
                OurInterface welcomePage = new OurInterface();
                welcomePage.setVisible(true);
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        // Utiliser GridBagConstraints pour centrer les boutons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons
        gbc.anchor = GridBagConstraints.CENTER;

        backgroundPanel.add(clientButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(biensButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(transactionButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(retourButton, gbc);

        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NextPage().setVisible(true);
            }
        });
    }
}