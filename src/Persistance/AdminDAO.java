package Persistance;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static List<String> getCharactersByPlayerName(String playerName, Connection conn) {
        try {
            List<String> characters = new ArrayList<>();
            String charactersQuery = "SELECT P.NOMBRE FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?";
            PreparedStatement ps = conn.prepareStatement(charactersQuery);
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                characters.add(rs.getString("NOMBRE"));
            }

            ps.close();
            rs.close();
            return characters;
        } catch (SQLException e) {
            System.out.println("An error occurred while loading the characters: " + e.getMessage());
        }
        return null;
    }

    public static StringBuilder getUserMessages(Connection conn){
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
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
