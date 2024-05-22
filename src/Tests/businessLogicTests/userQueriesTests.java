package Tests.businessLogicTests;

import Main.Domain.Characters;
import Main.Domain.Player;
import Main.Persistance.Conexion;
import Main.businessLogic.userQueries;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the userQueries class.
 */
public class userQueriesTests {

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
        } catch (SQLException e) {
            fail("Failed to close connection to database: " + e.getMessage());
        }
    }

    /**
     * Tests showing messages for an existing user.
     */
    @Test
    @Order(1)
    public void testShowUserMessages_UserExists_ShowMessages() {
        String username = "ShadowWarrior87";

        assertDoesNotThrow(() -> userQueries.showUserMessages(username, conn));
    }

    /**
     * Tests showing messages for a non-existing user.
     */
    @Test
    @Order(2)
    public void testShowUserMessages_UserDoesNotExist_ShowErrorMessage() {
        String username = "nonExistentUser";

        assertThrows(Exception.class, () -> userQueries.showUserMessages(username, conn));
    }

    /**
     * Tests getting a user by username for an existing user.
     */
    @Test
    @Order(3)
    public void testGetUserByUsername_UserExists_ReturnsPlayer() {
        String username = "IceNinja23";

        Player result = userQueries.getUserByUsername(username, conn);

        assertNotNull(result);
        assertEquals(username, result.getName());
    }

    /**
     * Tests getting a user by username for a non-existing user.
     */
    @Test
    @Order(4)
    public void testGetUserByUsername_UserDoesNotExist_ReturnsNull() {
        String username = "nonExistentUser";

        Player result = userQueries.getUserByUsername(username, conn);

        assertNull(result);
    }

    /**
     * Tests sending a message to support for an existing user.
     */
    @Test
    @Order(5)
    public void testSendMessageToSupport_UserExists_MessageInserted() {
        String username = "DragonKnight";
        String message = "Test message";

        assertDoesNotThrow(() -> userQueries.sendMessageToSupport(message, username, conn));
    }

    /**
     * Tests sending a message to support for a non-existing user.
     */
    @Test
    @Order(6)
    public void testSendMessageToSupport_UserNotExists_MessageInserted() {
        String username = "notExistentUser";
        String message = "Test message";

        assertThrows(Exception.class,() -> userQueries.sendMessageToSupport(message, username, conn));
    }

    /**
     * Tests getting characters for an existing user.
     */
    @Test
    @Order(7)
    public void testUsersCharacters_UserExists_ReturnsCharacters() {
        String username = "IceNinja23";

        List<Characters> characters = userQueries.usersCharacters(username, conn);

        assertNotNull(characters);
        assertFalse(characters.isEmpty());
    }
}
