import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static Connection connection;

    @BeforeAll
    static void setUp() {
        // Set up connection to the database for testing
        String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "videojuegosdb";
        String password = "zubiri";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
        // Close connection after all tests are done
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Main panel test")
    void testCreateAndShowGUI() {
        // Call createAndShowGUI method and check if it executes without errors
        assertDoesNotThrow(() -> Main.createAndShowGUI());
    }

    @Test
    @DisplayName("New character panel test")
    void testCreateCharacterPanel() {
        // Call createCharacterPanel method and check if it returns a JPanel
        JPanel characterPanel = Main.createCharacterPanel("Warrior", "Strong and resilient", "./photos/warrior.jfif");
        assertNotNull(characterPanel);
    }

    @Test
    @DisplayName("Authentication for the user")
    void testAuthenticateUser() {
        // Test authentication with correct username and password
        assertEquals(0, Main.authenticateUser("testUser", "testPassword"));
        // Test authentication with incorrect username and password
        assertEquals(-1, Main.authenticateUser("wrongUser", "wrongPassword"));
    }

    @Test
    @DisplayName("List of users")
    void testLoadUsersFromFile() {
        // Test if loadUsersFromFile returns a non-empty map
        Map<String, String> usersMap = Main.loadUsersFromFile();
        assertFalse(usersMap.isEmpty());
    }
}
