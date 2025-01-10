import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OurInterface extends JFrame {
    public OurInterface() {
        setTitle("Page de Bienvenue");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        // URL de l'image
        String imageUrl = "https://cdn.discordapp.com/attachments/1297979194227036333/1327355811843936256/achat-immobilier-divorce-notaires-office.jpeg?ex=6782c3ee&is=6781726e&hm=59f996d06b273727e55a9988c707bc0a61b0ad0070d81e2f70bf1ac1bb09afc3&";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imageUrl);
        backgroundPanel.setLayout(new GridBagLayout());

        // Label de bienvenue
        JLabel welcomeLabel = new JLabel("Welcome to our application");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setPreferredSize(new Dimension(300, 50));

        // Bouton Get Started
        JButton getStartedButton = new JButton("Get Started");
        getStartedButton.setPreferredSize(new Dimension(200, 50));

        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour le bouton Get Started
                NextPage nextPage = new NextPage();
                nextPage.setVisible(true);
                dispose(); // Ferme la fenêtre actuelle
            }
        });

        // Utiliser GridBagConstraints pour centrer les composants
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des composants
        gbc.anchor = GridBagConstraints.CENTER;

        backgroundPanel.add(welcomeLabel, gbc);
        gbc.gridy++;
        backgroundPanel.add(getStartedButton, gbc);

        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        // Afficher la page de bienvenue
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OurInterface().setVisible(true);
            }
        });
    }
}