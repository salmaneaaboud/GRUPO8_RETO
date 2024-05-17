package Tests.domainTests;

import Domain.Characters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class characterTests {
    @Test
    public void testGetName() {
        Characters character = new Characters("Alice", 5, "Warrior", "warrior.jpg");

        String name = character.getName();

        Assertions.assertEquals("Alice", name);
    }

    @Test
    public void testSetName() {
        Characters character = new Characters("Bob", 10, "Mage", "mage.jpg");

        character.setName("Charlie");

        Assertions.assertEquals("Charlie", character.getName());
    }

    @Test
    public void testGetLevel() {
        Characters character = new Characters("David", 15, "Rogue", "rogue.jpg");

        int level = character.getLevel();

        Assertions.assertEquals(15, level);
    }

    @Test
    public void testSetLevel() {
        Characters character = new Characters("Eve", 20, "Archer", "archer.jpg");

        character.setLevel(25);

        Assertions.assertEquals(25, character.getLevel());
    }

    @Test
    public void testGetType() {
        Characters character = new Characters("Fiona", 30, "Paladin", "paladin.jpg");

        String type = character.getType();

        Assertions.assertEquals("Paladin", type);
    }

    @Test
    public void testSetType() {
        Characters character = new Characters("George", 35, "Sorcerer", "sorcerer.jpg");

        character.setType("Knight");

        Assertions.assertEquals("Knight", character.getType());
    }

    @Test
    public void testGetImage() {
        Characters character = new Characters("Helen", 40, "Druid", "druid.jpg");

        String image = character.getImage();

        Assertions.assertEquals("druid.jpg", image);
    }

    @Test
    public void testSetImage() {
        Characters character = new Characters("Ian", 45, "Barbarian", "barbarian.jpg");

        character.setImage("new_barbarian.jpg");

        Assertions.assertEquals("new_barbarian.jpg", character.getImage());
    }
}
