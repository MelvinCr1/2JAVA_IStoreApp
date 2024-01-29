// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.service;

import IStoreApp.model.Inventory;
import IStoreApp.dataAccess.InventoryAccess;
import IStoreApp.model.Item;

import java.sql.SQLException;
import java.util.List;

public class InventoryManager {
    private static final InventoryAccess inventoryAccess;

    static {
        try {
            inventoryAccess = new InventoryAccess();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Méthode pour créer un nouvel inventaire
    public static void createInventory(Inventory inventory) throws SQLException {
        // Vérification si l'inventaire existe déja pour ce magasin
        if (getInventoryByStoreId(inventory.getStoreId()) != null){
            System.out.println("Erreur : Un inventaire pour ce magasin existe déja.");
            return;
        }
        InventoryAccess.createInventory(inventory);
        System.out.println("Inventaire crée avec succès.");
    }

    // Méthode pour récupérer un inventaire par son ID de magasin
    public static Inventory getInventoryByStoreId(int storeId) throws SQLException {
        return inventoryAccess.getInventoryByStoreId(storeId);
    }

    // Méthode pour mettre à jour un inventaire existant
    public static void updateInventory(Inventory inventory) throws SQLException {
        // Vérification si l'inventaire existe
        Inventory existingInventory = getInventoryByStoreId(inventory.getStoreId());
        if (existingInventory == null){
            System.out.println("Erreur : Cet inventaire n'existe pas pour ce magasin.");
            return;
        }
        inventoryAccess.updateInventory(inventory);
        System.out.println("Inventaire mis à jour avec succès !");
    }

    // Méthode pour supprimer un inventaire
    public static void deleteInventory(Inventory inventory) throws SQLException {
        // Vérification si l'inventaire existe
        Inventory existingInventory = getInventoryByStoreId(inventory.getStoreId());
        if (existingInventory == null) {
            System.out.println("Erreur : Cet inventaire n'existe pas pour ce magasin.");
            return;
        }
        inventoryAccess.deleteInventory(inventory);
        System.out.println("Inventaire supprimé avec succès !");
    }

    public static List<Item> getInventoryItems(int inventoryId) {
        return null;
    }

    public static void adjustInventoryItemQuantity(int itemId, int quantityAdjustment) {

    }
}
