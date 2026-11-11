import java.util.ArrayList;
import java.util.List;

public class PaletteReader {
    static String filename;

    public static void setFilename(String newFilename) {
        filename = newFilename;
    }

    public static void open() {
        // begin reading
    }

    public static void close() {
        // stop reading
    }

    public static List<String> readAllColors() {
        // reads colors from file and returns a list of colors.
        return new ArrayList<>();
    }
}
