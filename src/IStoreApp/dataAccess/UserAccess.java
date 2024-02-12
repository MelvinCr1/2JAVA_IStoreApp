// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import IStoreApp.Main;
import IStoreApp.model.User;

public class UserAccess {
    private static Connection connection;

    public UserAccess() throws SQLException {
        this.connection = Main.getConnection();
    }

    // Méthode pour créer un nouvel utilisateur dans la base de données
    public static void createUser(User user) throws SQLException {
        String query = "INSERT INTO users (email, pseudo, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPseudo());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour les informations d'un utilisateur dans la base de données
    public static void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET pseudo = ?, password = ?, role = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getPseudo());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un utilisateur de la base de données
    public static void deleteUser(User user) throws SQLException {
        String query = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un utilisateur par son email
    public static User getUserByEmail(String email) throws SQLException {
        Connection connection = Main.getConnection();
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(email, resultSet.getString("pseudo"), resultSet.getString("password"), resultSet.getString("role"));
                }
                connection.close();
            }
        }
        connection.close();
        return null;
    }
}