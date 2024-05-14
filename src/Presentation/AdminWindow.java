package Presentation;

import businessLogic.adminQueries;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminWindow extends JFrame {
    private final Connection conn;

    public AdminWindow(Connection connection) {
        this.conn = connection;
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
        supportButton.addActionListener(e -> showSupportMessages());
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
        JList<String> userList = adminQueries.createUserListPanel(conn);
        centerPanel.add(new JScrollPane(userList));

        JList<String> charactersList = new JList<>();
        centerPanel.add(new JScrollPane(charactersList));

        panel.add(centerPanel, BorderLayout.CENTER);

        if (userList != null) {
            userList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String selectedUser = userList.getSelectedValue();
                    // Panel for character list on the top right
                    adminQueries.createCharactersListPanel(charactersList,selectedUser,conn);
                }
            });
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void showSupportMessages() {
        JFrame supportMessagesFrame = new JFrame("Support Messages");
        supportMessagesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        supportMessagesFrame.setSize(600, 400);
        supportMessagesFrame.setLocationRelativeTo(null); // Center on the screen

        JTextArea messagesTextArea = new JTextArea();
        messagesTextArea.setEditable(false); // Make the text area read-only

        JScrollPane scrollPane = new JScrollPane(messagesTextArea);

        StringBuilder messages = adminQueries.loadUsersMessages(conn);
        messagesTextArea.setText(messages.toString());

        supportMessagesFrame.add(scrollPane);
        supportMessagesFrame.setVisible(true);


    }
}
