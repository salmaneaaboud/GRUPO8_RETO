package businessLogic;

import Domain.Character;
import Domain.Player;
import Persistance.databaseQueries;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class userQueries {

    public static void showUserMessages(String username, Connection conn) {
        Player player = getUserByUsername(username,conn);

        if (player != null) {
            StringBuilder messageBuilder = databaseQueries.showUserMessages(player, conn);

            if (messageBuilder != null) {
                JOptionPane.showMessageDialog(null, messageBuilder.toString(), "Messages for " + username, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "User not found: " + username, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Player getUserByUsername(String username, Connection conn){
        List<Player> players = databaseQueries.loadPlayersFromDatabase(conn);

        if (players != null) {
            for (Player player : players) {
                if (player.getName().equals(username)){
                    return player;
                }
            }
        }

        return null;
    }

    public static List<Character> usersCharacters (String username, Connection conn) {
        return databaseQueries.getCharactersByPlayerName(username,conn);
    }

    public static void sendMessageToSupport(String message, String username, Connection conn) {
        Player player = getUserByUsername(username,conn);
        if (player != null) {
            databaseQueries.insertUserMessage(player.getPlayerId(),message,conn);
        }
    }
}
