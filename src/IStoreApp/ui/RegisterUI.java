// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import IStoreApp.model.User;
import IStoreApp.service.UserManager;
import IStoreApp.service.PasswordManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static IStoreApp.service.Authentication.isEmailWhitelisted;

public class RegisterUI extends JFrame {
    private static JTextField emailField = new JTextField(20);
    private static JTextField pseudoField = new JTextField(20);
    private static JPasswordField passwordField = new JPasswordField(20);
    private static JPasswordField confirmPasswordField = new JPasswordField(20);
    private static JTextField roleField = new JTextField((20));

    public RegisterUI() {
        setTitle("Créer un compte");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ferme uniquement la fenêtre actuelle
        setLocationRelativeTo(null); // Centrer la fenêtre

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // 5 lignes, 1 colonne, espacement de 10 pixels

        panel.add(new JLabel("Email de l'utilisateur :"));
        panel.add(emailField);

        panel.add(new JLabel("Pseudo de l'utilisateur :"));
        panel.add(pseudoField);

        panel.add(new JLabel("Mot de passe de l'utilisateur :"));
        panel.add(passwordField);

        panel.add(new JLabel("Confirmation du mot de passe :"));
        panel.add(confirmPasswordField);

        // Bouton pour créer un compte
        JButton registerButton = new JButton("Créer un compte");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String pseudo = pseudoField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String role = "user";

                // Vérification si tous les champs sont remplis
                if (email.isEmpty() || pseudo.isEmpty() || password.isEmpty() || role.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterUI.this, "Veuillez remplir tous les champs.");
                    return;
                }

                // Vérification si les mots de passe correspondent
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegisterUI.this, "Les mots de passe ne correspondent pas.");
                    return;
                }

                // Vérifier si l'email est déjà utilisé
                try {
                    if (IStoreApp.service.UserManager.isUserExists(email)) {
                        JOptionPane.showMessageDialog(RegisterUI.this, "Cet email est déjà associé à un compte.");
                        return;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // Vérification si l'email est Whitelist
                try {
                    if (isEmailWhitelisted(email)) {
                        // Hasher le mot de passe avant de l'enregistrer
                        String hashedPassword = PasswordManager.hashPassword(password);
                        UserManager.createUser(new User(email, pseudo, hashedPassword, role));
                        JOptionPane.showMessageDialog(RegisterUI.this, "Compte créé avec succès !");
                        dispose(); // Ferme la fenêtre
                    } else {
                        JOptionPane.showMessageDialog(RegisterUI.this, "Votre email n'est pas autorisé à créer un compte.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterUI::new);
    }
}
