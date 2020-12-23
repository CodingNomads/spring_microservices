package platofrom.codingnomads.co.moviecatalogservice.model;

public class MovieInfoSummary {

    public MovieInfoSummary() {

    }

    public MovieInfoSummary(String id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
    }

    private String id;
    private String title;
    private String overview;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
