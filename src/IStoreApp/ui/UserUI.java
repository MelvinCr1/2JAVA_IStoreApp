// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import IStoreApp.model.User;
import IStoreApp.service.Authentication;
import IStoreApp.service.PasswordManager;
import IStoreApp.service.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserUI extends JFrame {
    private static final JTextField emailField = new JTextField(20);
    private static final JTextField pseudoField = new JTextField(20);
    private static final JPasswordField passwordField = new JPasswordField(20);
    private static final JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"admin", "user", "other"}); // Liste déroulante
    private String sessionId;

    public UserUI(String sessionId) {
        this.sessionId = sessionId;

        setTitle("Gestion Utilisateur");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 350);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // 5 lignes, 1 colonne, espacement de 10 pixels

        panel.add(new JLabel("Email de l'utilisateur :"));
        panel.add(emailField);

        panel.add(new JLabel("Pseudo de l'utilisateur :"));
        panel.add(pseudoField);

        panel.add(new JLabel("Mot de passe de l'utilisateur :"));
        panel.add(passwordField);

        // Vérifier si l'utilisateur est un administrateur pour afficher le label et la liste déroulante du rôle
        if (Authentication.isCurrentUserAdmin(sessionId)) {
            panel.add(new JLabel("Rôle de l'utilisateur :"));
            panel.add(roleComboBox);
        }

        // Bouton pour créer un nouvel utilisateur
        JButton createUserButton = new JButton("Créer un nouvel utilisateur");
        createUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String pseudo = pseudoField.getText();
                String password = new String(passwordField.getPassword());
                String role = "user"; // Rôle par défaut

                // Si l'utilisateur est un administrateur, récupérer le rôle à partir de la liste déroulante
                if (Authentication.isCurrentUserAdmin(sessionId)) {
                    role = (String) roleComboBox.getSelectedItem();
                }

                // Vérifier si l'un des champs est vide
                if(email.isEmpty() || pseudo.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(UserUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Hasher le mot de passe avant de le stocker dans la base de données
                    String hashedPassword = PasswordManager.hashPassword(password);
                    IStoreApp.service.UserManager.createUser(new User(email, pseudo, hashedPassword, role));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(createUserButton);

        // Bouton pour mettre à jour un utilisateur
        JButton updateAdminButton = new JButton("Mettre à jour un utilisateur");
        updateAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String newPseudo = pseudoField.getText();
                String newPassword = new String(passwordField.getPassword());
                String newRole = (String) roleComboBox.getSelectedItem();
                User user = null;

                // Vérifier si l'un des champs est vide
                if(email.isEmpty() || newPseudo.isEmpty() || newPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(UserUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    user = UserManager.getUserByEmail(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                user.setPseudo(newPseudo);
                String hashedPassword = PasswordManager.hashPassword(newPassword);
                user.setPassword(hashedPassword);
                user.setRole(newRole);
                try {
                    UserManager.updateUser(user, sessionId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(updateAdminButton);

        // Bouton pour supprimer un utilisateur
        JButton deleteUserButton = new JButton("Supprimer un utilisateur");
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                User user = null;
                try {
                    user = UserManager.getUserByEmail(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    UserManager.deleteUser(user, sessionId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(deleteUserButton);

        // Bouton pour afficher les détails d'un utilisateur
        JButton displayUserButton = new JButton("Afficher les détails d'un utilisateur");
        displayUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'email de l'utilisateur depuis le champ de saisie
                String email = emailField.getText();

                try {
                    // Récupérer l'utilisateur depuis la base de données en utilisant son email
                    User user = UserManager.getUserByEmail(email);

                    // Vérifier si l'utilisateur existe
                    if (user != null) {
                        // Afficher les détails de l'utilisateur dans une boîte de dialogue
                        JOptionPane.showMessageDialog(null, "Détails de l'utilisateur :\n" +
                                "Email: " + user.getEmail() + "\n" +
                                "Nom: " + user.getPseudo() + "\n" +
                                "Role: " + user.getRole());
                    } else {
                        // Afficher un message si l'utilisateur n'existe pas
                        JOptionPane.showMessageDialog(null, "L'utilisateur avec l'email " + email + " n'existe pas.");
                    }
                } catch (SQLException ex) {
                    // Gérer les exceptions liées à l'accès à la base de données
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(displayUserButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Ajout du panneau au cadre principal
        add(panel);

        panel.add(exitButton);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    public static void main(String sessionId) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserUI userUI = new UserUI(sessionId);
                userUI.setVisible(true);
            }
        });
    }
}