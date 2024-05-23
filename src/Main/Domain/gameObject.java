package Main.Domain;

import java.util.Objects;

/**
 * The gameObject class represents an object in a game with an ID, name, description, and price.
 */
public class gameObject {

    private String objectId;
    private String name;
    private String description;
    private int price;

    /**
     * Constructs a new game object with the specified ID, name, description, and price.
     *
     * @param objectId    the unique identifier for the game object
     * @param name        the name of the game object
     * @param description a description of the game object
     * @param price       the price of the game object
     */
    public gameObject(String objectId, String name, String description, int price) {
        this.objectId = objectId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Gets the unique identifier of the game object.
     *
     * @return the unique identifier of the game object
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Sets the unique identifier of the game object.
     *
     * @param objectId the new unique identifier of the game object
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * Gets the name of the game object.
     *
     * @return the name of the game object
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game object.
     *
     * @param name the new name of the game object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the game object.
     *
     * @return the description of the game object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the game object.
     *
     * @param description the new description of the game object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the game object.
     *
     * @return the price of the game object
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the game object.
     *
     * @param price the new price of the game object
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * The equals method implements an equivalence relation on non-null object references.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof gameObject))
            return false;
        gameObject gameObject = (gameObject) o;
        return Objects.equals(getObjectId(), gameObject.getObjectId());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getObjectId());
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
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
