package Tests.presentationTests;

import Main.Domain.BackgroundPanel;
import Main.Persistance.Conexion;
import Main.Presentation.UserWindow;
import org.junit.jupiter.api.*;

import javax.swing.*;

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

    @Test
    void showMissionsPanelTest() {
        userWindow.showMissionsPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
        assertTrue(components[0] instanceof JPanel);
    }

    @Test
    void showRegionsPanelTest() {
        userWindow.showRegionsPanel();
        Component[] components = userWindow.getCenterPanel().getComponents();
        assertTrue(components.length > 0);
        assertTrue(components[0] instanceof JScrollPane);
    }

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
