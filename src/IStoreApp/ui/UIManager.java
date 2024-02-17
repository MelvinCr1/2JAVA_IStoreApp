// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IStoreApp.service.Authentication;

public class UIManager extends JFrame {
    private String sessionId;

    public UIManager(String sessionId) {
        this.sessionId = sessionId;

        setTitle("iStore Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Création d'un panneau pour contenir les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10)); // 6 lignes, 1 colonne, espacement de 10 pixels

        // Vérifier le rôle de l'utilisateur
        if (Authentication.isCurrentUserAdmin(sessionId)) {
            // Bouton pour la gestion des utilisateurs
            JButton userButton = new JButton("Gestion des utilisateurs");
            userButton.addActionListener(e -> UserUI.main());
            panel.add(userButton);

            // Bouton pour la gestion des administrateurs
            JButton adminButton = new JButton("Gestion des administrateurs");
            adminButton.addActionListener(e -> AdminUI.main());
            panel.add(adminButton);

            // Bouton pour la gestion des magasins
            JButton storeButton = new JButton("Gestion des magasins");
            storeButton.addActionListener(e -> StoreUI.main());
            panel.add(storeButton);

            // Bouton pour la gestion des inventaires
            JButton inventoryButton = new JButton("Gestion des inventaires");
            inventoryButton.addActionListener(e -> InventoryUI.main());
            panel.add(inventoryButton);

            // Bouton pour la gestion des articles
            JButton itemButton = new JButton("Gestion des articles");
            itemButton.addActionListener(e -> ItemUI.main());
            panel.add(itemButton);
        } else {
            // Bouton pour la gestion des utilisateurs
            JButton userButton = new JButton("Gestion des utilisateurs");
            userButton.addActionListener(e -> UserUI.main());
            panel.add(userButton);
        }

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