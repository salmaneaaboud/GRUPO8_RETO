package Main.businessLogic;

import Main.Domain.*;
import Exceptions.UserNotFoundException;
import Main.Persistance.databaseQueries;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The userQueries class provides methods for querying and interacting with user-related data.
 */
public class userQueries {

    /**
     * Displays the messages for a specific user.
     *
     * @param username the username of the user
     * @param conn     the connection to the database
     * @throws UserNotFoundException if the user is not found
     */
    public static void showUserMessages(String username, Connection conn) throws UserNotFoundException {
        Player player = getUserByUsername(username, conn);

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

    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user
     * @param conn     the connection to the database
     * @return the Player object representing the user, or null if not found
     */
    public static Player getUserByUsername(String username, Connection conn) {
        List<Player> players = databaseQueries.loadPlayersFromDatabase(conn);

        if (players != null) {
            for (Player player : players) {
                if (player.getName().equals(username)) {
                    return player;
                }
            }
        }

        return null;
    }

    /**
     * Retrieves the characters belonging to a specific user.
     *
     * @param username the username of the user
     * @param conn     the connection to the database
     * @return a list of Characters belonging to the user
     */
    public static List<Characters> usersCharacters(String username, Connection conn) {
        return databaseQueries.getCharactersByPlayerName(username, conn);
    }

    /**
     * Sends a message to support from a user.
     *
     * @param message  the message to send
     * @param username the username of the user sending the message
     * @param conn     the connection to the database
     * @throws UserNotFoundException if the user is not found
     */
    public static void sendMessageToSupport(String message, String username, Connection conn) throws UserNotFoundException {
        Player player = getUserByUsername(username, conn);
        if (player != null) {
            databaseQueries.insertUserMessage(player.getPlayerId(), message, conn);
        } else {
            throw new UserNotFoundException("User not found!");
        }
    }

    /**
     * Retrieves the guild of a specific user.
     *
     * @param username the username of the user
     * @param conn     the connection to the database
     * @return the Guild object representing the user's guild
     * @throws UserNotFoundException if the user is not found
     */
    public static Guild getUserGuild(String username, Connection conn) throws UserNotFoundException {
        Player player = getUserByUsername(username, conn);
        if (player != null) {
            return databaseQueries.getUsersGuild(player.getPlayerId(), conn);
        } else {
            throw new UserNotFoundException("User not found!");
        }
    }

    /**
     * Retrieves the players belonging to a specific guild.
     *
     * @param guild the name of the guild
     * @param conn  the connection to the database
     * @return a list of players belonging to the guild
     */
    public static ArrayList<Player> getGuildPlayers(String guild, Connection conn) {
        return new ArrayList<>(Objects.requireNonNull(databaseQueries.getGuildsPlayer(conn, guild)));
    }

    /**
     * Retrieves the latest missions available in the game.
     *
     * @param conn the connection to the database
     * @return a list of the latest missions
     */
    public static ArrayList<Mission> getLatestMissions(Connection conn) {
        return new ArrayList<>(Objects.requireNonNull(databaseQueries.getLatestMissions(conn)));
    }

    /**
     * Retrieves the regions in the game.
     *
     * @param conn the connection to the database
     * @return a list of regions
     */
    public static ArrayList<Region> getRegions(Connection conn) {
        return new ArrayList<>(Objects.requireNonNull(databaseQueries.getRegions(conn)));
    }
}
