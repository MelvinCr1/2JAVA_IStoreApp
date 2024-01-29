// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IStoreApp.Main;
import IStoreApp.model.Store;

public class StoreAccess {
    private Connection connection;

    public StoreAccess() throws SQLException {
        this.connection = Main.getConnection();
    }

    // Méthode pour créer un nouveau magasin dans la base de données
    public void createStore(Store store) throws SQLException {
        String query = "INSERT INTO stores (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, store.getName());
            statement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour les informations d'un magasin dans la base de données
    public void updateStore(Store store) throws SQLException {
        String query = "UPDATE stores SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, store.getName());
            statement.setInt(2, store.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un magasin de la base de données
    public void deleteStore(Store store) throws SQLException {
        String query = "DELETE FROM stores WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, store.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un magasin par son identifiant
    public Store getStoreById(int storeId) throws SQLException {
        String query = "SELECT * FROM stores WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, storeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int StoreId = 0;
                    return new Store(resultSet.getString("name"), StoreId);
                }
            }
        }
        return null;
    }
}