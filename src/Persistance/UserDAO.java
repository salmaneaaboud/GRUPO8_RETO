package Persistance;

import Domain.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
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


}
