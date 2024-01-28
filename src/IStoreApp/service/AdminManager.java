package IStoreApp.service;

import IStoreApp.model.Admin;
import IStoreApp.dataAccess.AdminAccess;

import java.sql.SQLException;

public class AdminManager {
    private static final AdminAccess adminAccess;

    static {
        try {
            adminAccess = new AdminAccess();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Méthode pour créer un nouvel administrateur
    public static void createAdmin(Admin admin) throws SQLException {
        // Vérification si l'email administrateur existe déja
        if (getAdminByEmail(admin.getEmail()) != null){
            System.out.println("Erreur : Cet email est déja utilisé par un autre administrateur.");
            return;
        }
        // Appel de la méthode d'accès aux données pour créer l'administrateur
        adminAccess.createAdmin(admin);
        System.out.println("Administrateur créé avec succès !");
    }

    // Méthode pour récupérer un administrateur par son email
    public static Admin getAdminByEmail(String email) throws SQLException {
        // Appel de la méthode d'accès aux données pour récupérer l'administrateur
        return adminAccess.getAdminByEmail(email);
    }

    // Méthode pour mettre à jour un administrateur existant
    public static void updateAdmin(Admin admin) throws SQLException {
        // Vérification si l'administrateur existe
        Admin existingAdmin = getAdminByEmail(admin.getEmail());
        if (existingAdmin == null) {
            System.out.println("Erreur : L'administrateur spécifié n'existe pas.");
            return;
        }
        // Appel de la méthode d'accès aux données pour mettre à jour l'administrateur
        adminAccess.updateAdmin(admin);
        System.out.println("Administrateur mis à jour avec succès !");
    }

    // Méthode pour supprimer un administrateur
    public static void deleteAdmin(Admin admin) throws SQLException {
        // Vérification si l'administrateur existe
        Admin existingAdmin = getAdminByEmail(admin.getEmail());
        if (existingAdmin == null){
            System.out.println("Erreur : L'administrateur spécifié n'existe pas.");
            return;
        }
        // Appel de la méthode d'accès aux données pour supprimer l'administrateur
        adminAccess.deleteAdmin(admin);
        System.out.println("Administrateur supprimée avec succès !");
    }
}