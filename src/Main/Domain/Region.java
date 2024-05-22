package Main.Domain;

import java.util.Objects;

/**
 * The Region class represents a geographical region within a game, including details such as ID, name, description,
 * recommendation, and an associated image.
 */
public class Region {
    private int regionId;
    private String name;
    private String description;
    private String recommendation;
    private String image;

    /**
     * Constructs a new Region with the specified details.
     *
     * @param regionId       the unique identifier for the region
     * @param name           the name of the region
     * @param description    a description of the region
     * @param recommendation a recommendation related to the region
     * @param image          the path to an image associated with the region
     */
    public Region(int regionId, String name, String description, String recommendation, String image) {
        this.regionId = regionId;
        this.name = name;
        this.description = description;
        this.recommendation = recommendation;
        this.image = image;
    }

    /**
     * Gets the unique identifier of the region.
     *
     * @return the unique identifier of the region
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * Sets the unique identifier of the region.
     *
     * @param regionId the new unique identifier of the region
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    /**
     * Gets the name of the region.
     *
     * @return the name of the region
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the region.
     *
     * @param name the new name of the region
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the region.
     *
     * @return the description of the region
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the region.
     *
     * @param description the new description of the region
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the recommendation related to the region.
     *
     * @return the recommendation related to the region
     */
    public String getRecommendation() {
        return recommendation;
    }

    /**
     * Sets the recommendation related to the region.
     *
     * @param recommendation the new recommendation related to the region
     */
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    /**
     * Gets the path to the image associated with the region.
     *
     * @return the path to the image associated with the region
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the path to the image associated with the region.
     *
     * @param image the new path to the image associated with the region
     */
    public void setImage(String image) {
        this.image = image;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return regionId == region.regionId &&
                Objects.equals(name, region.name) &&
                Objects.equals(description, region.description) &&
                Objects.equals(recommendation, region.recommendation) &&
                Objects.equals(image, region.image);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(regionId, name, description, recommendation, image);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
