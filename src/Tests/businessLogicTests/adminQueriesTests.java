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

public class adminQueriesTests {

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
    public void testCreateUserListPanel() throws SQLException {
        JList<String> userList = adminQueries.getUsersList(conn);

        assertNotNull(userList);
        assertEquals(33, userList.getModel().getSize());
        assertEquals("ShadowWarrior87", userList.getModel().getElementAt(0));
        assertEquals("Firestorm99", userList.getModel().getElementAt(1));
    }

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

    @Test
    @Order(3)
    public void testLoadUsersMessages() throws SQLException {
        StringBuilder result = adminQueries.loadUsersMessages(conn);

        assertNotNull(result);
        assertTrue(result.toString().contains("Test message"), "Messages should contain the test message.");
    }

    @Test
    @Order(4)
    public void testGetGuilds() throws SQLException {
        JList<String> guildsList = adminQueries.getGuilds(conn);

        assertNotNull(guildsList);
        assertEquals(10, guildsList.getModel().getSize());
        assertEquals("KnightsOfNi", guildsList.getModel().getElementAt(0));
        assertEquals("DragonSlayers", guildsList.getModel().getElementAt(1));
    }

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

    @Test
    @Order(6)
    public void testGetTopPlayers() throws SQLException {
        ArrayList<Player> topPlayers = adminQueries.getTopPlayers(conn);

        assertNotNull(topPlayers);
        assertEquals(10, topPlayers.size());
        assertEquals("DragonKnight", topPlayers.get(0).getName());
        assertEquals(100, topPlayers.get(0).getLevel());
    }

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
