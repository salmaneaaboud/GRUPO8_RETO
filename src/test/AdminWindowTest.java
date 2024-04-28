package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AdminWindowTest {
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
    void testCreateAdminPanel() {
        // Create an instance of AdminWindow with a connection
        AdminWindow adminWindow = new AdminWindow(connection);

        // Call createAdminPanel method and check if it returns a JPanel
        JPanel adminPanel = adminWindow.createAdminPanel();
        assertNotNull(adminPanel);
    }
}
