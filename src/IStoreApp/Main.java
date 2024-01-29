// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------
// MySql database connection management file

package IStoreApp;

import IStoreApp.ui.LoginUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/istoreapp";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    public static boolean testerConnexion() {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            try (Connection connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE)) {
                // La connexion est réussie si aucun SQLException n'est levé
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
    }

    // Vérification
    public static void main(String[] args) {
        if (testerConnexion()) {
            System.out.println("Connexion à la base de données réussie.");
            LoginUI.main();
        } else {
            System.out.println("La connexion à la base de données a échoué.");
        }
    }
}

/*package IStoreApp.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/istoreapp";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Tentative de connexion à la base de données
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie !");
        } catch (SQLException e) {
            // Gestion des exceptions en cas d'échec de la connexion
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
        return connection;
    }
}
*/
