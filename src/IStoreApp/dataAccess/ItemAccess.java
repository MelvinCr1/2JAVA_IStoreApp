package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import IStoreApp.model.Item;

public class ItemAccess {
    private Connection connection;

    public ItemAccess(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour créer un nouvel article dans la base de données
    public void createItem(Item item) throws SQLException {
        String query = "INSERT INTO items (name, price, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getQuantity());
            statement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour les informations d'un article dans la base de données
    public void updateItem(Item item) throws SQLException {
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
    public void deleteItem(Item item) throws SQLException {
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