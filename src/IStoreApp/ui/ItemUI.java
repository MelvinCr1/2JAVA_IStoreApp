// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import IStoreApp.model.Item;
import IStoreApp.service.ItemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ItemUI extends JFrame {
    private static final JTextField nameField = new JTextField(20);
    private static final JTextField priceField = new JTextField(20);
    private static final JTextField quantityField = new JTextField(20);

    public ItemUI() {
        setTitle("Gestion des articles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 lignes, 1 colonne, espacement de 10 pixels

        // Saisie nom de l'article
        panel.add(new JLabel("Nom de l'article :"));
        panel.add(nameField);

        // Saisie prix de l'article
        panel.add(new JLabel("Prix de l'article :"));
        panel.add(priceField);

        // Saisie quantité en stock
        panel.add(new JLabel("Quantité en stock :"));
        panel.add(quantityField);

        // Bouton pour créer un nouvel article
        JButton createItemButton = new JButton("Créer un nouvel article");
        createItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                // Création d'un nouvel article
                try {
                    ItemManager.createItem(new Item(name, price, quantity));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(createItemButton);

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

    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ItemUI itemUI = new ItemUI();
                itemUI.setVisible(true);
            }
        });
    }
}