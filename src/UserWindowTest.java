import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserWindowTest {
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
    void testCreateUserPanel() {
        // Create an instance of UserWindow with a connection and saveUsername
        String saveUsername = "testUser";
        UserWindow userWindow = new UserWindow(connection, saveUsername);

        // Call createUserPanel method and check if it returns a JPanel
        JPanel userPanel = userWindow.createUserPanel();
        assertNotNull(userPanel);
    }
}
