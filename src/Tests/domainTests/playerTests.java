package Tests.domainTests;

import Domain.Player;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class playerTests {
    @Test
    @Order(1)
    public void testEquals() {
        Player player1 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player2 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player3 = new Player(2, "Gold", 15, "Bob", "password123", "bob@example.com");

        assertEquals(player1, player2);
        assertNotEquals(player1, player3);
    }

    @Test
    @Order(2)
    public void testHashCode() {
        Player player1 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player2 = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        Player player3 = new Player(2, "Gold", 15, "Bob", "password123", "bob@example.com");

        assertEquals(player1.hashCode(), player2.hashCode());
        assertNotEquals(player1.hashCode(), player3.hashCode());
    }

    @Test
    @Order(3)
    public void testToString() {
        Player player = new Player(1, "Silver", 10, "Alice", "password", "alice@example.com");
        String expectedToString = "Player{idPlayer='1', ranking='Silver', level=10, name='Alice', password='password', email='alice@example.com'}";

        assertEquals(expectedToString, player.toString());
    }

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
}
