package IStoreApp.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User("test@example.com", "JohnDoe", "password123", "user");
    }

    @Test
    public void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testGetPseudo() {
        assertEquals("JohnDoe", user.getPseudo());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetRole() {
        assertEquals("user", user.getRole());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", user.getEmail());
    }

    @Test
    public void testSetPseudo() {
        user.setPseudo("JaneDoe");
        assertEquals("JaneDoe", user.getPseudo());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }

    @Test
    public void testSetRole() {
        user.setRole("admin");
        assertEquals("admin", user.getRole());
    }
}