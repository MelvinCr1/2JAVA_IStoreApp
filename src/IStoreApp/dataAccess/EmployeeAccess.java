package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import IStoreApp.model.Employee;

public class EmployeeAccess {
    private static Connection connection;

    public EmployeeAccess() throws SQLException {
        this.connection = DatabaseManager.getConnection();
    }

    // Méthode pour créer un nouvel employé dans la base de données
    public static void createEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un employé par son identifiant
    public static Employee getEmployeeById(int employeeId) throws SQLException {
        String query = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    //return new Employee();
                }
            }
        }
        return null;
    }

    // Méthode pour mettre à jour les informations d'un employé dans la base de données
    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE employees SET pseudo = ?WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getPseudo());
            statement.setInt(3, employee.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un employé de la base de données
    public void deleteEmployee(Employee employee) throws SQLException {
        String query = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
        }
    }
}
