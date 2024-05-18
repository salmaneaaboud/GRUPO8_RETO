package Main.Presentation;

import Main.Domain.Characters;
import Main.Domain.Guild;
import Exceptions.UserNotFoundException;
import Main.businessLogic.userQueries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class UserWindow extends JFrame {
    private final Connection conn;
    private final String saveUsername;
    private JPanel panel;
    private JPanel centerPanel;

    public UserWindow(Connection connection, String saveUsername) {
        this.conn = connection;
        this.saveUsername = saveUsername;
        createUserPanel();
    }

    public void createUserPanel() {
        JFrame frame = new JFrame("Main.Presentation.Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        panel = new JPanel(new BorderLayout());
        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(createButtonWithListener("Characters", e -> showCharacterPanel()));
        northPanel.add(createButtonWithListener("Messages", e -> {
            try {
                userQueries.showUserMessages(saveUsername,conn);
            } catch (UserNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }));
        northPanel.add(createButtonWithListener("Ranking", null));
        northPanel.add(createButtonWithListener("Missions", null));
        northPanel.add(createButtonWithListener("Regions", null));
        northPanel.add(createButtonWithListener("Guild", e -> createGuildPanel()));
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
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        showCharacterPanel();

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
                try {
                    userQueries.sendMessageToSupport(message,saveUsername,conn);
                } catch (UserNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
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

    public void showCharacterPanel() {
        resetPanel(centerPanel);
        List<Characters> characters = userQueries.usersCharacters(saveUsername,conn);
        if (characters != null) {
            for (Characters i : characters) {
                centerPanel.add(Main.createCharacterPanel(i.getName(), i.getType() + " Level: " + i.getLevel(), i.getImage()));
            }
        }
        panel.add(centerPanel, BorderLayout.CENTER);
    }

    public void createGuildPanel() {
        resetPanel(centerPanel);
        try {
            Guild guild = userQueries.getUserGuild(saveUsername, conn);
            assert guild != null;

            JPanel guildPanel = new JPanel();
            guildPanel.setLayout(new BorderLayout());

            JLabel guildName = new JLabel(guild.getGuildName());
            guildName.setHorizontalAlignment(0);
            guildPanel.add(guildName, BorderLayout.NORTH);

            ImageIcon originalIcon = new ImageIcon(guild.getGuildImage());
            Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            Main.addMouseHoverEffect(imageLabel,scaledIcon);

            guildPanel.add(imageLabel, BorderLayout.CENTER);

            guildPanel.setPreferredSize(centerPanel.getSize());
            centerPanel.add(guildPanel);
        } catch (UserNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public JPanel getCenterPanel() {
        return this.centerPanel;
    }

    private void resetPanel(JPanel panel){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }
}
