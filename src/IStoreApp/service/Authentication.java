// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------
// Classe pour vérifier les informations de connexion

package IStoreApp.service;

import IStoreApp.dataAccess.UserAccess;
import IStoreApp.model.User;

import java.sql.SQLException;

public class Authentication {
    public static boolean authenticate(String email, String password) {
        try {
            // Récupérer l'utilisateur par son email
            User user = UserAccess.getUserByEmail(email);

            // Vérifier si l'utilisateur existe et si le mot de passe correspond
            return user != null && user.getPassword().equals(password);
        } catch (SQLException e) {
            // Gérer l'exception liée à l'accès à la base de données
            System.err.println("Erreur lors de l'authentification : " + e.getMessage());
            return false;
        }
    }

    // Méthode pour vérifier si un email est whitelisté
    public static boolean isEmailWhitelisted(String email) {
        return true;
    }
}
