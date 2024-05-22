package Tests.domainTests;

import Main.Domain.Mission;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class missionTests {

    @Test
    @Order(1)
    public void testConstructorAndGetters() {
        Mission mission = new Mission(1, 101, "Defeat the dragon", "1000 gold", "Hard");

        assertEquals(1, mission.getMissionId());
        assertEquals(101, mission.getMatchId());
        assertEquals("Defeat the dragon", mission.getDescription());
        assertEquals("1000 gold", mission.getReward());
        assertEquals("Hard", mission.getDifficulty());
    }

    @Test
    @Order(2)
    public void testToString() {
        Mission mission = new Mission(1, 101, "Defeat the dragon", "1000 gold", "Hard");
        String expectedToString = "Mission{missionId=1, matchId=101, description='Defeat the dragon', reward='1000 gold', difficulty='Hard'}";

        assertEquals(expectedToString, mission.toString());
    }
}
