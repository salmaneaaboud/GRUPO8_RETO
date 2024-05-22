package Tests.persistanceTests;

import Main.Domain.*;
import Main.Persistance.databaseQueries;
import Main.Persistance.Conexion;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link Main.Persistance.databaseQueries} class.
 * It covers various functionalities and scenarios to ensure the reliability of the database operations.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class databaseQueriesTests {

    private static Connection conn;

    /**
     * Set up method to establish a database connection before running the tests.
     */
    @BeforeAll
    static void setup() {
        conn = new Conexion().conectar();
    }

    /**
     * Tear down method to close the database connection after running all tests.
     */
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

    /**
     * Test to verify the loading of players from the database.
     */
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

    /**
     * Test to verify the retrieval of characters by player name from the database.
     */
    @Test
    @Order(2)
    void testGetCharactersByPlayerName() {
        List<Characters> characters = databaseQueries.getCharactersByPlayerName("Firestorm99", conn);
        assertNotNull(characters, "The list of characters should not be null.");
        assertFalse(characters.isEmpty(), "The list of characters should not be empty.");

        Characters character = characters.get(0);
        assertEquals("Gimli", character.getName(), "Character name should match.");
    }

    /**
     * Test to verify the retrieval of user messages from the database.
     */
    @Test
    @Order(3)
    void testGetUserMessages() {
        databaseQueries.insertUserMessage(1, "Test message", conn);
        StringBuilder messages = databaseQueries.getUserMessages(conn);
        assertNotNull(messages, "Messages Stringbuilder should not be null");
    }

    /**
     * Test to verify the insertion of user messages into the database.
     */
    @Test
    @Order(4)
    void testInsertUserMessage() {
        databaseQueries.insertUserMessage(1, "Test message", conn);

        StringBuilder messages = databaseQueries.getUserMessages(conn);
        assertNotNull(messages, "Messages should not be null.");
        assertTrue(messages.toString().contains("Test message"), "Messages should contain the test message.");
    }

    /**
     * Test to verify the retrieval and display of user messages from the database.
     */
    @Test
    @Order(5)
    void testShowUserMessages() {
        Player player = new Player(1, "Novato", 1, "Player1", "pass1", "player1@example.com");

        databaseQueries.insertUserMessage(1, "Test message", conn);

        StringBuilder messages = databaseQueries.showUserMessages(player, conn);
        assertNotNull(messages, "Messages should not be null.");
        assertTrue(messages.toString().contains("Test message"), "Messages should contain the test message.");
    }

    /**
     * Test to verify the handling of null connection when retrieving characters by player name.
     */
    @Test
    @Order(6)
    void testGetCharactersByPlayerName_NullConnection() {
        assertThrows(IllegalArgumentException.class, () -> {
            databaseQueries.getCharactersByPlayerName("Firestorm99", null);
        });
    }

    /**
     * Test to verify the handling of SQLException when retrieving user messages.
     */
    @Test
    @Order(7)
    void testGetUserMessages_HandleSQLException() {
        assertThrows(RuntimeException.class, () -> {
            databaseQueries.getUserMessages(null);
        });
    }

    /**
     * Test to verify the handling of SQLException when inserting user messages.
     */
    @Test
    @Order(8)
    void testInsertUserMessage_HandleSQLException() {
        assertThrows(RuntimeException.class, () -> {
            databaseQueries.insertUserMessage(1, "Test message", null);
        });
    }

    /**
     * Test to verify the handling of SQLException when displaying user messages.
     */
    @Test
    @Order(9)
    void testShowUserMessages_HandleSQLException() {
        assertThrows(RuntimeException.class, () -> {
            Player player = new Player(1, "Novato", 1, "Player1", "pass1", "player1@example.com");
            databaseQueries.showUserMessages(player, null);
        });
    }

    /**
     * Test to verify the retrieval of guilds from the database.
     */
    @Test
    @Order(10)
    void testGetGuilds() {
        List<Guild> guilds = databaseQueries.getGuilds(conn);
        assertNotNull(guilds, "The list of guilds should not be null.");
        assertFalse(guilds.isEmpty(), "The list of guilds should not be empty.");

        Guild guild = guilds.get(0);
        assertEquals(1, guild.getGuildId(), "Guild ID should match.");
        assertEquals("The Knights of Light", guild.getGuildName(), "Guild name should match.");
    }

    /**
     * Test to verify the retrieval of a user's guild from the database.
     */
    @Test
    @Order(11)
    void testGetUsersGuild() {
        Guild guild = databaseQueries.getUsersGuild(1, conn);
        assertNotNull(guild, "The guild should not be null.");
        assertEquals(4, guild.getGuildId(), "Guild ID should match.");
        assertEquals("The Dark Renegades", guild.getGuildName(), "Guild name should match.");
    }

    /**
     * Test to verify the retrieval of top players from the database.
     */
    @Test
    @Order(13)
    void testGetTopPlayers() {
        List<Player> players = databaseQueries.getTopPlayers(conn);
        assertNotNull(players, "The list of players should not be null.");
        assertFalse(players.isEmpty(), "The list of players should not be empty.");

        Player player = players.get(0);
        assertTrue(player.getLevel() >= 1, "Player level should be greater than or equal to 1.");
    }

    /**
     * Test to verify the retrieval of top characters from the database.
     */
    @Test
    @Order(14)
    void testGetTopCharacters() {
        List<Characters> characters = databaseQueries.getTopCharacters(conn);
        assertNotNull(characters, "The list of characters should not be null.");
        assertFalse(characters.isEmpty(), "The list of characters should not be empty.");

        Characters character = characters.get(0);
        assertTrue(character.getLevel() >= 1, "Character level should be greater than or equal to 1.");
    }

    /**
     * Test to verify the retrieval of the latest missions from the database.
     */
    @Test
    @Order(15)
    void testGetLatestMissions() {
        List<Mission> missions = databaseQueries.getLatestMissions(conn);
        assertNotNull(missions, "The list of missions should not be null.");
        assertFalse(missions.isEmpty(), "The list of missions should not be empty.");

        Mission mission = missions.get(0);
        assertEquals("Easy", mission.getDifficulty(), "Mission difficulty should match.");
    }

    /**
     * Test to verify the retrieval of regions from the database.
     */
    @Test
    @Order(16)
    void testGetRegions() {
        List<Region> regions = databaseQueries.getRegions(conn);
        assertNotNull(regions, "The list of regions should not be null.");
        assertFalse(regions.isEmpty(), "The list of regions should not be empty.");

        Region region = regions.get(0);
        assertEquals(1, region.getRegionId(), "Region ID should match.");
        assertEquals("Dark forest", region.getName(), "Region name should match.");
    }
}
