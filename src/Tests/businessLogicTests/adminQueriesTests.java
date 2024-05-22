package Tests.businessLogicTests;

import Main.Domain.Characters;
import Main.Domain.Player;
import Main.Persistance.Conexion;
import Main.businessLogic.adminQueries;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the adminQueries class.
 */
public class adminQueriesTests {

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
     * Tests the creation of a user list panel.
     *
     * @throws SQLException if a SQL exception occurs.
     */
    @Test
    @Order(1)
    public void testCreateUserListPanel() throws SQLException {
        JList<String> userList = adminQueries.getUsersList(conn);

        assertNotNull(userList);
        assertEquals(33, userList.getModel().getSize());
        assertEquals("ShadowWarrior87", userList.getModel().getElementAt(0));
        assertEquals("Firestorm99", userList.getModel().getElementAt(1));
    }

    /**
     * Tests the creation of a character list panel.
     *
     * @throws SQLException if a SQL exception occurs.
     */
    @Test
    @Order(2)
    public void testCreateCharactersListPanel() throws SQLException {
        JList<String> mockList = new JList<>();
        adminQueries.createCharactersListPanel(mockList, "ShadowWarrior87", conn);

        DefaultListModel<String> listModel = (DefaultListModel<String>) mockList.getModel();
        assertEquals(3, listModel.getSize());
        assertEquals("Borja", listModel.getElementAt(0));
        assertEquals("Legolas", listModel.getElementAt(1));
        assertEquals("Sombra", listModel.getElementAt(2));
    }

    /**
     * Tests loading user messages.
     *
     * @throws SQLException if a SQL exception occurs.
     */
    @Test
    @Order(3)
    public void testLoadUsersMessages() throws SQLException {
        StringBuilder result = adminQueries.loadUsersMessages(conn);

        assertNotNull(result);
        assertTrue(result.toString().contains("Test message"), "Messages should contain the test message.");
    }

    /**
     * Tests retrieving guilds.
     *
     * @throws SQLException if a SQL exception occurs.
     */
    @Test
    @Order(4)
    public void testGetGuilds() throws SQLException {
        JList<String> guildsList = adminQueries.getGuilds(conn);

        assertNotNull(guildsList);
        assertEquals(10, guildsList.getModel().getSize());
        assertEquals("KnightsOfNi", guildsList.getModel().getElementAt(0));
        assertEquals("DragonSlayers", guildsList.getModel().getElementAt(1));
    }

    /**
     * Tests creating a guild players list panel.
     *
     * @throws SQLException if a SQL exception occurs.
     */
    @Test
    @Order(5)
    public void testCreateGuildPlayersListPanel() throws SQLException {
        JList<String> mockList = new JList<>();
        adminQueries.createGuildPlayersListPanel(mockList, "The Knights of Light", conn);

        DefaultListModel<String> listModel = (DefaultListModel<String>) mockList.getModel();
        assertEquals(3, listModel.getSize());
        assertEquals("ThunderMage42", listModel.getElementAt(0));
        assertEquals("TornadoWarrior", listModel.getElementAt(1));
        assertEquals("delegado33", listModel.getElementAt(2));
    }

    /**
     * Tests retrieving top players.
     *
     * @throws SQLException if a SQL exception occurs.
     */
    @Test
    @Order(6)
    public void testGetTopPlayers() throws SQLException {
        ArrayList<Player> topPlayers = adminQueries.getTopPlayers(conn);

        assertNotNull(topPlayers);
        assertEquals(10, topPlayers.size());
        assertEquals("DragonKnight", topPlayers.get(0).getName());
        assertEquals(100, topPlayers.get(0).getLevel());
    }

    /**
     * Tests retrieving top characters.
     *
     * @throws SQLException if a SQL exception occurs.
     */
    @Test
    @Order(7)
    public void testGetTopCharacters() throws SQLException {
        ArrayList<Characters> topCharacters = adminQueries.getTopCharacters(conn);

        assertNotNull(topCharacters);
        assertEquals(10, topCharacters.size());
        assertEquals("Kael", topCharacters.get(0).getName());
        assertEquals(100, topCharacters.get(0).getLevel());
    }
}
