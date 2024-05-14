package businessLogic;

import Domain.Player;
import Persistance.UserDAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class userQueries {

    public static void showUserMessages(String username, Connection conn) {
        Optional<Player> optionalPlayer = getUserByUsername(username,conn);


        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            StringBuilder messageBuilder = UserDAO.showUserMessages(player, conn);

            if (messageBuilder != null) {
                JOptionPane.showMessageDialog(null, messageBuilder.toString(), "Messages for " + username, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "User not found: " + username, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Optional getUserByUsername(String username, Connection conn){
        List<Player> players = UserDAO.loadPlayersFromDatabase(conn);

        return players.stream()
                .filter(player -> player.getName().equals(username))
                .findFirst();
    }
}
