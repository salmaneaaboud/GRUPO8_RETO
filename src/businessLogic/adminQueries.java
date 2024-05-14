package businessLogic;

import Domain.Player;
import Persistance.AdminDAO;
import Persistance.UserDAO;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class adminQueries {
    public static JList<String> createUserListPanel(Connection conn) {
        List<Player> users = UserDAO.loadPlayersFromDatabase(conn);
        DefaultListModel<String> userListModel = new DefaultListModel<>();

        if (users!=null) {
            for (Player player : users) {
                userListModel.addElement(player.getName());
            }
            return new JList<>(userListModel);
        }

        return null;
    }

    // Method to create a JList containing the list of characters from the database
    public static void createCharactersListPanel(JList<String> list, String playerName, Connection conn) {
        DefaultListModel<String> charactersListModel = new DefaultListModel<>();
        list.setModel(charactersListModel);
        List<String> characters = AdminDAO.getCharactersByPlayerName(playerName,conn);

        for (String character : characters) {
            charactersListModel.addElement(character);
        }
    }

    public static StringBuilder loadUsersMessages(Connection conn) {
        // Query stored messages and display them in the JTextArea
        StringBuilder messages = AdminDAO.getUserMessages(conn);

        return messages;
    }
}
