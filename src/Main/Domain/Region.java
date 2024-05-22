package Main.Domain;

import java.util.Objects;

public class Region {
    private int regionId;
    private String name;
    private String description;
    private String recommendation;
    private String image;

    public Region(int regionId, String name, String description, String recommendation, String image) {
        this.regionId = regionId;
        this.name = name;
        this.description = description;
        this.recommendation = recommendation;
        this.image = image;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
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

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return regionId == region.regionId && Objects.equals(name, region.name) && Objects.equals(description, region.description) && Objects.equals(recommendation, region.recommendation) && Objects.equals(image, region.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionId, name, description, recommendation, image);
    }

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
