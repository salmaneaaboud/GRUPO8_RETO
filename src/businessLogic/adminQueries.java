package businessLogic;

import javax.swing.*;
import java.sql.*;

public class adminQueries {
    public static JList<String> createUserListPanel(Connection conn) {
        try {
            String userQuery = "SELECT NOMBRE FROM JUGADOR";
            Statement st = conn.createStatement();
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
    public static void createCharactersListPanel(JList<String> list, String playerName, Connection conn) {
        try {
            String charactersQuery = "SELECT P.NOMBRE FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?";
            PreparedStatement ps = conn.prepareStatement(charactersQuery);
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

    public static void showSupportMessages(Connection conn) {
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
            Statement statement = conn.createStatement();
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
