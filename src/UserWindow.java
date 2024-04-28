import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserWindow extends JFrame {
    private Connection connection;
    private String saveUsername;

    public UserWindow(Connection connection, String saveUsername) {
        this.connection = connection;
        this.saveUsername = saveUsername;
        createUserPanel();
    }

    public JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // North Area with FlowLayout
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("Characters"));
        JButton messagesButton = new JButton("Messages");
        messagesButton.addActionListener(e -> Main.showUserMessages(saveUsername)); // It calls the Main showUserMessages with the logged username
        northPanel.add(messagesButton);
        northPanel.add(new JButton("Ranking"));
        northPanel.add(new JButton("Missions"));
        northPanel.add(new JButton("Regions"));
        northPanel.add(new JButton("Guild"));
        northPanel.add(new JButton("News"));
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
                centerPanel.add(Main.createCharacterPanel(i[0], i[2]+" Level: "+i[1], i[3]));
            }
        }
        panel.add(centerPanel, BorderLayout.CENTER);

        JButton supportButton = new JButton("Support");
        supportButton.addActionListener(e -> showSupportForm());
        southPanel.add(supportButton);

        southPanel.add(logoutButton);
        southPanel.setPreferredSize(new Dimension(0, 50));
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    private ArrayList<String[]> usersCharactersList (String username) {
        try {
            ArrayList<String[]> characters = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT P.NOMBRE,P.NIVEL,P.TIPO,P.IMAGEN FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?");
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
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, Main.getUserIdByUsername(saveUsername));
            ps.setString(2, message);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while sending the message to support: " + e.getMessage());
        }
    }
}
