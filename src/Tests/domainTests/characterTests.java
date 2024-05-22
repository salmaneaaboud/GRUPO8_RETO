package Tests.domainTests;

import Main.Domain.Characters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Characters class.
 */
public class characterTests {

    /**
     * Tests the getName method.
     */
    @Test
    public void testGetName() {
        Characters character = new Characters("Alice", 5, "Warrior", "warrior.jpg");

        String name = character.getName();

        Assertions.assertEquals("Alice", name);
    }

    /**
     * Tests the setName method.
     */
    @Test
    public void testSetName() {
        Characters character = new Characters("Bob", 10, "Mage", "mage.jpg");

        character.setName("Charlie");

        Assertions.assertEquals("Charlie", character.getName());
    }

    /**
     * Tests the getLevel method.
     */
    @Test
    public void testGetLevel() {
        Characters character = new Characters("David", 15, "Rogue", "rogue.jpg");

        int level = character.getLevel();

        Assertions.assertEquals(15, level);
    }

    /**
     * Tests the setLevel method.
     */
    @Test
    public void testSetLevel() {
        Characters character = new Characters("Eve", 20, "Archer", "archer.jpg");

        character.setLevel(25);

        Assertions.assertEquals(25, character.getLevel());
    }

    /**
     * Tests the getType method.
     */
    @Test
    public void testGetType() {
        Characters character = new Characters("Fiona", 30, "Paladin", "paladin.jpg");

        String type = character.getType();

        Assertions.assertEquals("Paladin", type);
    }

    /**
     * Tests the setType method.
     */
    @Test
    public void testSetType() {
        Characters character = new Characters("George", 35, "Sorcerer", "sorcerer.jpg");

        character.setType("Knight");

        Assertions.assertEquals("Knight", character.getType());
    }

    /**
     * Tests the getImage method.
     */
    @Test
    public void testGetImage() {
        Characters character = new Characters("Helen", 40, "Druid", "druid.jpg");

        String image = character.getImage();

        Assertions.assertEquals("druid.jpg", image);
    }

    /**
     * Tests the setImage method.
     */
    @Test
    public void testSetImage() {
        Characters character = new Characters("Ian", 45, "Barbarian", "barbarian.jpg");

        character.setImage("new_barbarian.jpg");

        Assertions.assertEquals("new_barbarian.jpg", character.getImage());
    }
}
