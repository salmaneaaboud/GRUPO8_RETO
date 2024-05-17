package Tests.domainTests;

import Domain.gameObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class gameObjectTests {

    @Test
    @Order(1)
    public void testEquals() {
        gameObject object1 = new gameObject("1", "Sword", "A sharp blade", 100);
        gameObject object2 = new gameObject("1", "Sword", "A sharp blade", 100);
        gameObject object3 = new gameObject("2", "Shield", "A sturdy shield", 150);

        assertEquals(object1, object2);
        assertNotEquals(object1, object3);
    }

    @Test
    @Order(2)
    public void testHashCode() {
        gameObject object1 = new gameObject("1", "Sword", "A sharp blade", 100);
        gameObject object2 = new gameObject("1", "Sword", "A sharp blade", 100);
        gameObject object3 = new gameObject("2", "Shield", "A sturdy shield", 150);

        assertEquals(object1.hashCode(), object2.hashCode());
        assertNotEquals(object1.hashCode(), object3.hashCode());
    }

    @Test
    @Order(3)
    public void testToString() {
        gameObject object = new gameObject("1", "Sword", "A sharp blade", 100);
        String expectedToString = "Object{objectId='1', name='Sword', description='A sharp blade', price=100}";

        assertEquals(expectedToString, object.toString());
    }

    @Test
    @Order(4)
    public void testGettersAndSetters() {
        gameObject object = new gameObject("1", "Sword", "A sharp blade", 100);

        object.setObjectId("2");
        object.setName("Shield");
        object.setDescription("A sturdy shield");
        object.setPrice(150);

        assertEquals("2", object.getObjectId());
        assertEquals("Shield", object.getName());
        assertEquals("A sturdy shield", object.getDescription());
        assertEquals(150, object.getPrice());
    }
}
