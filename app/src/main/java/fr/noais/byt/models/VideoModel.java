package fr.noais.byt.models;

public class VideoModel {
    private Long id;
    private String title;
    private String description;
    private String url;
    private Category category;
    private boolean isFavorite;

    // ***** CONSTRUCTORS ***** //

    public VideoModel(String title, String description, String url, Category category, boolean isFavorite) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.category = category;
        this.isFavorite = isFavorite;
    }

    public VideoModel() {
    }

    // ***** GETTERS ***** //

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    // ***** SETTERS ***** //


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
