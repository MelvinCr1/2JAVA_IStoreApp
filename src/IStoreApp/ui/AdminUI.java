// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminUI extends JFrame {
    private static final JTextField whitelistEmail = new JTextField(20);
    private static final JTextField emailField = new JTextField(20);

    public AdminUI() {
        setTitle("Gestion Whitelist");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 lignes, 1 colonne, espacement de 10 pixels

        panel.add(new JLabel("Email :"));
        panel.add(emailField);

        // Bouton pour ajouter une email whiteList
        JButton addEmailButton = new JButton("Ajouter un email whiteList");
        addEmailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();

                // Vérifier si l'un des champs est vide
                if(email.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    IStoreApp.service.WhitelistManager.addToWhitelist(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(addEmailButton);

        // Bouton pour retirer une email whiteList
        JButton deleteEmailButton = new JButton("Supprimer un email whitelist");
        deleteEmailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                try {
                    IStoreApp.service.WhitelistManager.removeFromWhitelist(email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //JOptionPane.showMessageDialog(AdminUI.this, "Pas encore développé");
            }
        });
        panel.add(deleteEmailButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AdminUI adminUI = new AdminUI();
                adminUI.setVisible(true);
            }
        });
    }
}