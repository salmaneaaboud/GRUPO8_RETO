package Main.Domain;

/**
 * The Characters class represents a character in a game with a name, level, type, and image.
 */
public class Characters {

    private String name;
    private int level;
    private String type;
    private String image;

    /**
     * Constructs a new character with the specified name, level, type, and image.
     *
     * @param name  the name of the character
     * @param level the level of the character
     * @param type  the type of the character (e.g., warrior, mage)
     * @param image the image representing the character
     */
    public Characters(String name, int level, String type, String image) {
        this.name = name;
        this.level = level;
        this.type = type;
        this.image = image;
    }

    /**
     * Gets the name of the character.
     *
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the character.
     *
     * @param name the new name of the character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the level of the character.
     *
     * @return the level of the character
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the character.
     *
     * @param level the new level of the character
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the type of the character.
     *
     * @return the type of the character
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the character.
     *
     * @param type the new type of the character
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the image representing the character.
     *
     * @return the image representing the character
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image representing the character.
     *
     * @param image the new image representing the character
     */
    public void setImage(String image) {
        this.image = image;
    }
}
