package Main.Presentation;

import Main.Domain.BackgroundPanel;
import Main.Domain.Characters;
import Main.Domain.Player;
import Main.Persistance.Conexion;
import Main.businessLogic.adminQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class representing the administration window of the application.
 * Allows administrators to manage users, guilds, advanced statistics, and support messages.
 */
public class AdminWindow extends JFrame {
    /**
     * Connection to the database used by the administration window.
     */
    private final Connection conn;

    /**
     * Central panel of the administration window.
     */
    private JPanel centerPanel;

    /**
     * Creates a new instance of AdminWindow.
     *
     * @param connection Connection to the database.
     */
    public AdminWindow(Connection connection) {
        this.conn = connection;
        createAdminPanel();
    }

    /**
     * Creates the administration panel with its components and functionalities.
     */
    public void createAdminPanel() {
        JFrame frame = new JFrame("Admin Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        BackgroundPanel backgroundPanel = new BackgroundPanel("./photos/bgvs/background2.png");
        backgroundPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(createButtonWithListener("User Management", e -> openUserManagement()));
        northPanel.add(createButtonWithListener("Guild Management", e -> openGuildManagement()));
        northPanel.add(createButtonWithListener("Advanced Statistics", e -> openAdvancedStatistics()));
        northPanel.add(createButtonWithListener("System", null));
        northPanel.add(createButtonWithListener("Support", e -> showSupportMessages()));
        backgroundPanel.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = Main.createAuthentificationButtons("Log out");
        logoutButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(backgroundPanel);
            topFrame.dispose();
            Main.createAndShowGUI();
        });

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        backgroundPanel.add(southPanel, BorderLayout.SOUTH);

        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        JLabel text = new JLabel("You are now logged in as an ADMIN");
        text.setFont(new Font("Serif", Font.PLAIN, 35));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPanel.add(Box.createVerticalGlue());
        labelPanel.add(text);
        labelPanel.add(Box.createVerticalGlue());

        centerPanel.add(labelPanel, BorderLayout.CENTER);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        frame.getContentPane().add(backgroundPanel);
        frame.setVisible(true);
    }

    /**
     * Displays support messages in a modal window.
     */
    public void showSupportMessages() {
        JFrame supportMessagesFrame = new JFrame("Support Messages");
        supportMessagesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        supportMessagesFrame.setSize(600, 400);
        supportMessagesFrame.setLocationRelativeTo(null);

        JTextArea messagesTextArea = new JTextArea();
        messagesTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messagesTextArea);

        StringBuilder messages = adminQueries.loadUsersMessages(conn);
        messagesTextArea.setText(messages.toString());

        supportMessagesFrame.add(scrollPane);
        supportMessagesFrame.setVisible(true);
    }

    /**
     * Opens the user management window.
     */
    public void openUserManagement() {
        Main.resetCenterPanel(centerPanel);

        JList<String> userList = adminQueries.getUsersList(conn);
        centerPanel.add(new JScrollPane(userList));

        JList<String> charactersList = new JList<>();
        centerPanel.add(new JScrollPane(charactersList));

        if (userList != null) {
            userList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String selectedUser = userList.getSelectedValue();
                    adminQueries.createCharactersListPanel(charactersList,selectedUser,conn);
                }
            });
        }

        centerPanel.setOpaque(true);
    }

    /**
     * Opens the guild management window.
     */
    public void openGuildManagement() {
        Main.resetCenterPanel(centerPanel);

        JList<String> guildsList = adminQueries.getGuilds(conn);
        centerPanel.add(new JScrollPane(guildsList));

        JList<String> charactersList = new JList<>();
        centerPanel.add(new JScrollPane(charactersList));

        if (guildsList != null) {
            guildsList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String selectedGuild = guildsList.getSelectedValue();
                    adminQueries.createGuildPlayersListPanel(charactersList,selectedGuild,conn);
                }
            });
        }

        centerPanel.setOpaque(true);
    }

    /**
     * Creates a JButton with the specified text and ActionListener.
     * If an ActionListener is provided, it is added to the button.
     * If no ActionListener is provided, a default ActionListener is added
     * to show a "Coming Soon" message dialog when the button is clicked.
     *
     * @param buttonText The text to be displayed on the button.
     * @param actionListener The ActionListener to be added to the button.
     *                       If null, a default ActionListener is added.
     * @return The created JButton with the specified text and ActionListener.
     */
    private JButton createButtonWithListener(String buttonText, ActionListener actionListener) {
        JButton button = Main.createCustomButton(buttonText);
        if (actionListener != null) {
            button.addActionListener(actionListener);
        } else {
            button.addActionListener(e -> JOptionPane.showMessageDialog(null, "Coming Soon"));
        }
        return button;
    }

    /**
     * Creates and displays advanced statistics in the window.
     */
    public void openAdvancedStatistics() {
        Main.resetCenterPanel(centerPanel);

        JPanel rankingsPanel = new JPanel();
        rankingsPanel.setLayout(new BoxLayout(rankingsPanel, BoxLayout.Y_AXIS));
        rankingsPanel.setOpaque(false);

        DefaultListModel<String> charactersListModel = new DefaultListModel<>();
        ArrayList<Characters> topCharacters = adminQueries.getTopCharacters(conn);
        if (topCharacters != null) {
            for (Characters character : topCharacters) {
                charactersListModel.addElement(character.getName() + " - " + "Level : " + character.getLevel());
            }
        }
        JList<String> topCharactersList = new JList<>(charactersListModel);
        rankingsPanel.add(new JLabel("Top 10 Best Characters"));
        rankingsPanel.add(new JScrollPane(topCharactersList));

        DefaultListModel<String> playersListModel = new DefaultListModel<>();
        ArrayList<Player> topPlayers = adminQueries.getTopPlayers(conn);
        if (topPlayers != null) {
            for (Player player : topPlayers) {
                playersListModel.addElement(player.getName() + " - " + "Level : " + player.getLevel());
            }
        }
        JList<String> topPlayersList = new JList<>(charactersListModel);
        rankingsPanel.add(new JLabel("Top 10 Best Players"));
        rankingsPanel.add(new JScrollPane(topPlayersList));


        centerPanel.add(rankingsPanel);
    }

    /**
     * Returns the connection to the database.
     *
     * @return The connection to the database.
     */
    public Connection getConnection() {
        return conn;
    }
}
