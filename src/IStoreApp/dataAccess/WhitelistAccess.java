// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.dataAccess;

import IStoreApp.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WhitelistAccess {
    private static Connection connection;

    public WhitelistAccess(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour vérifier si un email est whitelisté
    public static boolean isEmailWhitelisted(String email) throws SQLException {
        Connection connection = Main.getConnection();
        String query = "SELECT * FROM whitelist WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Retourne true si l'email est trouvé dans la liste blanche
            }
        }
    }

    // Méthode pour ajouter un e-mail à la liste blanche dans la base de données
    public static void addToWhitelist(String email) throws SQLException {
        String query = "INSERT INTO whitelist (email) VALUES (?)";
        try (Connection connection = Main.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un e-mail de la liste blanche dans la base de données
    public static void removeFromWhitelist(String email) throws SQLException {
        String query = "DELETE FROM whitelist WHERE email = ?";
        try (Connection connection = Main.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }
}