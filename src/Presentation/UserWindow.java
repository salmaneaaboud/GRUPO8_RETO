package Presentation;

import Domain.Character;
import businessLogic.userQueries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class UserWindow extends JFrame {
    private final Connection conn;
    private final String saveUsername;

    public UserWindow(Connection connection, String saveUsername) {
        this.conn = connection;
        this.saveUsername = saveUsername;
        createUserPanel();
    }

    public void createUserPanel() {
        JFrame frame = new JFrame("Presentation.Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        JPanel panel = new JPanel(new BorderLayout());
        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(createButtonWithListener("Characters", null));
        northPanel.add(createButtonWithListener("Messages", e -> userQueries.showUserMessages(saveUsername,conn)));
        northPanel.add(createButtonWithListener("Ranking", null));
        northPanel.add(createButtonWithListener("Missions", null));
        northPanel.add(createButtonWithListener("Regions", null));
        northPanel.add(createButtonWithListener("Guild", null));
        northPanel.add(createButtonWithListener("News", null));
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

        // Center with GridLayout simulating a grid of characters
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Creating custom panels to represent user's characters
        List<Character> characters = userQueries.usersCharacters(saveUsername,conn);
        if (characters != null) {
            for (Character i : characters) {
                centerPanel.add(Main.createCharacterPanel(i.getName(), i.getType() + " Level: " + i.getLevel(), i.getImage()));
            }
        }
        panel.add(centerPanel, BorderLayout.CENTER);

        JButton supportButton = new JButton("Support");
        supportButton.addActionListener(e -> showSupportForm());
        southPanel.add(supportButton);

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);


        frame.getContentPane().add(panel);
        frame.setVisible(true);
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

    private void showSupportForm() {
        JFrame supportFrame = new JFrame("Support");
        supportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        supportFrame.setSize(400, 200);
        supportFrame.setLocationRelativeTo(null); // Center on the screen

        JPanel panel = new JPanel(new BorderLayout());

        JTextArea messageTextArea = new JTextArea();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String message = messageTextArea.getText();
            if (!message.isEmpty()) {
                userQueries.sendMessageToSupport(message,saveUsername,conn);
                JOptionPane.showMessageDialog(supportFrame, "Message sent successfully!");
                supportFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(supportFrame, "Please enter a message to send.");
            }
        });

        panel.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.SOUTH);

        supportFrame.add(panel);
        supportFrame.setVisible(true);
    }


}
