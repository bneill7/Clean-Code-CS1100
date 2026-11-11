public class Painting {
    enum Medium {
        OIL,
        WATERCOLOR,
        GOUACHE,
        ACRYLIC
    }
    String title;
    String artist;
    double value;
    Medium medium;

    public Painting(String title, String artist, double value, Medium medium) {
        if (title == null || artist == null || title.isEmpty() || artist.isEmpty() || value < 0 || medium == null)
            throw new IllegalArgumentException("All values must be non-null, have content, and/or be non-negative.");
        this.title = title;
        this.artist = artist;
        this.value = value;
        this.medium = medium;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public double getValue() {
        return value;
    }
    public double getWidthCm() {
        return 50;
    }

    public Medium getMedium() { return medium; }
}
