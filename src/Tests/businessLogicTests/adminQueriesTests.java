package Tests.businessLogicTests;
import Main.Persistance.Conexion;
import Main.businessLogic.adminQueries;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

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
}
