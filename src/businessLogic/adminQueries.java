package businessLogic;

import Domain.Character;
import Domain.Player;
import Persistance.databaseQueries;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class adminQueries {
    public static JList<String> createUserListPanel(Connection conn) {
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
        List<Character> characters = databaseQueries.getCharactersByPlayerName(playerName,conn);

        if (characters != null) {
            for (Character character : characters) {
                charactersListModel.addElement(character.getName());
            }
        }
    }

    public static StringBuilder loadUsersMessages(Connection conn) {
        return databaseQueries.getUserMessages(conn);
    }
}
