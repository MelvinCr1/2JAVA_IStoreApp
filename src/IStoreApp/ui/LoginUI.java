package IStoreApp.ui;

import IStoreApp.service.Authentication;

import javax.swing.*;

public class LoginUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginUI() {
        setTitle("Connexion");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email :");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(e -> authenticate());

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void authenticate() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (Authentication.authenticate(email, password)) {
            JOptionPane.showMessageDialog(this, "Authentification réussie, Bienvenue !");
            // Redirection vers le menu principal
            UIManager.main(new String[]{});
            dispose(); // Fermer la fenêtre
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Erreur : Email ou mot de passe incorrect. Voulez-vous réessayer ?", "Erreur d'authentification", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                // Quitter
                JOptionPane.showMessageDialog(this, "Au revoir !");
                dispose(); // Fermer la fenêtre
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::new);
    }
}