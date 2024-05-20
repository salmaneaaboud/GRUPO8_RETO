package Main.businessLogic;

import Main.Domain.Characters;
import Main.Domain.Guild;
import Main.Domain.Mission;
import Main.Domain.Player;
import Exceptions.UserNotFoundException;
import Main.Persistance.databaseQueries;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static ArrayList<Player> getGuildPlayers(String guild, Connection conn) {
        return new ArrayList<>(Objects.requireNonNull(databaseQueries.getGuildsPlayer(conn, guild)));
    }

    public static ArrayList<Mission> getLatestMissions(Connection conn) {
        return new ArrayList<>(Objects.requireNonNull(databaseQueries.getLatestMissions(conn)));
    }
}
