// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence

package IStoreApp.ui;

import IStoreApp.service.Authentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginUI() {
        setTitle("Connexion");
        setSize(320, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email :");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(e -> {
            try {
                authenticate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton registerButton = new JButton("Créer un compte");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterUI registerUI = new RegisterUI();
                registerUI.setVisible(true);
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private void authenticate() throws SQLException {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (Authentication.authenticate(email, password)) {
            JOptionPane.showMessageDialog(this, "Authentification réussie !");
            // Redirection vers le menu principal
            UIManager.main(new String[]{});
            dispose(); // Fermer la fenêtre
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Erreur : Email ou mot de passe incorrect. Voulez-vous réessayer ?", "Erreur d'authentification", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                // Quitter
                JOptionPane.showMessageDialog(this, "Déconnexion !");
                dispose(); // Fermer la fenêtre
                System.exit(0);
            }
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(LoginUI::new);
    }
}