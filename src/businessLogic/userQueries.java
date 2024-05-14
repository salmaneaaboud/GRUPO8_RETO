package businessLogic;

import Domain.Character;
import Domain.Player;
import Persistance.databaseQueries;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class userQueries {

    public static void showUserMessages(String username, Connection conn) {
        Optional<Player> optionalPlayer = getUserByUsername(username,conn);


        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            StringBuilder messageBuilder = databaseQueries.showUserMessages(player, conn);

            if (messageBuilder != null) {
                JOptionPane.showMessageDialog(null, messageBuilder.toString(), "Messages for " + username, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "User not found: " + username, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Optional getUserByUsername(String username, Connection conn){
        List<Player> players = databaseQueries.loadPlayersFromDatabase(conn);

        if (players != null) {
            return players.stream()
                    .filter(player -> player.getName().equals(username))
                    .findFirst();
        }

        return Optional.empty();
    }

    public static List<Character> usersCharacters (String username, Connection conn) {
        return databaseQueries.getCharactersByPlayerName(username,conn);
    }

    public static void sendMessageToSupport(String message, String username, Connection conn) {
        databaseQueries.insertUserMessage(message,username,conn);
    }
}
