package IStoreApp.service;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordManager {

    // Méthode pour hacher un mot de passe
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Méthode pour vérifier un mot de passe par rapport à son hachage
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}