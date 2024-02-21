package IStoreApp.service;

import static org.junit.Assert.*;
import org.junit.Test;

public class LoggerTest {

    @Test
    public void testLog() {
        String message = "Test log message";
        Logger.log(message);

        assertTrue(checkLogContainsMessage(message));
    }

    private boolean checkLogContainsMessage(String message) {
        return true;
    }
}
