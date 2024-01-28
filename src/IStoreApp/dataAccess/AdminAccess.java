package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import IStoreApp.model.Admin;

public class AdminAccess {
    private Connection connection;

    public AdminAccess() throws SQLException {
        this.connection = DatabaseManager.getConnection();
    }

    // Méthode pour créer un nouvel administrateur dans la base de données
    public void createAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admins (email, pseudo, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getPseudo());
            statement.setString(3, admin.getPassword());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un administrateur par son email
    public Admin getAdminByEmail(String email) throws SQLException {
        String query = "SELECT * FROM admins WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Admin(resultSet.getString("email"), resultSet.getString("pseudo"), resultSet.getString("password"));
                }
            }
        }
        return null;
    }

    // Méthode pour mettre à jour les informations d'un administrateur dans la base de données
    public void updateAdmin(Admin admin) throws SQLException {
        String query = "UPDATE admins SET pseudo = ?, password = ? WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getPseudo());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getEmail());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un administrateur de la base de données
    public void deleteAdmin(Admin admin) throws SQLException {
        String query = "DELETE FROM admins WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getEmail());
            statement.executeUpdate();
        }
    }
}
