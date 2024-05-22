package Tests.domainTests;

import Main.Domain.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class regionTests {
    @Test
    @Order(1)
    public void testEquals() {
        Region region1 = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        Region region2 = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        Region region3 = new Region(2, "South", "Warm region", "Visit during summer", "south.jpg");

        Assertions.assertEquals(region1, region2);
        Assertions.assertNotEquals(region1, region3);
        Assertions.assertNotEquals(region1, "Not a Region object");
        Assertions.assertNotEquals(region1, null);
        Assertions.assertEquals(region1, region1);
    }

    @Test
    @Order(2)
    public void testHashCode() {
        Region region1 = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        Region region2 = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        Region region3 = new Region(2, "South", "Warm region", "Visit during summer", "south.jpg");

        Assertions.assertEquals(region1.hashCode(), region2.hashCode());
        Assertions.assertNotEquals(region1.hashCode(), region3.hashCode());
    }

    @Test
    @Order(3)
    public void testToString() {
        Region region = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        String expectedToString = "Region{regionId=1, name='North', description='Cold region', recommendation='Visit during winter', image='north.jpg'}";

        Assertions.assertEquals(expectedToString, region.toString());
    }

    @Test
    @Order(4)
    public void testGettersAndSetters() {
        Region region = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");

        region.setRegionId(2);
        region.setName("South");
        region.setDescription("Warm region");
        region.setRecommendation("Visit during summer");
        region.setImage("south.jpg");

        Assertions.assertEquals(2, region.getRegionId());
        Assertions.assertEquals("South", region.getName());
        Assertions.assertEquals("Warm region", region.getDescription());
        Assertions.assertEquals("Visit during summer", region.getRecommendation());
        Assertions.assertEquals("south.jpg", region.getImage());
    }

    @Test
    @Order(5)
    public void testEqualsWithDifferentType() {
        Region region = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        Assertions.assertNotEquals(region, "Not a Region object");
    }

    @Test
    @Order(6)
    public void testEqualsWithNull() {
        Region region = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        Assertions.assertNotEquals(region, null);
    }

    @Test
    @Order(7)
    public void testEqualsWithItself() {
        Region region = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        Assertions.assertEquals(region, region);
    }

    @Test
    @Order(8)
    public void testHashCodeConsistency() {
        Region region = new Region(1, "North", "Cold region", "Visit during winter", "north.jpg");
        int hashCode1 = region.hashCode();
        int hashCode2 = region.hashCode();
        Assertions.assertEquals(hashCode1, hashCode2);
    }
}
