package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import IStoreApp.model.User;

public class UserAccess {
    private Connection connection;

    public UserAccess(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour créer un nouvel utilisateur dans la base de données
    public void createUser(User user) throws SQLException {
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
    public void updateUser(User user) throws SQLException {
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
    public void deleteUser(User user) throws SQLException {
        String query = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un utilisateur par son email
    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(email, resultSet.getString("pseudo"), resultSet.getString("password"), resultSet.getString("role"));
                }
            }
        }
        return null;
    }
}