// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIManager extends JFrame {
    public UIManager() {
        setTitle("iStore Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Création d'un panneau pour contenir les boutons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10)); // 6 lignes, 1 colonne, espacement de 10 pixels

        // Bouton pour la gestion des utilisateurs
        JButton userButton = new JButton("Gestion des utilisateurs");
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserUI.main();
            }
        });
        panel.add(userButton);

        // Bouton pour la gestion des administrateurs
        JButton adminButton = new JButton("Gestion des administrateurs");
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminUI.main();
            }
        });
        panel.add(adminButton);

        // Bouton pour la gestion des magasins
        JButton storeButton = new JButton("Gestion des magasins");
        storeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StoreUI.main();
            }
        });
        panel.add(storeButton);

        // Bouton pour la gestion des inventaires
        JButton inventoryButton = new JButton("Gestion des inventaires");
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InventoryUI.main();
            }
        });
        panel.add(inventoryButton);

        // Bouton pour la gestion des articles
        JButton itemButton = new JButton("Gestion des articles");
        itemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemUI.main();
            }
        });
        panel.add(itemButton);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager uiManager = new UIManager();
                uiManager.setVisible(true);
            }
        });
    }
}