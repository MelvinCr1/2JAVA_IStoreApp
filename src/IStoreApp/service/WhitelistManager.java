// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.service;

import java.sql.SQLException;

public class WhitelistManager {
    // Méthode pour ajouter un e-mail à la liste blanche
    public static void addToWhitelist(String email) throws SQLException {
        // Ajouter l'e-mail à la liste blanche dans la base de données
        IStoreApp.dataAccess.WhitelistAccess.addToWhitelist(email);
    }

    // Méthode pour supprimer un e-mail de la liste blanche
    public static void removeFromWhitelist(String email) throws SQLException {
        // Supprimer l'e-mail de la liste blanche dans la base de données
        IStoreApp.dataAccess.WhitelistAccess.removeFromWhitelist(email);
    }
}

