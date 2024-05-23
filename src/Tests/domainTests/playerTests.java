package Tests.domainTests;

import Main.Domain.Player;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Player class.
 */
public class playerTests {

    /**
     * Test the equals method of the Player class.
     */
    @Test
    @Order(1)
    public void testEquals() {
        Player player1 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player2 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player3 = new Player(2, "Gold", 15, "Bob", "password123", "bob@example.com");

        assertEquals(player1, player2);
        assertNotEquals(player1, player3);
        assertNotEquals(player1, "Not a Player object");
        assertNotEquals(player1, null);
        assertEquals(player1, player1);
    }

    /**
     * Test the hashCode method of the Player class.
     */
    @Test
    @Order(2)
    public void testHashCode() {
        Player player1 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player2 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player3 = new Player(2, "Gold", 15, "Bob", "password123", "bob@example.com");

        assertEquals(player1.hashCode(), player2.hashCode());
        assertNotEquals(player1.hashCode(), player3.hashCode());
    }

    /**
     * Test the toString method of the Player class.
     */
    @Test
    @Order(3)
    public void testToString() {
        Player player = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        String expectedToString = "Player{idPlayer='1', ranking='Silver', level=10, name='Alice', password='password', email='alice@example.com'}";

        assertEquals(expectedToString, player.toString());
    }

    /**
     * Test the getters and setters of the Player class.
     */
    @Test
    @Order(4)
    public void testGettersAndSetters() {
        Player player = new Player("Alice", "password");

        player.setPlayerId(1);
        player.setRanking("Silver");
        player.setLevel(10);
        player.setEmail("alice@example.com");

        assertEquals(1, player.getPlayerId());
        assertEquals("Silver", player.getRanking());
        assertEquals(10, player.getLevel());
        assertEquals("Alice", player.getName());
        assertEquals("password", player.getPassword());
        assertEquals("alice@example.com", player.getEmail());
    }

    /**
     * Test the equals method with a different type.
     */
    @Test
    @Order(5)
    public void testEqualsWithDifferentType() {
        Player player = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        assertNotEquals(player, "Not a Player object");
    }

    /**
     * Test the equals method with null.
     */
    @Test
    @Order(6)
    public void testEqualsWithNull() {
        Player player = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        assertNotEquals(player, null);
    }

    /**
     * Test the equals method with itself.
     */
    @Test
    @Order(7)
    public void testEqualsWithItself() {
        Player player = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        assertEquals(player, player);
    }

    /**
     * Test the consistency of hashCode.
     */
    @Test
    @Order(8)
    public void testHashCodeConsistency() {
        Player player = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        int hashCode1 = player.hashCode();
        int hashCode2 = player.hashCode();
        assertEquals(hashCode1, hashCode2);
    }
}
