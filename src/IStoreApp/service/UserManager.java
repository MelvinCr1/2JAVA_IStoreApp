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
        // Vérification si l'email est déjà utilisé
        if (getUserByEmail(user.getEmail()) != null) {
            System.out.println("Erreur : Cet email est déjà utilisé.");
            return;
        }
        // Appel à la méthode d'accès aux données pour enregistrer l'utilisateur
        UserAccess.createUser(user);
        System.out.println("Utilisateur créé avec succès !");
    }

    // Méthode pour récupérer un utilisateur par son email
    public static User getUserByEmail(String email) throws SQLException {
        // Appel à la méthode d'accès aux données pour récupérer l'utilisateur par email
        return userAccess.getUserByEmail(email);
    }

    // Méthode pour mettre à jour un utilisateur existant
    public static void updateUser(User user, String sessionId) throws SQLException {
        User currentUser = getCurrentUser(sessionId); // Récupérer l'utilisateur actuellement connecté

        // Vérification si l'utilisateur existe
        User existingUser = getUserByEmail(user.getEmail());
        if (existingUser == null) {
            System.out.println("Erreur : Utilisateur inexistant.");
            return;
        }

        // Vérification si l'utilisateur actuellement connecté est celui que vous souhaitez mettre à jour
        if (currentUser != null && currentUser.getEmail().equals(user.getEmail())) {
            // L'utilisateur actuellement connecté peut se mettre à jour lui-même
            UserAccess.updateUser(user);
            System.out.println("Utilisateur mis à jour avec succès !");
            return;
        }

        // Vérification si l'utilisateur actuellement connecté est un administrateur
        if (currentUser != null && currentUser.getRole().equalsIgnoreCase("admin")) {
            // L'utilisateur actuellement connecté est un administrateur, donc autoriser la mise à jour
            UserAccess.updateUser(user);
            System.out.println("L'utilisateur est mis à jour avec succès par l'administrateur.");
            return;
        }

        // Si l'utilisateur actuellement connecté n'est ni l'utilisateur lui-même ni un administrateur
        System.out.println("Vous n'avez pas l'autorisation de mettre à jour cet utilisateur.");
    }

    // Méthode pour supprimer un utilisateur
    public static void deleteUser(User user, String sessionId) throws SQLException {
        User currentUser = getCurrentUser(sessionId); // Récupérer l'utilisateur actuellement connecté

        // Vérification si l'utilisateur existe
        User existingUser = getUserByEmail(user.getEmail());
        if (existingUser == null) {
            System.out.println("Erreur : Utilisateur inexistant.");
            return;
        }

        // Vérification si l'utilisateur actuellement connecté est celui que vous souhaitez supprimer
        if (currentUser != null && currentUser.getEmail().equals(user.getEmail())) {
            // L'utilisateur actuellement connecté peut se supprimer lui-même
            UserAccess.deleteUser(user);
            System.out.println("Utilisateur supprimé avec succès !");
            return;
        }

        // Vérification si l'utilisateur actuellement connecté est un administrateur
        if (currentUser != null && currentUser.getRole().equalsIgnoreCase("admin")) {
            // L'utilisateur actuellement connecté est un administrateur, donc autoriser la suppression
            UserAccess.deleteUser(user);
            System.out.println("L'utilisateur est supprimé avec succès par l'administrateur.");
            return;
        }

        // Si l'utilisateur actuellement connecté n'est ni l'utilisateur lui-même ni un administrateur
        System.out.println("Vous n'avez pas l'autorisation de supprimer cet utilisateur.");
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