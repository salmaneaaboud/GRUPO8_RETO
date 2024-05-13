package Presentation;

import Domain.Player;
import Persistance.UserDAO;
import businessLogic.userQueries;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import static businessLogic.userQueries.getUserByUsername;

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
        northPanel.add(createButtonWithListener("Messages", e -> userQueries.showUserMessages(saveUsername,conn))); // It calls the Presentation.Main showUserMessages with the logged username
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
        ArrayList<String[]> characters = usersCharactersList(saveUsername);
        if (characters != null) {
            for (String[] i : characters) {
                centerPanel.add(Main.createCharacterPanel(i[0], i[2] + " Level: " + i[1], i[3]));
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

    private ArrayList<String[]> usersCharactersList (String username) {
        try {
            ArrayList<String[]> characters = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement("SELECT P.NOMBRE,P.NIVEL,P.TIPO,P.IMAGEN FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] characterInfo = new String[4];
                characterInfo[0] = rs.getString("NOMBRE");
                characterInfo[1] = String.valueOf(rs.getInt("NIVEL"));
                characterInfo[2] = rs.getString("TIPO");
                characterInfo[3] = "./photos/"+rs.getString("IMAGEN");
                characters.add(characterInfo);
            }

            return characters;
        } catch (SQLException e) {
            System.out.println("Error found while loading the characters!");
        }
        return null;
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
                sendMessageToSupport(message);
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

    private void sendMessageToSupport(String message) {
        try {
            String insertQuery = "INSERT INTO mensajes_soporte (IdJugador, mensaje) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            Optional<Player> user = userQueries.getUserByUsername(saveUsername,conn);
            int userId = user.get().getPlayerId();
            ps.setInt(1, userId);
            ps.setString(2, message);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while sending the message to support: " + e.getMessage());
        }
    }
}
