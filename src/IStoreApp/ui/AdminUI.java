package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI extends JFrame {
    private static final JTextField emailField = new JTextField(20);
    private static final JTextField pseudoField = new JTextField(20);
    private static final JPasswordField passwordField = new JPasswordField(20);

    public AdminUI() {
        setTitle("Gestion des administrateurs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 lignes, 1 colonne, espacement de 10 pixels

        // Saisie email de l'administrateur
        panel.add(new JLabel("Email de l'administrateur :"));
        panel.add(emailField);

        // Saisie pseudo de l'administrateur
        panel.add(new JLabel("Pseudo de l'administrateur :"));
        panel.add(pseudoField);

        // Saisie mot de passe de l'administrateur
        panel.add(new JLabel("Mot de passe de l'administrateur :"));
        panel.add(passwordField);

        // Bouton pour créer un nouvel administrateur
        JButton createAdminButton = new JButton("Créer un nouvel administrateur");
        createAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String pseudo = pseudoField.getText();
                String password = new String(passwordField.getPassword());
                // IStoreApp.service.AdminManager.createAdmin(new Admin(email, pseudo, password));
                JOptionPane.showMessageDialog(AdminUI.this, "Pas encore développé");
            }
        });
        panel.add(createAdminButton);

        // Bouton pour afficher les détails d'un administrateur
        JButton displayAdminButton = new JButton("Afficher les détails d'un administrateur");
        displayAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                // Admin admin = AdminManager.getAdminByEmail(email);
                JOptionPane.showMessageDialog(AdminUI.this, "Pas encore développé");
            }
        });
        panel.add(displayAdminButton);

        // Bouton pour mettre à jour un administrateur
        JButton updateAdminButton = new JButton("Mettre à jour un administrateur");
        updateAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String newPseudo = pseudoField.getText();
                String newPassword = new String(passwordField.getPassword());
                // Admin admin = AdminManager.getAdminByEmail(email);
                // admin.setPseudo(newPseudo);
                // admin.setPassword(newPassword);
                // AdminManager.updateAdmin(admin);
                JOptionPane.showMessageDialog(AdminUI.this, "Pas encore développé");
            }
        });
        panel.add(updateAdminButton);

        // Bouton pour supprimer un administrateur
        JButton deleteAdminButton = new JButton("Supprimer un administrateur");
        deleteAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                // Admin admin = AdminManager.getAdminByEmail(email);
                // AdminManager.deleteAdmin(admin);
                JOptionPane.showMessageDialog(AdminUI.this, "Pas encore développé");
            }
        });
        panel.add(deleteAdminButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Au revoir !");
                System.exit(0);
            }
        });
        panel.add(exitButton);

        // Ajout du panneau au cadre principal
        add(panel);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    public static void main(/*String[] args*/) {
        // Création et affichage de l'interface utilisateur
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AdminUI adminUI = new AdminUI();
                adminUI.setVisible(true);
            }
        });
    }
}