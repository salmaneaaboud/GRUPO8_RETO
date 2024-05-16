package Tests;

import Domain.Character;
import Domain.Player;
import Persistance.databaseQueries;
import Persistance.Conexion;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class databaseQueriesTests {

    private static Connection conn;

    @BeforeAll
    static void setup() {
        conn = new Conexion().conectar();
    }

    @AfterAll
    static void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            fail("Failed to close connection to database: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void testLoadPlayersFromDatabase() {
        List<Player> players = databaseQueries.loadPlayersFromDatabase(conn);
        assertNotNull(players, "The list of players should not be null.");
        assertFalse(players.isEmpty(), "The list of players should not be empty.");

        Player player = players.get(0);
        assertEquals(1, player.getPlayerId(), "Player ID should match.");
        assertEquals("ShadowWarrior87", player.getName(), "Player name should match.");
    }

    @Test
    @Order(2)
    void testGetCharactersByPlayerName() {
        List<Character> characters = databaseQueries.getCharactersByPlayerName("Firestorm99", conn);
        assertNotNull(characters, "The list of characters should not be null.");
        assertFalse(characters.isEmpty(), "The list of characters should not be empty.");

        Character character = characters.get(0);
        assertEquals("Gimli", character.getName(), "Character name should match.");
    }

    @Test
    @Order(3)
    void testGetUserMessages() {
        databaseQueries.insertUserMessage(1, "Test message", conn);
        StringBuilder messages = databaseQueries.getUserMessages(conn);
        assertNotNull(messages,"Messages Stringbuilder should not be null");
    }

    @Test
    @Order(4)
    void testInsertUserMessage() {
        databaseQueries.insertUserMessage(1, "Test message", conn);

        StringBuilder messages = databaseQueries.getUserMessages(conn);
        assertNotNull(messages, "Messages should not be null.");
        assertTrue(messages.toString().contains("Test message"), "Messages should contain the test message.");
    }

    @Test
    @Order(5)
    void testShowUserMessages() {
        Player player = new Player(1, "Novato", 1, "Player1", "pass1", "player1@example.com");

        databaseQueries.insertUserMessage(1, "Test message", conn);

        StringBuilder messages = databaseQueries.showUserMessages(player, conn);
        assertNotNull(messages, "Messages should not be null.");
        assertTrue(messages.toString().contains("Test message"), "Messages should contain the test message.");
    }

}
