// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.service;

import IStoreApp.model.User;
import IStoreApp.dataAccess.UserAccess;

import java.sql.SQLException;

public class UserManager {
    private static final UserAccess userAccess;

    static {
        try {
            userAccess = new UserAccess();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Méthode pour créer un nouvel utilisateur
    public static void createUser(User user) throws SQLException {
        // Vérification si l'email est déja utilisé
        if (getUserByEmail(user.getEmail()) != null){
            System.out.println("Erreur : Cet email est déja utilisé.");
            return;
        }
        // Appel à la méthode d'accès aux données pour enregistrer l'utilisateur
        IStoreApp.dataAccess.UserAccess.createUser(user);
        System.out.println("Utilisateur créé avec succès !");
    }

    // Méthode pour récupérer un utilisateur par son email
    public static User getUserByEmail(String email) throws SQLException {
        // Appel à la méthode d'accès aux données pour récupérer l'utilisateur par email
        return userAccess.getUserByEmail(email);
    }

    // Méthode pour mettre à jour un utilisateur existant
    public static void updateUser(User user) throws SQLException {
        // Vérification si l'utilisateur existe
        User existingUser = getUserByEmail(user.getEmail());
        if (existingUser == null) {
            System.out.println("Erreur : Utilisateur inexistant.");
            return;
        }
        // Mise à jour de l'utilisateur
        IStoreApp.dataAccess.UserAccess.updateUser(user);
        System.out.println("Utilisateur mis à jour avec succès !");
    }

    // Méthode pour supprimer un utilisateur
    public static void deleteUser(User user) throws SQLException {
        // Vérification si l'utilisateur existe
        User existingUser = getUserByEmail(user.getEmail());
        if (existingUser == null){
            System.out.println("Erreur : Utilisateur inexistant.");
            return;
        }
        // Suppression de l'utilisateur
        IStoreApp.dataAccess.UserAccess.deleteUser(user);
        System.out.println("Utilisateur supprimé avec succès !");
    }

    // Méthode pour vérifier si un utilisateur existe avec l'email donné
    public static boolean isUserExists(String email) throws SQLException {
        // Utiliser l'accès aux données pour vérifier si l'utilisateur existe
        User user = UserAccess.getUserByEmail(email);
        // Si user est différent de null, cela signifie qu'un utilisateur avec cet email existe
        return user != null;
    }

    // Méthode pour récupérer l'utilisateur actuellement connecté
    public static User getCurrentUser(String sessionId) throws SQLException {
        // Récupérer l'email de l'utilisateur à partir de l'identifiant de session
        String userEmail = SessionManager.getUserEmail(sessionId);

        // Vérifier si l'email existe dans la session
        if (userEmail != null) {
            // Si oui, récupérer l'utilisateur depuis la base de données en utilisant l'email
            return UserAccess.getUserByEmail(userEmail);
        } else {
            // Si l'email n'est pas trouvé dans la session, retourner null
            return null;
        }
    }
}