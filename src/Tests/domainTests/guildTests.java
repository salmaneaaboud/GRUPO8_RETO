package Tests.domainTests;

import Main.Domain.Guild;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class guildTests {

    @Test
    @Order(1)
    public void testEquals() {
        Guild guild1 = new Guild(1, "Knights", "PvE", 100, "knights.png");
        Guild guild2 = new Guild(1, "Knights", "PvE", 100, "knights.png");
        Guild guild3 = new Guild(2, "Mages", "PvP", 200, "mages.png");

        assertEquals(guild1, guild2);
        assertNotEquals(guild1, guild3);
    }

    @Test
    @Order(2)
    public void testHashCode() {
        Guild guild1 = new Guild(1, "Knights", "PvE", 100, "knights.png");
        Guild guild2 = new Guild(1, "Knights", "PvE", 100, "knights.png");
        Guild guild3 = new Guild(2, "Mages", "PvP", 200, "mages.png");

        assertEquals(guild1.hashCode(), guild2.hashCode());
        assertNotEquals(guild1.hashCode(), guild3.hashCode());
    }

    @Test
    @Order(3)
    public void testToString() {
        Guild guild = new Guild(1, "Knights", "PvE", 100, "knights.png");
        String expectedToString = "Guild{guildId=1, guildName='Knights', guildType='PvE', leaderId=100}";

        assertEquals(expectedToString, guild.toString());
    }

    @Test
    @Order(4)
    public void testGettersAndSetters() {
        Guild guild = new Guild(1, "Knights", "PvE", 100, "knights.png");

        guild.setGuildId(2);
        guild.setGuildName("Mages");
        guild.setGuildType("PvP");
        guild.setLeaderId(200);
        guild.setGuildImage("mages.png");

        assertEquals(2, guild.getGuildId());
        assertEquals("Mages", guild.getGuildName());
        assertEquals("PvP", guild.getGuildType());
        assertEquals(200, guild.getLeaderId());
        assertEquals("mages.png", guild.getGuildImage());
    }
}
