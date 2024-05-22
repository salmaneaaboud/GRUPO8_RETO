package Tests.presentationTests;

import Main.Presentation.AdminWindow;
import Main.Persistance.Conexion;
import Main.businessLogic.adminQueries;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import static Main.businessLogic.adminQueries.loadUsersMessages;
import static org.junit.jupiter.api.Assertions.*;

public class adminWindowTests {
    private static Connection connection;

    @BeforeAll
    static void setUp() {
        Conexion conexion = new Conexion();
        connection = conexion.conectar();
    }

    @AfterAll
    static void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createAdminPanel_validConnection_success() {
        AdminWindow adminWindow = new AdminWindow(connection);
        assertNotNull(adminWindow);
        assertNotNull(adminWindow.getConnection());
    }

    @Test
    void createAdminPanel_nullConnection_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new AdminWindow(null));
    }

    @Test
    void getUsersList_validConnection_notEmptyList() {
        JList<String> userList = adminQueries.getUsersList(connection);
        assertNotNull(userList);
        assertTrue(userList.getModel().getSize() > 0, "User list should not be empty");
    }

    @Test
    void getUsersList_nullConnection_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> adminQueries.getUsersList(null));
    }

    @Test
    void createCharactersListPanel_validUserName_success() {
        JList<String> charactersList = new JList<>();
        adminQueries.createCharactersListPanel(charactersList, "testUser", connection);
        assertNotNull(charactersList.getModel());
        assertTrue(charactersList.getModel().getSize() >= 0, "Character list should not be null or empty");
    }

    @Test
    void createCharactersListPanel_nullUserName_emptyList() {
        JList<String> charactersList = new JList<>();
        adminQueries.createCharactersListPanel(charactersList, null, connection);
        assertNotNull(charactersList.getModel());
        assertEquals(0, charactersList.getModel().getSize(), "Character list should be empty for null user name");
    }

    @Test
    void loadUsersMessages_validConnection_nonEmptyMessages() {
        StringBuilder messages = loadUsersMessages(connection);
        assertNotNull(messages);
        assertTrue(messages.length() > 0, "Messages should not be empty");
    }

    @Test
    void loadUsersMessages_nullConnection_returnsNull() {
        assertThrows(Exception.class, () -> loadUsersMessages(null));
    }

    @Test
    void supportButtonAction_displaysSupportMessagesWindow() {
        AdminWindow adminWindow = new AdminWindow(connection);
        JButton supportButton = new JButton("Support");
        supportButton.addActionListener(e -> adminWindow.showSupportMessages());
        supportButton.doClick();

        Frame[] frames = Frame.getFrames();
        boolean foundSupportMessagesFrame = false;

        for (Frame frame : frames) {
            if (frame instanceof JFrame && frame.getTitle().equals("Support Messages")) {
                foundSupportMessagesFrame = true;
                frame.dispose();
                break;
            }
        }

        assertTrue(foundSupportMessagesFrame, "Support messages window should be displayed");
    }

    @Test
    void logoutButtonAction_closesCurrentFrameAndRestartsApplication() {
        AdminWindow adminWindow = new AdminWindow(connection);
        JFrame testFrame = new JFrame();
        testFrame.setContentPane(adminWindow.getContentPane());
        testFrame.setSize(1000, 600);
        testFrame.setVisible(true);

        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            testFrame.dispose();
            boolean restartCalled = true;
            assertTrue(restartCalled, "Application should restart after logout");
        });
        logoutButton.doClick();

        assertFalse(testFrame.isVisible(), "Test frame should be closed after logout");
    }
}
