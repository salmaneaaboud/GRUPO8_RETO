package Main.businessLogic;

import Main.Presentation.AdminWindow;
import Main.Presentation.UserWindow;
import Main.Domain.Player;
import Main.Persistance.databaseQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * The Authenticator class handles user authentication and displays the login form.
 */
public class Authenticator {
    private static String saveUsername;

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @param conn     the connection to the database
     * @return an integer representing the type of user:         0 for a regular user,         1 for an admin user,         -1 for invalid credentials
     */
    public static int authenticateUser(String username, String password, Connection conn) {
        List<Player> players = databaseQueries.loadPlayersFromDatabase(conn);

        assert players != null;
        for (Player player : players) {
            if (player.getName().equals(username) && player.getPassword().equals(password)) {
                saveUsername = player.getName();
                return 0;
            }
        }

        if ("admin".equals(username) && "admin".equals(password)) {
            return 1;
        }

        return -1;
    }

    /**
     * Displays the login form for user authentication.
     *
     * @param parentFrame the parent frame to center the login form relative to
     * @param conn        the connection to the database
     */
    public static void showLoginForm(JFrame parentFrame, Connection conn) {
        JFrame loginFrame = new JFrame("Log In");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLocationRelativeTo(parentFrame);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Log in");
        JButton cancelButton = new JButton("Cancel");

        ActionListener loginAction = e -> {
            String username = userText.getText();
            char[] password = passwordText.getPassword();
            String passwordString = new String(password);
            int userType = authenticateUser(username, passwordString, conn);
            if (userType == 1) {
                loginFrame.dispose();
                parentFrame.dispose();
                new AdminWindow(conn);
            } else if (userType == 0) {
                loginFrame.dispose();
                parentFrame.dispose();
                new UserWindow(conn, saveUsername);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Incorrect username or password.");
            }
        };

        loginButton.addActionListener(loginAction);

        ActionListener enterAction = e -> loginAction.actionPerformed(new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, ""));

        userText.addActionListener(enterAction);
        passwordText.addActionListener(enterAction);

        cancelButton.addActionListener(e -> loginFrame.dispose());

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(cancelButton);
        panel.add(loginButton);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }
}
