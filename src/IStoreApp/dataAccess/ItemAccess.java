// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IStoreApp.Main;
import IStoreApp.model.Item;

public class ItemAccess {
    private static Connection connection;

    public ItemAccess() throws SQLException {
        this.connection = Main.getConnection();
    }

    // Méthode pour créer un nouvel article dans la base de données
    public static void createItem(Item item) throws SQLException {
        String query = "INSERT INTO items (name, price, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getQuantity());
            statement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour les informations d'un article dans la base de données
    public static void updateItem(Item item) throws SQLException {
        String query = "UPDATE items SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getQuantity());
            statement.setInt(4, item.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un article de la base de données
    public static void deleteItem(Item item) throws SQLException {
        String query = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un article par son identifiant
    public Item getItemById(int itemId) throws SQLException {
        String query = "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity");
                    return new Item(itemId, name, price, quantity);
                }
            }
        }
        return null;
    }
}