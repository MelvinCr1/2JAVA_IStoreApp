// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.service;

import IStoreApp.model.Item;
import IStoreApp.dataAccess.ItemAccess;

import java.sql.SQLException;
import java.util.List;

public class ItemManager {
    private static final ItemAccess itemAccess;

    static {
        try {
            itemAccess = new ItemAccess();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createItem(Item item) throws SQLException {
        // Vérifier si l'article existe déjà
        if (getItemById(item.getId()) != null) {
            System.out.println("Erreur : Un article avec cet ID existe déjà.");
            return;
        }
        // Appeler la méthode d'accès aux données pour créer l'article
        IStoreApp.dataAccess.ItemAccess.createItem(item);
        System.out.println("Article créé avec succès !");
    }

    public static Item getItemById(int itemId) throws SQLException {
        // Appeler la méthode d'accès aux données pour récupérer l'article par ID
        return itemAccess.getItemById(itemId);
    }

    public static void updateItem(Item item) throws SQLException {
        // Vérifier si l'article existe
        Item existingItem = getItemById(item.getId());
        if (existingItem == null) {
            System.out.println("Erreur : Cet article n'existe pas.");
            return;
        }
        // Appeler la méthode d'accès aux données pour mettre à jour l'article
        //IStoreApp.dataAccess.ItemAccess.updateItem(item);
        System.out.println("Article mis à jour avec succès !");
    }

    public static void deleteItem(Item item) throws SQLException {
        // Vérifier si l'article existe
        Item existingItem = getItemById(item.getId());
        if (existingItem == null) {
            System.out.println("Erreur : Cet article n'existe pas.");
            return;
        }
        // Appeler la méthode d'accès aux données pour supprimer l'article
        IStoreApp.dataAccess.ItemAccess.deleteItem(item);
        System.out.println("Article supprimé avec succès !");
    }

    public static List<Item> getAllItems() {
        return null;
    }
}
