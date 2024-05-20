package Main.Presentation;

import Exceptions.UserNotFoundException;
import Main.Domain.Guild;
import Main.Persistance.Conexion;
import Main.businessLogic.adminQueries;
import Main.businessLogic.userQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class AdminWindow extends JFrame {
    private final Connection conn;

    private JPanel centerPanel;

    public AdminWindow(Connection connection) {
        this.conn = connection;
        createAdminPanel();
    }

    public void createAdminPanel() {
        JFrame frame = new JFrame("Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        JPanel panel = new JPanel(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(createButtonWithListener("User Management", e -> openUserManagement()));
        northPanel.add(createButtonWithListener("Guild Management", e -> openGuildManagement()));
        northPanel.add(createButtonWithListener("Advanced Statistics", null));
        northPanel.add(createButtonWithListener("System", null));
        northPanel.add(createButtonWithListener("Support", e -> showSupportMessages()));
        panel.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            topFrame.dispose();
            Main.createAndShowGUI();
        });

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));

        panel.add(centerPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

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

    public void openUserManagement() {
        resetPanel(centerPanel);

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
    }

    public void openGuildManagement() {
        resetPanel(centerPanel);

        JList<String> guildsList = adminQueries.getGuilds(conn);
        centerPanel.add(new JScrollPane(guildsList));

        JList<String> charactersList = new JList<>();
        centerPanel.add(new JScrollPane(charactersList));
    }

    private JButton createButtonWithListener(String buttonText, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        if (actionListener != null) {
            button.addActionListener(actionListener);
        } else {
            button.addActionListener(e -> JOptionPane.showMessageDialog(null, "Coming Soon"));
        }
        return button;
    }

    public Connection getConnection() {
        return conn;
    }

    private void resetPanel(JPanel panel){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();
        AdminWindow a = new AdminWindow(conn);
    }
}
