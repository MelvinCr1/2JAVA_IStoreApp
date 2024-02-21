// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------
// Classe pour vérifier les informations de connexion

package IStoreApp.service;

import IStoreApp.dataAccess.UserAccess;
import IStoreApp.dataAccess.WhitelistAccess;
import IStoreApp.model.User;

import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Authentication {
    public static boolean authenticate(String email, String password) {
        try {
            // Récupérer l'utilisateur par son email
            User user = UserAccess.getUserByEmail(email);

            // Vérifier si l'utilisateur existe et si le mot de passe correspond
            if (user != null) {
                // Vérifier le mot de passe avec BCrypt
                return BCrypt.checkpw(password, user.getPassword());
            } else {
                return false;
            }
        } catch (SQLException e) {
            // Gérer l'exception liée à l'accès à la base de données
            System.err.println("Erreur lors de l'authentification : " + e.getMessage());
            return false;
        }
    }

    // Méthode pour vérifier si un email est whitelisté
    public static boolean isEmailWhitelisted(String email) {
        try {
            // Vérifier si l'email est whitelisté
            return WhitelistAccess.isEmailWhitelisted(email);
        } catch (SQLException e) {
            // Gérer l'exception liée à l'accès à la base de données
            System.err.println("Erreur lors de la vérification de la liste blanche : " + e.getMessage());
            return false;
        }
    }

    public static boolean isCurrentUserAdmin(String sessionId) {
        try {
            // Récupérer l'utilisateur actuellement connecté
            User currentUser = UserManager.getCurrentUser(sessionId);

            // Vérifier si l'utilisateur existe et si son rôle est "admin"
            return currentUser != null && currentUser.getRole().equalsIgnoreCase("admin");
        } catch (SQLException e) {
            // Gérer l'exception liée à l'accès à la base de données
            System.err.println("Erreur lors de la vérification du rôle de l'utilisateur : " + e.getMessage());
            return false;
        }
    }
}