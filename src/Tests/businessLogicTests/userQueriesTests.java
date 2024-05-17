package Tests.businessLogicTests;

import Domain.Characters;
import Domain.Player;
import Persistance.Conexion;
import businessLogic.userQueries;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class userQueriesTests {

    private static Connection conn;

    @BeforeAll
    static void setup() {
        conn = new Conexion().conectar();
    }

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

    @Test
    @Order(1)
    public void testShowUserMessages_UserExists_ShowMessages() {
        String username = "ShadowWarrior87";

        assertDoesNotThrow(() -> userQueries.showUserMessages(username, conn));
    }

    @Test
    @Order(2)
    public void testShowUserMessages_UserDoesNotExist_ShowErrorMessage() {
        String username = "nonExistentUser";

        assertThrows(Exception.class, () -> userQueries.showUserMessages(username, conn));
    }

    @Test
    @Order(3)
    public void testGetUserByUsername_UserExists_ReturnsPlayer() {
        String username = "IceNinja23";

        Player result = userQueries.getUserByUsername(username, conn);

        assertNotNull(result);
        assertEquals(username, result.getName());
    }

    @Test
    @Order(4)
    public void testGetUserByUsername_UserDoesNotExist_ReturnsNull() {
        String username = "nonExistentUser";

        Player result = userQueries.getUserByUsername(username, conn);

        assertNull(result);
    }

    @Test
    @Order(5)
    public void testSendMessageToSupport_UserExists_MessageInserted() {
        String username = "DragonKnight";
        String message = "Test message";

        assertDoesNotThrow(() -> userQueries.sendMessageToSupport(message, username, conn));
    }

    @Test
    @Order(6)
    public void testSendMessageToSupport_UserNotExists_MessageInserted() {
        String username = "notExistentUser";
        String message = "Test message";

        assertThrows(Exception.class,() -> userQueries.sendMessageToSupport(message, username, conn));
    }

    @Test
    @Order(7)
    public void testUsersCharacters_UserExists_ReturnsCharacters() {
        String username = "IceNinja23";

        List<Characters> characters = userQueries.usersCharacters(username, conn);

        assertNotNull(characters);
        assertFalse(characters.isEmpty());
    }
}
