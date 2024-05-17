package Persistance;

import Domain.Characters;
import Domain.Guild;
import Domain.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class databaseQueries {

    public static List<Characters> getCharactersByPlayerName(String playerName, Connection conn) {
        try {
            if (conn == null) {
                throw new IllegalArgumentException("Connection cannot be null");
            }
            String charactersQuery = "SELECT P.NOMBRE,P.NIVEL,P.TIPO,P.IMAGEN FROM PERSONAJE P INNER JOIN JUGADOR J ON P.IDJUGADOR = J.IDJUGADOR WHERE J.NOMBRE = ?";
            PreparedStatement ps = conn.prepareStatement(charactersQuery);
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();

            List<Characters> characters = new ArrayList<>();
            while (rs.next()) {
                Characters character = new Characters(
                        rs.getString("NOMBRE"),
                        rs.getInt("NIVEL"),
                        rs.getString("TIPO"),
                        "./photos/"+rs.getString("IMAGEN")
                );
                characters.add(character);
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
            System.out.println("Cannot load user messages");
        }
        return null;
    }

    public static List<Player> loadPlayersFromDatabase(Connection conn) {
        List<Player> players = new ArrayList<>();
        try {
            String playerQuery = "SELECT * FROM JUGADOR";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(playerQuery);

            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("IDJUGADOR"),
                        rs.getString("RANGO"),
                        rs.getInt("NIVEL"),
                        rs.getString("NOMBRE"),
                        rs.getString("CLAVE"),
                        rs.getString("EMAIL")
                );
                players.add(player);
            }
            return players;
        } catch (SQLException e) {
            System.out.println("Cannot load user messages");
        }
        return null;
    }

    public static StringBuilder showUserMessages(Player player, Connection conn){
        int playerId = player.getPlayerId();

        try {
            String query = "SELECT mensaje FROM mensajes_soporte WHERE IdJugador = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();

            StringBuilder messageBuilder = new StringBuilder();
            while (rs.next()) {
                messageBuilder.append(rs.getString("mensaje")).append("\n");
            }

            ps.close();
            rs.close();
            return messageBuilder;
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching user messages: " + e.getMessage());
        }

        return null;
    }

    public static void insertUserMessage(int userId, String message, Connection conn){
        try {
            String insertQuery = "INSERT INTO mensajes_soporte (IdJugador, mensaje) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(insertQuery);

            ps.setInt(1, userId);
            ps.setString(2, message);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while sending the message to support: " + e.getMessage());
        }
    }

    public static Guild getUsersGuild(int userId, Connection conn){
        try {
            String guildQuery = "SELECT * FROM GREMIO G INNER JOIN JUGADOR J ON G.IDGREMIO = J.IDGREMIO WHERE J.IDJUGADOR = ?";
            PreparedStatement ps = conn.prepareStatement(guildQuery);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Guild(rs.getInt("IDGREMIO"),rs.getString("NOMBRE"),rs.getString("TIPO"), rs.getInt("LIDER"), "./photos/guild/"+rs.getString("IMAGEN"));
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("An error occurred while sending the message to support: " + e.getMessage());
        }
        return null;
    }
}
