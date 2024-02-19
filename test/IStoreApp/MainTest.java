package IStoreApp;

import static org.junit.Assert.*;
import org.junit.*;

import java.sql.SQLException;

public class MainTest {

    @Test
    public void testTesterConnexion() {
        assertTrue(Main.testerConnexion());
    }

    @Test
    public void testGetConnection() {
        try {
            assertNotNull(Main.getConnection());
        } catch (SQLException e) {
            fail("La méthode getConnection a levé une exception : " + e.getMessage());
        }
    }
}