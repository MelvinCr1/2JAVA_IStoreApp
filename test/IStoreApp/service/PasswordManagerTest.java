package IStoreApp.service;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PasswordManagerTest {

    @Test
    public void testHashPassword() {
        // Teste si le hachage du mot de passe fonctionne correctement
        String plainTextPassword = "password123";
        String hashedPassword = PasswordManager.hashPassword(plainTextPassword);
        assertTrue(PasswordManager.verifyPassword(plainTextPassword, hashedPassword));
    }

    @Test
    public void testVerifyPassword() {
        // Teste si la vérification du mot de passe haché fonctionne correctement
        String plainTextPassword = "password123";
        String hashedPassword = PasswordManager.hashPassword(plainTextPassword);
        assertTrue(PasswordManager.verifyPassword(plainTextPassword, hashedPassword));
    }
}

