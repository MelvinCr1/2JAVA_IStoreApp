// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.service;

import IStoreApp.model.Admin;
import IStoreApp.dataAccess.AdminAccess;

import javax.swing.JOptionPane;
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
            JOptionPane.showMessageDialog(null, "Erreur : Cet email est déjà utilisé par un autre administrateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Appel de la méthode d'accès aux données pour créer l'administrateur
        adminAccess.createAdmin(admin);

        JOptionPane.showMessageDialog(null, "Administrateur créé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Erreur : L'administrateur spécifié n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Appel de la méthode d'accès aux données pour mettre à jour l'administrateur
        adminAccess.updateAdmin(admin);
        JOptionPane.showMessageDialog(null, "Administrateur mis à jour avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    // Méthode pour supprimer un administrateur
    public static void deleteAdmin(Admin admin) throws SQLException {
        // Vérification si l'administrateur existe
        Admin existingAdmin = getAdminByEmail(admin.getEmail());
        if (existingAdmin == null){
            JOptionPane.showMessageDialog(null, "Erreur : L'administrateur spécifié n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Appel de la méthode d'accès aux données pour supprimer l'administrateur
        adminAccess.deleteAdmin(admin);
        JOptionPane.showMessageDialog(null, "Administrateur supprimée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
}