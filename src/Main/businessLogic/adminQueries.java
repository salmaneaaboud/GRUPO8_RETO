package Main.businessLogic;

import Main.Domain.Characters;
import Main.Domain.Guild;
import Main.Domain.Player;
import Main.Persistance.databaseQueries;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class adminQueries {
    public static JList<String> getUsersList(Connection conn) {
        if (conn == null) {
            throw new IllegalArgumentException("Connection cannot be null");
        }
        List<Player> users = databaseQueries.loadPlayersFromDatabase(conn);
        DefaultListModel<String> userListModel = new DefaultListModel<>();

        if (users!=null) {
            for (Player player : users) {
                userListModel.addElement(player.getName());
            }
            return new JList<>(userListModel);
        }

        return null;
    }

    public static void createCharactersListPanel(JList<String> list, String playerName, Connection conn) {
        DefaultListModel<String> charactersListModel = new DefaultListModel<>();
        list.setModel(charactersListModel);
        List<Characters> characters = databaseQueries.getCharactersByPlayerName(playerName,conn);

        if (characters != null) {
            for (Characters character : characters) {
                charactersListModel.addElement(character.getName());
            }
        }
    }

    public static StringBuilder loadUsersMessages(Connection conn) {
        return databaseQueries.getUserMessages(conn);
    }

    public static JList<String> getGuilds(Connection conn) {
        ArrayList<Guild> guilds = databaseQueries.getGuilds(conn);

        DefaultListModel<String> userListModel = new DefaultListModel<>();

        if (guilds != null) {
            for (Guild guild : guilds) {
                userListModel.addElement(guild.getGuildName());
            }
            return new JList<>(userListModel);
        }

        return null;
    }

    public static void createGuildPlayersListPanel(JList<String> charactersList, String guild, Connection conn) {
        ArrayList<Player> guildPlayers = databaseQueries.getGuildsPlayer(conn,guild);
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        charactersList.setModel(userListModel);

        if (guildPlayers != null) {
            for (Player player : guildPlayers) {
                userListModel.addElement(player.getName());
            }
            new JList<>(userListModel);
        }
    }

    public static ArrayList<Player> getTopPlayers(Connection conn) {
        return databaseQueries.getTopPlayers(conn);
    }

    public static ArrayList<Characters> getTopCharacters(Connection conn) {
        return databaseQueries.getTopCharacters(conn);
    }
}
