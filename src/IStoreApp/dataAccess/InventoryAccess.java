// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IStoreApp.Main;
import IStoreApp.model.Inventory;

public class InventoryAccess {
    private static Connection connection;

    public InventoryAccess() throws SQLException {
        this.connection = Main.getConnection();
    }

    // Méthode pour créer un nouvel inventaire dans la base de données
    public static void createInventory(Inventory inventory) throws SQLException {
        String query = "INSERT INTO inventories (store_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, inventory.getStoreId());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un inventaire par l'identifiant de magasin
    public Inventory getInventoryByStoreId(int storeId) throws SQLException {
        String query = "SELECT * FROM inventories WHERE store_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, storeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Inventory(resultSet.getInt("store_id"));
                }
            }
        }
        return null;
    }

    // Méthode pour mettre à jour les informations d'un inventaire dans la base de données
    public void updateInventory(Inventory inventory) throws SQLException {
        String query = "UPDATE inventories SET store_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, inventory.getStoreId());
            statement.setInt(2, inventory.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un inventaire de la base de données
    public void deleteInventory(Inventory inventory) throws SQLException {
        String query = "DELETE FROM inventories WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, inventory.getId());
            statement.executeUpdate();
        }
    }
}