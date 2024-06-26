package Tests.businessLogicTests;

import Main.Persistance.Conexion;
import Main.businessLogic.Authenticator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Authenticator class.
 */
public class authenticatorTests {

    private static Connection conn;

    /**
     * Establishes a connection to the database before running the tests.
     */
    @BeforeAll
    static void setup() {
        conn = new Conexion().conectar();
    }

    /**
     * Closes the connection to the database after running all the tests.
     */
    @AfterAll
    static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            fail("Failed to close connection to database: " + e.getMessage());
        }
    }

    /**
     * Tests authentication of a valid user.
     */
    @Test
    @Order(1)
    public void testAuthenticateUser_ValidUser_ReturnsUserType() {
        String username = "Firestorm99";
        String password = "fir3st0rm!";

        int userType = Authenticator.authenticateUser(username, password, conn);

        assertEquals(0, userType);
    }

    /**
     * Tests authentication of a valid admin.
     */
    @Test
    @Order(2)
    public void testAuthenticateUser_ValidAdmin_ReturnsAdminType() {
        String username = "admin";
        String password = "admin";

        int userType = Authenticator.authenticateUser(username, password, conn);

        assertEquals(1, userType);
    }

    /**
     * Tests authentication of an invalid user.
     */
    @Test
    @Order(3)
    public void testAuthenticateUser_InvalidUser_ReturnsErrorType() {
        String username = "nonExistentUser";
        String password = "wrongPassword";

        int userType = Authenticator.authenticateUser(username, password, conn);

        assertEquals(-1, userType);
    }

    /**
     * Tests authentication of an invalid admin.
     */
    @Test
    @Order(4)
    public void testAuthenticateUser_InvalidAdmin_ReturnsErrorType() {
        String username = "admin";
        String password = "wrongPassword";

        int userType = Authenticator.authenticateUser(username, password, conn);

        assertEquals(-1, userType);
    }

    /**
     * Tests authentication with empty username and password.
     */
    @Test
    @Order(5)
    public void testAuthenticateUser_EmptyUsernamePassword_ReturnsErrorType() {
        String username = "";
        String password = "";

        int userType = Authenticator.authenticateUser(username, password, conn);

        assertEquals(-1, userType);
    }

    /**
     * Tests authentication of a non-existent user.
     */
    @Test
    @Order(6)
    public void testAuthenticateUser_NonExistentUser_ReturnsErrorType() {
        String username = "nonExistentUser";
        String password = "wrongPassword";

        int userType = Authenticator.authenticateUser(username, password, conn);

        assertEquals(-1, userType);
    }

    /**
     * Tests showing the login form after successful authentication.
     */
    @Test
    @Order(7)
    public void testShowLoginForm_ValidAuthentication_SuccessfulLogin() {
        JFrame parentFrame = new JFrame();

        Authenticator.authenticateUser("Firestorm99", "fir3st0rm!", conn);

        Authenticator.showLoginForm(parentFrame, conn);

        assertFalse(parentFrame.isVisible());
    }
}
