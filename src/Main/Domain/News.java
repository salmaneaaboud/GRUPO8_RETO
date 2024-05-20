package Main.Domain;

public class News {
    private String title;
    private String briefDescription;
    private String imagePath;

    public News(String title, String briefDescription, String imagePath) {
        this.title = title;
        this.briefDescription = briefDescription;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public String getImagePath() {
        return imagePath;
    }
}