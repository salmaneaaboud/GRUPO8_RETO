package Tests.domainTests;

import Main.Domain.News;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the News class.
 */
public class newsTests {

    /**
     * Test the constructor and getters of the News class.
     */
    @Test
    @Order(1)
    public void testConstructorAndGetters() {
        News news = new News("Breaking News", "This is a brief description of the news.", "path/to/image.jpg");

        assertEquals("Breaking News", news.getTitle());
        assertEquals("This is a brief description of the news.", news.getBriefDescription());
        assertEquals("path/to/image.jpg", news.getImagePath());
    }
}
