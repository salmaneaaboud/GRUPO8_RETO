package Main.Presentation;

import Main.Domain.BackgroundPanel;
import Main.Domain.Characters;
import Main.Domain.News;
import Main.Domain.Player;
import Main.Persistance.Conexion;
import Main.Persistance.databaseQueries;
import Main.businessLogic.Authenticator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * The main class representing the entry point of the application.
 * Contains methods for creating and displaying the main GUI, loading character panels,
 * news panels, and rankings, as well as utility methods for creating buttons and resetting panels.
 */
public class Main extends JFrame {
    /**
     * Connection to the database.
     */
    public static Connection conn;

    /**
     * The central panel of the GUI.
     */
    private static JPanel centerPanel;

    /**
     * The background panel of the GUI.
     */
    private static BackgroundPanel backgroundPanel;

    /**
     * The main method to launch the application.
     * Sets up the GUI and displays it.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    /**
     * Creates and displays the main GUI of the application.
     */
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Warriors of God");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        conn = new Conexion().conectar();

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        backgroundPanel = new BackgroundPanel("./photos/bgvs/background1.png");
        backgroundPanel.setLayout(new BorderLayout());
        mainContainer.add(backgroundPanel);

        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new FlowLayout());
        JButton charactersButton = createCustomButton("Characters");
        charactersButton.addActionListener(e -> loadCharacterPanel());
        northPanel.add(charactersButton);

        JButton rankingButton = createCustomButton("Ranking");
        rankingButton.addActionListener(e -> loadRankings(centerPanel));
        northPanel.add(rankingButton);

        JButton missionsButton = createCustomButton("Missions");
        northPanel.add(missionsButton);
        missionsButton.addActionListener(e -> Authenticator.showLoginForm(frame, conn));

        JButton regionsButton = createCustomButton("Regions");
        northPanel.add(regionsButton);
        regionsButton.addActionListener(e -> Authenticator.showLoginForm(frame, conn));

        JButton newsButton = createCustomButton("News");
        newsButton.addActionListener(e -> loadNews(centerPanel));
        northPanel.add(newsButton);
        backgroundPanel.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = createAuthentificationButtons("Log in");
        loginButton.addActionListener(e -> Authenticator.showLoginForm(frame, conn));

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        backgroundPanel.add(southPanel, BorderLayout.SOUTH);

        centerPanel = new JPanel();
        loadCharacterPanel();

        frame.setVisible(true);
    }

    /**
     * Loads the character panel with character information.
     */
    public static void loadCharacterPanel() {
        resetCenterPanel(centerPanel);
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        centerPanel.add(createCharaterBox("Warrior", "Strong and resilient", "./photos/warrior.jfif"));
        centerPanel.add(createCharaterBox("Archer", "Precise and lethal", "./photos/archer.jfif"));
        centerPanel.add(createCharaterBox("Wizard", "Master of magical power", "./photos/wizard.jfif"));
        centerPanel.add(createCharaterBox("Summoner", "Creature controller", "./photos/summoner.jfif"));
        centerPanel.add(createCharaterBox("Hunter", "Voracious hunter", "./photos/granyhunter.png"));
        centerPanel.add(createCharaterBox("Tank", "Team shield", "./photos/tank.jfif"));
        centerPanel.add(createCharaterBox("Slayer", "Unstoppable berserker", "./photos/milfslayer.jfif"));
        centerPanel.add(createCharaterBox("Assassin", "Swift and stealthy", "./photos/assassin.jfif"));
        centerPanel.add(createCharaterBox("Paladin", "Team support", "./photos/paladin.jfif"));
        centerPanel.add(createCharaterBox("Coming Soon", "Exciting things coming", "./photos/new.jfif"));

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a character box panel with the specified name, description, and image.
     *
     * @param name        The name of the character.
     * @param description The description of the character.
     * @param imagePath   The file path of the character image.
     * @return The created character box panel.
     */
    public static JPanel createCharaterBox(String name, String description, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(icon);
        addMouseHoverEffect(imageLabel, icon);
        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel descriptionLabel = new JLabel(description);
        infoPanel.add(nameLabel);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoPanel.add(descriptionLabel);

        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Adds a mouse hover effect to the specified label with the specified icon.
     *
     * @param label The label to which the mouse hover effect is added.
     * @param icon  The icon associated with the label.
     */
    public static void addMouseHoverEffect(JLabel label, ImageIcon icon) {
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Image img = icon.getImage();
                Image newImg = img.getScaledInstance(icon.getIconWidth() + 50, icon.getIconHeight() + 50, java.awt.Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(newImg));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setIcon(icon);
            }
        });
    }

    /**
     * Loads the news panel with news information.
     *
     * @param panel The panel to which the news is loaded.
     */
    public static void loadNews(JPanel panel) {
        resetCenterPanel(panel);
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        ArrayList<News> newsList = new ArrayList<>();

        newsList.add(new News("  New expansion 'Rise of the Titans' announced for Warriors of God!", "  Get ready to face giants and conquer new territories in the upcoming major update of the game!", "./photos/news/news1.png"));
        newsList.add(new News("  Double XP event this weekend in Warriors of God!", "  Don't miss the opportunity to level up quickly and unlock new abilities during this special event!", "./photos/news/news2.png"));
        newsList.add(new News("  Celebrate the third anniversary of Warriors of God with exclusive gifts!", "  Get exclusive rewards, including legendary weapons and unique mounts, as we celebrate three years of adventures in the world of Warriors of God!", "./photos/news/news3.png"));

        for (News news : newsList) {
            JPanel newsPanel = new JPanel();
            newsPanel.setLayout(new BorderLayout());
            newsPanel.setOpaque(false);

            ImageIcon newsIcon = new ImageIcon(news.getImagePath());
            JLabel newsImageLabel = new JLabel(scaleImage(newsIcon));
            newsPanel.add(newsImageLabel, BorderLayout.WEST);

            JPanel newsInfoPanel = new JPanel();
            newsInfoPanel.setOpaque(false);
            newsInfoPanel.setLayout(new BoxLayout(newsInfoPanel, BoxLayout.Y_AXIS));

            JLabel newsTitleLabel = new JLabel(news.getTitle());
            newsTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JLabel newsDescriptionLabel = new JLabel(news.getBriefDescription());
            newsDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            newsInfoPanel.add(newsTitleLabel);
            newsInfoPanel.add(newsDescriptionLabel);

            newsPanel.add(newsInfoPanel, BorderLayout.CENTER);

            panel.add(newsPanel);
        }
    }

    /**
     * Loads the rankings panel with player and character information.
     *
     * @param panel The panel to which the rankings are loaded.
     */
    public static void loadRankings(JPanel panel) {
        resetCenterPanel(panel);

        ArrayList<Player> topPlayers = databaseQueries.getTopPlayers(conn);
        JPanel topPlayersPanel = new JPanel();
        topPlayersPanel.setBorder(BorderFactory.createTitledBorder("Top 10 Best Players"));
        topPlayersPanel.setLayout(new BoxLayout(topPlayersPanel, BoxLayout.Y_AXIS));
        assert topPlayers != null;
        for (Player player : topPlayers) {
            JLabel playerLabel = new JLabel(player.getName() + " - Level " + player.getLevel());
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
            topPlayersPanel.add(playerLabel);
        }
        JScrollPane playersScrollPane = new JScrollPane(topPlayersPanel);

        ArrayList<Characters> topCharacters = databaseQueries.getTopCharacters(conn);
        JPanel topCharactersPanel = new JPanel();
        topCharactersPanel.setBorder(BorderFactory.createTitledBorder("Top 10 Best Characters"));
        topCharactersPanel.setLayout(new BoxLayout(topCharactersPanel, BoxLayout.Y_AXIS));
        assert topCharacters != null;
        for (Characters character : topCharacters) {
            JLabel characterLabel = new JLabel(character.getName() + " - Level " + character.getLevel());
            characterLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            topCharactersPanel.add(characterLabel);
        }
        JScrollPane charactersScrollPane = new JScrollPane(topCharactersPanel);

        panel.setLayout(new GridLayout(1, 2));
        panel.add(playersScrollPane);
        panel.add(charactersScrollPane);
    }

    /**
     * Scales the specified image icon to the specified dimensions.
     * @param imageIcon The image icon to be scaled.
     * @return The scaled image icon.
     */
    private static ImageIcon scaleImage(ImageIcon imageIcon) {
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    /**
     * Creates a custom button with the specified text.
     *
     * @param text The text displayed on the button.
     * @return The created custom button.
     */
    public static JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(187, 125, 62));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    /**
     * Creates an authentication button with the specified text.
     *
     * @param text The text displayed on the button.
     * @return The created authentication button.
     */
    public static JButton createAuthentificationButtons(String text) {
        JButton button = new JButton(text);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    /**
     * Resets the specified panel by removing all components and updating its layout.
     *
     * @param panel The panel to be reset.
     */
    public static void resetCenterPanel(JPanel panel){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
    }
}
