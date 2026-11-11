public class Sculpture {
    enum Material {
        STONE,
        WOOD,
        PLASTER
    }
    String title;
    String artist;
    double value;
    Material mat;

    public Sculpture(String title, String artist, double value, Material mat) {
        if (title == null || artist == null || title.isEmpty() || artist.isEmpty() || value < 0 || mat == null)
            throw new IllegalArgumentException("All values must be non-null, have content, and/or be non-negative.");
        this.title = title;
        this.artist = artist;
        this.value = value;
        this.mat = mat;
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

    public Material getMaterial() { return mat; }
}
