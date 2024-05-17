package Presentation;

import Persistance.Conexion;
import businessLogic.Authenticator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Main extends JFrame {
    public static Connection conn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Presentation.Main Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        conn = new Conexion().conectar();
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Characters"));
        northPanel.add(new JButton("Ranking"));
        northPanel.add(new JButton("Missions"));
        northPanel.add(new JButton("Regions"));
        northPanel.add(new JButton("News"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.LINE_AXIS));
        JButton loginButton = new JButton("Log in");

        loginButton.addActionListener(e -> Authenticator.showLoginForm(frame, conn));

        southPanel.add(loginButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        mainContainer.add(southPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 5, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        centerPanel.add(createCharacterPanel("Warrior", "Strong and resilient", "./photos/warrior.jfif"));
        centerPanel.add(createCharacterPanel("Archer", "Precise and lethal", "./photos/archer.jfif"));
        centerPanel.add(createCharacterPanel("Wizard", "Master of magical power", "./photos/wizard.jfif"));
        centerPanel.add(createCharacterPanel("Summoner", "Creature controller", "./photos/summoner.jfif"));
        centerPanel.add(createCharacterPanel("Hunter", "Voracious hunter", "./photos/granyhunter.png"));
        centerPanel.add(createCharacterPanel("Tank", "Team shield", "./photos/tank.jfif"));
        centerPanel.add(createCharacterPanel("Slayer", "Unstoppable berserker", "./photos/milfslayer.jfif"));
        centerPanel.add(createCharacterPanel("Assassin", "Swift and stealthy", "./photos/assassin.jfif"));
        centerPanel.add(createCharacterPanel("Paladin", "Team support", "./photos/paladin.jfif"));
        centerPanel.add(createCharacterPanel("Coming Soon", "Exciting things coming", "./photos/new.jfif"));

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static JPanel createCharacterPanel(String name, String description, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(icon);
        addMouseHoverEffect(imageLabel,icon);
        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(name);
        JLabel descriptionLabel = new JLabel(description);
        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);

        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }

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
}
