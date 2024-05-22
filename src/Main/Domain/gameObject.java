package Main.Domain;

import java.util.Objects;

public class gameObject {

    private String objectId;
    private String name;
    private String description;
    private int price;

    public gameObject(String objectId, String name, String description, int price) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof gameObject))
            return false;
        gameObject gameObject = (gameObject) o;
        return Objects.equals(getObjectId(), gameObject.getObjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectId());
    }

    @Override
    public String toString() {
        return "Object{" +
                "objectId='" + objectId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
