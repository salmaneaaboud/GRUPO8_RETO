package Main.businessLogic;

import Main.Domain.Characters;
import Main.Domain.Guild;
import Main.Domain.Player;
import Main.Persistance.databaseQueries;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * The adminQueries class provides a set of static methods for querying and managing data related to players,
 * characters, and guilds within the application. It interacts with the database to retrieve and update information.
 */
public class adminQueries {

    /**
     * Retrieves a list of users from the database and returns it as a JList.
     *
     * @param conn the connection to the database
     * @return a JList containing the names of the users, or null if no users are found
     * @throws IllegalArgumentException if the connection is null
     */
    public static JList<String> getUsersList(Connection conn) {
        if (conn == null) {
            throw new IllegalArgumentException("Connection cannot be null");
        }
        List<Player> users = databaseQueries.loadPlayersFromDatabase(conn);
        DefaultListModel<String> userListModel = new DefaultListModel<>();

        if (users != null) {
            for (Player player : users) {
                userListModel.addElement(player.getName());
            }
            return new JList<>(userListModel);
        }

        return null;
    }

    /**
     * Creates and populates a panel with a list of characters for a given player.
     *
     * @param list       the JList to be populated
     * @param playerName the name of the player whose characters are to be listed
     * @param conn       the connection to the database
     */
    public static void createCharactersListPanel(JList<String> list, String playerName, Connection conn) {
        DefaultListModel<String> charactersListModel = new DefaultListModel<>();
        list.setModel(charactersListModel);
        List<Characters> characters = databaseQueries.getCharactersByPlayerName(playerName, conn);

        if (characters != null) {
            for (Characters character : characters) {
                charactersListModel.addElement(character.getName());
            }
        }
    }

    /**
     * Loads user messages from the database.
     *
     * @param conn the connection to the database
     * @return a StringBuilder containing the user messages
     */
    public static StringBuilder loadUsersMessages(Connection conn) {
        return databaseQueries.getUserMessages(conn);
    }

    /**
     * Retrieves a list of guilds from the database and returns it as a JList.
     *
     * @param conn the connection to the database
     * @return a JList containing the names of the guilds, or null if no guilds are found
     */
    public static JList<String> getGuilds(Connection conn) {
        ArrayList<Guild> guilds = databaseQueries.getGuilds(conn);
        DefaultListModel<String> guildListModel = new DefaultListModel<>();

        if (guilds != null) {
            for (Guild guild : guilds) {
                guildListModel.addElement(guild.getGuildName());
            }
            return new JList<>(guildListModel);
        }

        return null;
    }

    /**
     * Creates and populates a panel with a list of players in a given guild.
     *
     * @param playersList the JList to be populated
     * @param guildName   the name of the guild whose players are to be listed
     * @param conn        the connection to the database
     */
    public static void createGuildPlayersListPanel(JList<String> playersList, String guildName, Connection conn) {
        ArrayList<Player> guildPlayers = databaseQueries.getGuildsPlayer(conn, guildName);
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        playersList.setModel(userListModel);

        if (guildPlayers != null) {
            for (Player player : guildPlayers) {
                userListModel.addElement(player.getName());
            }
            new JList<>(userListModel);  // This line seems redundant and can be removed
        }
    }

    /**
     * Retrieves a list of top players from the database.
     *
     * @param conn the connection to the database
     * @return an ArrayList of top players
     */
    public static ArrayList<Player> getTopPlayers(Connection conn) {
        return databaseQueries.getTopPlayers(conn);
    }

    /**
     * Retrieves a list of top characters from the database.
     *
     * @param conn the connection to the database
     * @return an ArrayList of top characters
     */
    public static ArrayList<Characters> getTopCharacters(Connection conn) {
        return databaseQueries.getTopCharacters(conn);
    }
}
