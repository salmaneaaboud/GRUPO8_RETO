package Presentation;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminWindow extends JFrame {
    private final Connection connection;

    public AdminWindow(Connection connection) {
        this.connection = connection;
        createAdminPanel();
    }

    public void createAdminPanel() {
        JFrame frame = new JFrame("Presentation.Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        JPanel panel = new JPanel(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("User Management"));
        northPanel.add(new JButton("Guild Management"));
        northPanel.add(new JButton("Advanced Statistics"));
        northPanel.add(new JButton("System"));
        JButton supportButton = new JButton("Support");
        supportButton.addActionListener(e -> showSupportMessages()); // Modified to display stored messages
        northPanel.add(supportButton);
        panel.add(northPanel, BorderLayout.NORTH);

        // South Area with BoxLayout and height of 50
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            // Log out and restart the application
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            topFrame.dispose(); // Close current frame
            Main.createAndShowGUI(); // Restart the application
        });

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        // Center with BoxLayout divided into three parts
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));

        // Panel for user list on the left
        JList<String> userList = createUserListPanel();
        centerPanel.add(new JScrollPane(userList));

        // Panel for character list on the top right
        JList<String> charactersList = new JList<>();
        centerPanel.add(new JScrollPane(charactersList));

        // Panel for possible changes on the bottom right
        // You should implement or remove this panel based on your needs
        // centerPanel.add(createChangesPanel());

        panel.add(centerPanel, BorderLayout.CENTER);

        if (userList != null) {
            userList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    JList<String> list = (JList<String>) e.getSource();
                    String selectedUser = list.getSelectedValue();
                    createCharactersListPanel(charactersList,selectedUser);
                }
            });
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // Method to create a JList containing the list of users
    private JList<String> createUserListPanel() {
        try {
            String userQuery = "SELECT NOMBRE FROM JUGADOR";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(userQuery);

            if (rs.next()) {
                DefaultListModel<String> userListModel = new DefaultListModel<>();
                do {
                    userListModel.addElement(rs.getString("NOMBRE"));
                } while (rs.next());
                return new JList<>(userListModel);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("An error was found while loading the users");
        }
        return null;
    }

    // Method to create a JList containing the list of characters from the database
    private void createCharactersListPanel(JList<String> list, String playerName) {
        try {
            String charactersQuery = "SELECT P.NOMBRE FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?";
            PreparedStatement ps = connection.prepareStatement(charactersQuery);
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();

            DefaultListModel<String> charactersListModel = new DefaultListModel<>();
            list.setModel(charactersListModel);

            while (rs.next()) {
                charactersListModel.addElement(rs.getString("NOMBRE"));
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while loading the characters: " + e.getMessage());
        }
    }

    private void showSupportMessages() {
        JFrame supportMessagesFrame = new JFrame("Support Messages");
        supportMessagesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        supportMessagesFrame.setSize(600, 400);
        supportMessagesFrame.setLocationRelativeTo(null); // Center on the screen

        JTextArea messagesTextArea = new JTextArea();
        messagesTextArea.setEditable(false); // Make the text area read-only

        JScrollPane scrollPane = new JScrollPane(messagesTextArea);

        supportMessagesFrame.add(scrollPane);
        supportMessagesFrame.setVisible(true);

        // Query stored messages and display them in the JTextArea
        StringBuilder messages = new StringBuilder();
        try {
            String query = "SELECT * FROM mensajes_soporte";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                messages.append("User: ").append(resultSet.getString("IdJugador")).append("\n");
                messages.append("Message: ").append(resultSet.getString("mensaje")).append("\n");
                messages.append("Date: ").append(resultSet.getString("fecha")).append("\n\n");
            }
            messagesTextArea.setText(messages.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
