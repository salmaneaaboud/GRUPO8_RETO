package Tests.presentationTests;

import Persistance.Conexion;
import Presentation.UserWindow;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

class userWindowTests {

    private Connection connection;
    private UserWindow userWindow;

    @BeforeEach
    void setUp() {
        Conexion conexion = new Conexion();
        connection = conexion.conectar();
        userWindow = new UserWindow(connection, "IceNinja23");
    }


    @AfterEach
    void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createUserPanelTest() {
        assertNotNull(userWindow);
    }

    @Test
    void showCharacterPanelTest() {
        userWindow.showCharacterPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
    }

    @Test
    void createGuildPanelTest() {
        userWindow.createGuildPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
    }


}
