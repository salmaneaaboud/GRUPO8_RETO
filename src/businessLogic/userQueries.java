package businessLogic;

import Domain.Characters;
import Domain.Guild;
import Domain.Player;
import Exceptions.UserNotFoundException;
import Persistance.databaseQueries;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class userQueries {

    public static void showUserMessages(String username, Connection conn) throws UserNotFoundException {
        Player player = getUserByUsername(username,conn);

        if (player != null) {
            StringBuilder messageBuilder = databaseQueries.showUserMessages(player, conn);

            if (messageBuilder != null) {
                JOptionPane.showMessageDialog(null, messageBuilder.toString(), "Messages for " + username, JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "User not found: " + username, "Error", JOptionPane.ERROR_MESSAGE);
            throw new UserNotFoundException("User not found: " + username);
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

    public static List<Characters> usersCharacters (String username, Connection conn) {
        return databaseQueries.getCharactersByPlayerName(username,conn);
    }

    public static void sendMessageToSupport(String message, String username, Connection conn) throws UserNotFoundException{
        Player player = getUserByUsername(username,conn);
        if (player != null) {
            databaseQueries.insertUserMessage(player.getPlayerId(),message,conn);
        } else {
            throw new UserNotFoundException("User not found!");
        }
    }

    public static Guild getUserGuild(String username, Connection conn) throws UserNotFoundException {
        Player player = getUserByUsername(username,conn);
        if (player != null){
            return databaseQueries.getUsersGuild(player.getPlayerId(),conn);
        } else {
            throw new UserNotFoundException("User not found!");
        }
    }
}
