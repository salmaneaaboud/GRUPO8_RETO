package Main.Domain;

/**
 * The News class represents a news item with a title, brief description, and an image path.
 */
public class News {
    private String title;
    private String briefDescription;
    private String imagePath;

    /**
     * Constructs a new news item with the specified title, brief description, and image path.
     *
     * @param title            the title of the news item
     * @param briefDescription a brief description of the news item
     * @param imagePath        the path to the image associated with the news item
     */
    public News(String title, String briefDescription, String imagePath) {
        this.title = title;
        this.briefDescription = briefDescription;
        this.imagePath = imagePath;
    }

    /**
     * Gets the title of the news item.
     *
     * @return the title of the news item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the brief description of the news item.
     *
     * @return the brief description of the news item
     */
    public String getBriefDescription() {
        return briefDescription;
    }

    /**
     * Gets the path to the image associated with the news item.
     *
     * @return the path to the image associated with the news item
     */
    public String getImagePath() {
        return imagePath;
    }
}
