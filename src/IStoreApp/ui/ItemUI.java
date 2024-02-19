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
    private static final JTextField storeField = new JTextField(20);

    public ItemUI() {
        setTitle("Gestion Articles");
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

        // Choix du magasin
        panel.add(new JLabel("magasin"));
        panel.add(storeField);

        // Bouton pour créer un nouvel article
        JButton createItemButton = new JButton("Créer un nouvel article");
        createItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String priceStr = priceField.getText();
                String quantityStr = quantityField.getText();
                String store = storeField.getText();

                // Vérifier si tous les champs sont remplis
                if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty() || store.isEmpty()) {
                    JOptionPane.showMessageDialog(ItemUI.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Vérifier si la quantité est supérieure à 0
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityStr);
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(ItemUI.this, "La quantité doit être supérieure à 0.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ItemUI.this, "Veuillez entrer une quantité valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Vérifier si le prix est un nombre valide
                double price;
                try {
                    price = Double.parseDouble(priceStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ItemUI.this, "Veuillez entrer un prix valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Création d'un nouvel article
                try {
                    ItemManager.createItem(new Item(name, price, quantity, store));
                    JOptionPane.showMessageDialog(ItemUI.this, "Article créé avec succès !");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(createItemButton);

        // Bouton pour supprimer un article

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