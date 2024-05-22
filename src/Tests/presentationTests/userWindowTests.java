package Tests.presentationTests;

import Main.Domain.BackgroundPanel;
import Main.Persistance.Conexion;
import Main.Presentation.UserWindow;
import org.junit.jupiter.api.*;

import javax.swing.*;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UserWindow class.
 */
class userWindowTests {

    private Connection connection;
    private UserWindow userWindow;

    /**
     * Setup method to initialize the connection and user window.
     */
    @BeforeEach
    void setUp() {
        Conexion conexion = new Conexion();
        connection = conexion.conectar();
        userWindow = new UserWindow(connection, "IceNinja23");
    }

    /**
     * Tear down method to close the connection.
     */
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

    /**
     * Tests the creation of the user panel.
     */
    @Test
    void createUserPanelTest() {
        assertNotNull(userWindow);
    }

    /**
     * Tests the display of the character panel.
     */
    @Test
    void showCharacterPanelTest() {
        userWindow.showCharacterPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
    }

    /**
     * Tests the creation of the guild panel.
     */
    @Test
    void createGuildPanelTest() {
        userWindow.createGuildPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
    }

    /**
     * Tests the display of the missions panel.
     */
    @Test
    void showMissionsPanelTest() {
        userWindow.showMissionsPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
        assertTrue(components[0] instanceof JPanel);
    }

    /**
     * Tests the display of the regions panel.
     */
    @Test
    void showRegionsPanelTest() {
        userWindow.showRegionsPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
        assertTrue(components[0] instanceof JScrollPane);
    }

    /**
     * Tests the support button functionality.
     */
    @Test
    void supportButtonTest() {
        JButton supportButton = findButtonWithText("Support");
        assertNotNull(supportButton);

        supportButton.doClick();
        boolean supportWindowOpened = false;
        for (Window window : JFrame.getWindows()) {
            if (window instanceof JFrame) {
                JFrame frame = (JFrame) window;
                if ("Support".equals(frame.getTitle())) {
                    supportWindowOpened = true;
                    frame.dispose();
                    break;
                }
            }
        }
        assertTrue(supportWindowOpened);
    }

    /**
     * Helper method to find a button with a specific text within the user window.
     *
     * @param text The text of the button to find.
     * @return The button found, or null if not found.
     */
    private JButton findButtonWithText(String text) {
        Component[] components = userWindow.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof BackgroundPanel) {
                BackgroundPanel backgroundPanel = (BackgroundPanel) component;
                Component[] bgComponents = backgroundPanel.getComponents();
                for (Component bgComponent : bgComponents) {
                    if (bgComponent instanceof JPanel) {
                        JPanel panel = (JPanel) bgComponent;
                        Component[] panelComponents = panel.getComponents();
                        for (Component panelComponent : panelComponents) {
                            if (panelComponent instanceof JButton) {
                                JButton button = (JButton) panelComponent;
                                if (text.equals(button.getText())) {
                                    return button;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
