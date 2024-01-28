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

    public static Employee getEmployeeById(int employeeId) {
        return null;
    }

    /*
    // Méthode pour récupérer un employé par son identifiant
    public Employee getEmployeeById(int employeeId) throws SQLException {
        String query = "SELECT * FROM employees WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Employee();
                }
            }
        }
        return null;
    }
    */

    // Méthode pour mettre à jour les informations d'un employé dans la base de données
    public void updateEmployee(Employee employee) throws SQLException {
        // Implémentez la logique pour mettre à jour un employé dans la base de données
    }

    // Méthode pour supprimer un employé de la base de données
    public void deleteEmployee(Employee employee) throws SQLException {
        // Implémentez la logique pour supprimer un employé de la base de données
    }
}
