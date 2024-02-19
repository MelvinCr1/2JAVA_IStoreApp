// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IStoreApp.service.Authentication;
import IStoreApp.service.SessionManager;

public class UIManager extends JFrame {
    private String sessionId;

    public UIManager(String sessionId) {
        this.sessionId = sessionId;

        setTitle("iStore Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        // Création d'un panneau pour contenir les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10)); // 6 lignes, 1 colonne, espacement de 10 pixels

        if (Authentication.isCurrentUserAdmin(sessionId)){
            // Bouton pour la gestion de la whitelist
            JButton adminButton = new JButton("Gestion de la whitelist");
            adminButton.addActionListener(e -> AdminUI.main());
            panel.add(adminButton);
        }

        // Bouton pour la gestion des utilisateurs
        JButton userButton = new JButton("Gestion utilisateur");
        userButton.addActionListener(e -> UserUI.main(sessionId));
        panel.add(userButton);

        // Bouton pour la gestion des magasins
        JButton storeButton = new JButton("Gestion magasin");
        storeButton.addActionListener(e -> StoreUI.main(sessionId));
        panel.add(storeButton);

        // Bouton pour la gestion des inventaires
        JButton inventoryButton = new JButton("Gestion inventaire");
        inventoryButton.addActionListener(e -> InventoryUI.main());
        panel.add(inventoryButton);

        // Bouton pour la gestion des articles
        JButton itemButton = new JButton("Gestion article");
        itemButton.addActionListener(e -> ItemUI.main());
        panel.add(itemButton);

        // Bouton pour se déconnecter
        JButton logoutButton = new JButton("Se déconnecter");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mettre fin à la session actuelle
                SessionManager.endSession(sessionId);

                // Afficher la fenêtre de connexion
                LoginUI loginUI = new LoginUI();
                loginUI.setVisible(true);

                // Fermer la fenêtre actuelle
                dispose();
            }
        });
        panel.add(logoutButton);

        // Bouton pour quitter l'application
        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        // Ajout du panneau au cadre principal
        add(panel);
        // Centrer la fenêtre
        setLocationRelativeTo(null);
    }

    public static void main(String sessionId) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager uiManager = new UIManager(sessionId);
                uiManager.setVisible(true);
            }
        });
    }
}