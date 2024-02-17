// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import IStoreApp.model.Admin;
import IStoreApp.service.AdminManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminUI extends JFrame {
    private static final JTextField emailField = new JTextField(20);
    private static final JTextField pseudoField = new JTextField(20);
    private static final JPasswordField passwordField = new JPasswordField(20);

    public AdminUI() {
        setTitle("Gestion des administrateurs");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 lignes, 1 colonne, espacement de 10 pixels

        panel.add(new JLabel("Email de l'administrateur :"));
        panel.add(emailField);

        panel.add(new JLabel("Pseudo de l'administrateur :"));
        panel.add(pseudoField);

        panel.add(new JLabel("Mot de passe de l'administrateur :"));
        panel.add(passwordField);

        // Bouton pour créer un nouvel administrateur
        JButton createAdminButton = new JButton("Créer un nouvel administrateur");
        createAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String pseudo = pseudoField.getText();
                String password = new String(passwordField.getPassword());

                // Vérifier si l'un des champs est vide
                if(email.isEmpty() || pseudo.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    IStoreApp.service.AdminManager.createAdmin(new Admin(email, pseudo, password));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(createAdminButton);

        // Bouton pour afficher les détails d'un administrateur
        JButton displayAdminButton = new JButton("Afficher les détails d'un administrateur");
        displayAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                try {
                    Admin admin = AdminManager.getAdminByEmail(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //JOptionPane.showMessageDialog(AdminUI.this, "Pas encore développé");
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
                Admin admin = null;
                try {
                    admin = AdminManager.getAdminByEmail(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    AdminManager.deleteAdmin(admin);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(deleteAdminButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        // Ajout du panneau au cadre principal
        panel.add(createAdminButton);
        add(panel);

        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    public static void main(/*String[] args*/) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AdminUI adminUI = new AdminUI();
                adminUI.setVisible(true);
            }
        });
    }
}