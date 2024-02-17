// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryUI extends JFrame {
    private static final JTextField storeIdField = new JTextField(20);
    private static final JTextField inventoryIdField = new JTextField(20);
    private static final JTextField itemIdField = new JTextField(20);
    private static final JTextField quantityAdjustmentField = new JTextField(20);

    public InventoryUI() {
        setTitle("Gestion des inventaires");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10)); // 6 lignes, 1 colonne, espacement de 10 pixels

        // Saisie l'ID du magasin associé à l'inventaire
        panel.add(new JLabel("ID du magasin associé à l'inventaire :"));
        panel.add(storeIdField);

        // Saisie ID de l'inventaire
        panel.add(new JLabel("ID de l'inventaire :"));
        panel.add(inventoryIdField);

        // Saisie ID de l'article
        panel.add(new JLabel("ID de l'article :"));
        panel.add(itemIdField);

        // Saisie ajustement de quantité
        panel.add(new JLabel("Ajustement de quantité (+ pour augmenter, - pour diminuer) :"));
        panel.add(quantityAdjustmentField);

        // Bouton pour créer un nouvel inventaire
        JButton createInventoryButton = new JButton("Créer un nouvel inventaire");
        createInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int storeId = Integer.parseInt(storeIdField.getText());
                // IStoreApp.service.InventoryManager.createInventory(new Inventory(storeId));
                JOptionPane.showMessageDialog(InventoryUI.this, "Pas encore développé");
            }
        });
        panel.add(createInventoryButton);

        // Bouton pour afficher les articles dans un inventaire
        JButton displayItemsButton = new JButton("Afficher les articles dans un inventaire");
        displayItemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int inventoryId = Integer.parseInt(inventoryIdField.getText());
                // List<Item> items = IStoreApp.service.InventoryManager.getInventoryItems(inventoryId);
                JOptionPane.showMessageDialog(InventoryUI.this, "Pas encore développé");
            }
        });
        panel.add(displayItemsButton);

        // Bouton pour ajuster la quantité d'un article dans un inventaire
        JButton adjustQuantityButton = new JButton("Ajuster la quantité d'un article dans un inventaire");
        adjustQuantityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int itemId = Integer.parseInt(itemIdField.getText());
                int quantityAdjustment = Integer.parseInt(quantityAdjustmentField.getText());
                // IStoreApp.service.InventoryManager.adjustInventoryItemQuantity(itemId, quantityAdjustment);
                JOptionPane.showMessageDialog(InventoryUI.this, "Pas encore développé");
            }
        });
        panel.add(adjustQuantityButton);

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
                InventoryUI inventoryUI = new InventoryUI();
                inventoryUI.setVisible(true);
            }
        });
    }
}