package IStoreApp.dataAccess;

import IStoreApp.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WhiteListAccess {
    private static Connection connection;

    public WhiteListAccess(Connection connection) {
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
}
