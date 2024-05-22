package Main.Presentation;

import Exceptions.UserNotFoundException;
import Main.Domain.*;
import Main.businessLogic.userQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the window for a user, providing access to various functionalities
 * such as viewing characters, messages, ranking, missions, regions, and guilds.
 */
public class UserWindow extends JFrame {
    /**
     * The connection to the database.
     */
    private final Connection conn;
    /**
     * The username of the current user.
     */
    private final String saveUsername;
    /**
     * The central panel of the window.
     */
    private JPanel centerPanel;
    /**
     * The background panel of the window.
     */
    private BackgroundPanel backgroundPanel;

    /**
     * Constructs a new UserWindow with the specified connection and username.
     *
     * @param connection   The connection to the database.
     * @param saveUsername The username of the current user.
     */
    public UserWindow(Connection connection, String saveUsername) {
        this.conn = connection;
        this.saveUsername = saveUsername;
        createUserPanel();
    }

    /**
     * Creates the user panel with various functionalities.
     */
    public void createUserPanel() {
        JFrame frame = new JFrame("User's Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        backgroundPanel = new BackgroundPanel("./photos/bgvs/background3.png");
        backgroundPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(createButtonWithListener("Characters", e -> showCharacterPanel()));
        northPanel.add(createButtonWithListener("Messages", e -> {
            try {
                userQueries.showUserMessages(saveUsername,conn);
            } catch (UserNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }));
        northPanel.add(createButtonWithListener("Ranking", e -> Main.loadRankings(centerPanel)));
        northPanel.add(createButtonWithListener("Missions", e -> showMissionsPanel()));
        northPanel.add(createButtonWithListener("Regions", e -> showRegionsPanel()));
        northPanel.add(createButtonWithListener("Guild", e -> createGuildPanel()));
        northPanel.add(createButtonWithListener("News", e -> Main.loadNews(centerPanel)));
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

        centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        showCharacterPanel();

        JButton supportButton = Main.createAuthentificationButtons("Support");
        supportButton.addActionListener(e -> showSupportForm());
        southPanel.add(supportButton);

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        backgroundPanel.add(southPanel, BorderLayout.SOUTH);


        frame.getContentPane().add(backgroundPanel);
        frame.setVisible(true);
    }

    /**
     * Creates a button with the specified text and action listener.
     * If the action listener is null, a default action is set to display a message dialog.
     * @param buttonText The text displayed on the button.
     * @param actionListener The action listener associated with the button.
     * @return The created button.
     */
    private JButton createButtonWithListener(String buttonText, ActionListener actionListener) {
        JButton button = Main.createCustomButton(buttonText);
        button.addActionListener(Objects.requireNonNullElseGet(actionListener, () -> e -> JOptionPane.showMessageDialog(null, "Coming Soon")));
        return button;
    }

    /**
     * Displays the support form for the user to send messages to support.
     */
    private void showSupportForm() {
        JFrame supportFrame = new JFrame("Support");
        supportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        supportFrame.setSize(400, 200);
        supportFrame.setLocationRelativeTo(null);

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

    /**
     * Displays the character panel with the user's characters.
     */
    public void showCharacterPanel() {
        Main.resetCenterPanel(centerPanel);
        List<Characters> characters = userQueries.usersCharacters(saveUsername,conn);
        if (characters != null) {
            for (Characters i : characters) {
                centerPanel.add(Main.createCharaterBox(i.getName(), i.getType() + " Level: " + i.getLevel(), i.getImage()));
            }
        }
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Displays the missions panel with the latest missions.
     */
    public void showMissionsPanel() {
        Main.resetCenterPanel(centerPanel);
        centerPanel.setLayout(new GridLayout(0, 1, 10, 10));

        ArrayList<Mission> missions = userQueries.getLatestMissions(conn);
        for (Mission mission : missions) {
            JPanel missionPanel = new JPanel(new BorderLayout());
            missionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            missionPanel.setBackground(Color.WHITE);

            JLabel titleLabel = new JLabel("Mission: " + mission.getDescription());
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            titlePanel.add(titleLabel);
            missionPanel.add(titlePanel, BorderLayout.NORTH);

            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            JLabel rewardLabel = new JLabel("Reward: " + mission.getReward());
            JLabel difficultyLabel = new JLabel("Difficulty: " + mission.getDifficulty());
            infoPanel.add(rewardLabel);
            infoPanel.add(difficultyLabel);
            missionPanel.add(infoPanel, BorderLayout.CENTER);

            centerPanel.add(missionPanel);
        }
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Creates and displays the guild panel for the user.
     * If the user is not part of any guild, an exception is thrown.
     */
    public void createGuildPanel() {
        Main.resetCenterPanel(centerPanel);
        try {
            Guild guild = userQueries.getUserGuild(saveUsername, conn);
            assert guild != null;

            JPanel guildPanel = new JPanel();
            guildPanel.setLayout(new BorderLayout());

            JLabel guildName = new JLabel(guild.getGuildName());
            guildName.setFont(new Font("Arial", Font.BOLD, 20));
            guildName.setHorizontalAlignment(SwingConstants.CENTER);
            guildPanel.add(guildName, BorderLayout.NORTH);

            ImageIcon originalIcon = new ImageIcon(guild.getGuildImage());
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            Main.addMouseHoverEffect(imageLabel, scaledIcon);

            guildPanel.add(imageLabel, BorderLayout.CENTER);

            JPanel playersPanel = new JPanel();
            playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
            playersPanel.setOpaque(false);
            List<Player> players = userQueries.getGuildPlayers(guild.getGuildName(), conn);
            for (Player player : players) {
                JLabel playerLabel = new JLabel(player.getName() + " - Level: " + player.getLevel());
                playersPanel.add(playerLabel);
            }

            JTextArea chatArea = new JTextArea();
            chatArea.setEditable(false);
            chatArea.setText("Chat functionality coming soon...");

            JScrollPane chatScrollPane = new JScrollPane(chatArea);
            chatScrollPane.setPreferredSize(new Dimension(300, 100));

            JPanel southPanel = new JPanel(new BorderLayout());
            southPanel.add(new JLabel("Guild Players:"), BorderLayout.NORTH);
            southPanel.add(new JScrollPane(playersPanel), BorderLayout.CENTER);
            southPanel.add(chatScrollPane, BorderLayout.SOUTH);

            guildPanel.add(southPanel, BorderLayout.SOUTH);

            centerPanel.add(guildPanel);
        } catch (UserNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Displays the regions panel with information about different regions.
     */
    public void showRegionsPanel() {
        Main.resetCenterPanel(centerPanel);
        centerPanel.setLayout(new BorderLayout());

        JPanel regionsContainer = new JPanel();
        regionsContainer.setLayout(new BoxLayout(regionsContainer, BoxLayout.Y_AXIS));

        List<Region> regions = userQueries.getRegions(conn);
        if (regions != null) {
            for (Region region : regions) {
                JPanel regionPanel = new JPanel(new BorderLayout());
                regionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                regionPanel.setBackground(new Color(227, 182, 135));
                regionPanel.setMaximumSize(new Dimension(600, 400));

                JLabel nameLabel = new JLabel("  Region: " + region.getName());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
                JPanel titlePanel = new JPanel(new BorderLayout());
                titlePanel.setBackground(new Color(227, 182, 135));
                titlePanel.add(nameLabel, BorderLayout.WEST);
                regionPanel.add(titlePanel, BorderLayout.NORTH);

                JPanel infoPanel = new JPanel(new GridBagLayout());
                infoPanel.setBackground(new Color(227, 182, 135));
                infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;

                JLabel descriptionLabel = new JLabel("Description: " + region.getDescription());
                descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(descriptionLabel, gbc);

                gbc.gridy++;
                JLabel recommendationLabel = new JLabel("Recommendation: " + region.getRecommendation());
                recommendationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                infoPanel.add(recommendationLabel, gbc);

                regionPanel.add(infoPanel, BorderLayout.CENTER);

                ImageIcon originalIcon = new ImageIcon(region.getImage());
                Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(scaledIcon);
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JPanel imagePanel = new JPanel();
                imagePanel.setBackground(new Color(227, 182, 135));
                imagePanel.add(imageLabel);
                regionPanel.add(imagePanel, BorderLayout.SOUTH);

                regionsContainer.add(regionPanel);
                regionsContainer.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(regionsContainer);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Retrieves the center panel of the user window.
     *
     * @return The center panel of the user window.
     */
    public JPanel getCenterPanel() {
        return centerPanel;
    }

}
