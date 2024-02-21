package IStoreApp.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import IStoreApp.dataAccess.UserAccess;
import IStoreApp.dataAccess.WhitelistAccess;
import IStoreApp.model.User;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
    private UserAccess userAccessMock;
    private WhitelistAccess whitelistAccessMock;
    private UserManager userManagerMock;

    @Before
    public void setUp() {
        userAccessMock = mock(UserAccess.class);
        whitelistAccessMock = mock(WhitelistAccess.class);
        userManagerMock = mock(UserManager.class);
    }

    @Test
    public void testAuthenticate_ValidCredentials() throws SQLException {
        // Mocking
        User user = new User("test@example.com", "JohnDoe", "$2a$10$KdMzPyVL4SmfohKbMhxfGeJey7ewKHHy/sHdUN9cv6m.Y2jW4J8RW", "user");
        when(userAccessMock.getUserByEmail("test@example.com")).thenReturn(user);
        when(userManagerMock.getCurrentUser(anyString())).thenReturn(user);

        assertTrue(Authentication.authenticate("test@example.com", "password123"));
    }

    @Test
    public void testAuthenticate_InvalidCredentials() throws SQLException {
        // Mocking
        when(userAccessMock.getUserByEmail("test@example.com")).thenReturn(null);

        assertFalse(Authentication.authenticate("test@example.com", "password123"));
    }

    @Test
    public void testIsEmailWhitelisted_WhitelistedEmail() throws SQLException {
        // Mocking
        when(whitelistAccessMock.isEmailWhitelisted("test@example.com")).thenReturn(true);

        assertTrue(Authentication.isEmailWhitelisted("test@example.com"));
    }

    @Test
    public void testIsEmailWhitelisted_NonWhitelistedEmail() throws SQLException {
        // Mocking
        when(whitelistAccessMock.isEmailWhitelisted("test@example.com")).thenReturn(false);

        assertFalse(Authentication.isEmailWhitelisted("test@example.com"));
    }

    @Test
    public void testIsCurrentUserAdmin_AdminUser() throws SQLException {
        // Mocking
        User adminUser = new User("admin@example.com", "Admin", "adminpassword", "admin");
        when(userManagerMock.getCurrentUser(anyString())).thenReturn(adminUser);

        assertTrue(Authentication.isCurrentUserAdmin("sessionId"));
    }

    @Test
    public void testIsCurrentUserAdmin_NonAdminUser() throws SQLException {
        // Mocking
        User regularUser = new User("user@example.com", "User", "userpassword", "user");
        when(userManagerMock.getCurrentUser(anyString())).thenReturn(regularUser);

        assertFalse(Authentication.isCurrentUserAdmin("sessionId"));
    }

}