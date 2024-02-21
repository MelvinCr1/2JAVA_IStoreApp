package IStoreApp.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SessionManagerTest {

    private String testUserEmail;
    private String testSessionId;

    @Before
    public void setUp() {
        // Initialisation des variables de test
        testUserEmail = "test@example.com";
        testSessionId = SessionManager.startSession(testUserEmail);
    }

    @After
    public void tearDown() {
        // Nettoyage après chaque test
        SessionManager.endSession(testSessionId);
    }

    @Test
    public void testStartSession() {
        // Vérifie si une session est démarrée avec succès
        assertTrue(SessionManager.getUserEmail(testSessionId).equals(testUserEmail));
    }

    @Test
    public void testEndSession() {
        // Termine la session et vérifie si elle est correctement terminée
        SessionManager.endSession(testSessionId);
        assertNull(SessionManager.getUserEmail(testSessionId));
    }

    @Test
    public void testGenerateSessionId() {
        // Vérifie si un identifiant de session est généré avec succès
        String newSessionId = SessionManager.startSession("test2@example.com");
        assertTrue(newSessionId != null && !newSessionId.isEmpty());
    }
}
