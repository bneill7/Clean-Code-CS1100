import java.util.*;

/**
 * This class is home to lots of miscellaneous functions. See if you can clean them up!
 */
public class ArtStoreUtil {
    Set<Painting> paintings = new HashSet<>(); // sets .contains() runs in O(1), but you cannot access arbitrary elems.
    Map<Paint, Integer> paintStock = new HashMap<>(); // maps Paint -> Integer. (i.e. paintStock.get(somePaint) -> stock of somePaint)

    /**
     * This method opens the exhibit by calculating the insured value of its property, printing and creating the report,
     * and sending the report to a curator. It also saves information to database.
     *
     * To clean it, think about understanbility. How much is this function doing? Are related lines of code close to each
     * other?
     * @difficulty MEDIUM
     * @param paintings Contains the stock of the museums possessions
     * @param curatorEmail Email address to which the repoty is sent.
     */
    void openExhibit(List<Painting> paintings, String curatorEmail) {
        double insuredValue = 0;
        String report = "";
        for (Painting p : paintings) insuredValue += p.getValue();
        if (curatorEmail == null || !curatorEmail.contains("@")) throw new IllegalArgumentException();
        report = "Insured value: " + insuredValue;
        // send email.
        // save to database
        System.out.println(report);
    }

    /**
     * Calculates the framing fee for a painting. There are two different pricing tiers based on painting width.
     *
     * It's hard to keep all of these if conditions in mind. How could the structure change to make the branching easier
     * to think about?
     * @diffuculty EASY
     * @param p painting to calculate the framing fee of.
     * @return price to frame, 0 for invalid inputs
     */
    double framingFee(Painting p) {
        if (p != null) {
            if (p.getWidthCm() > 0) {
                if (p.getWidthCm() > 100) {
                    return 45.0;
                } else {
                    return 20.0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * Loads a list of colors from a file.
     *
     * There is a problem with understandability and maintainability here... Is PaletteReader's interface appropriate?
     * How can PaletteReader be changed so that interfacing is simpler than the underlying implementation?
     * @difficulty MEDIUM
     * @param filename name of file where pallette is stored. (Assume this input is always valid.)
     * @return all the colors found in the file at filename.
     */
    List<String> loadPalette(String filename) {
        PaletteReader.setFilename(filename);
        PaletteReader.open();
        List<String> returnList = PaletteReader.readAllColors();
        PaletteReader.close();
        return returnList;
    }

    /**
     * Attempts to load a painting from a file.
     *
     * There is a scalability and maintainability problem here. How can you fix the error? What happens if an error happens?
     * Is this a good idea?
     * @difficulty EASY
     * @param filename filename where the painting is stored
     * @return the painting information stored at the file.
     */
    Painting loadPainting(String filename) {
        try {
            return parse(readFile(filename));
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong :\\");
        }
    }

    /**
     * IGNORE ME ~~ This is not a problem (it is here so the program compiles). Pretend it is implemented correctly.
     * Do note how this helper function is beside the function it is used in.
     * @param fileContent N/A
     * @return N/A
     */
    private Painting parse(String fileContent) {
        // turns string into painting object
        return new Painting("DEFAULT_TITLE", "DEFAULT_ARTIST", 0, Painting.Medium.OIL);
    }

    /**
     * IGNORE ME ~~ This is not a problem (it is here so the program compiles). Pretend it is implemented correctly.
     * Do note how this helper function is beside the function it is used in.
     * @param fileName N/A
     * @return N/A
     */
    private String readFile(String fileName) {
        // reads file, throws error if it does not exist
        return "";
    }

    /**
     * How could the understandability be improved?
     *
     * @difficulty EASY
     * @param size new brush size
     */
    void setBrushSize(int size) {
        if (size > 0) System.out.println("Sets brush size."); // sets brush size.
        else throw new IllegalArgumentException("Invalid");
    }

    /**
     * Restores all paint stocks back to amount, regardless of existing stock. Use full opacity paint.
     *
     * We can see a problem b/c of the dirty code in this example. The developer made a typo ("RDE" instead of "RED").
     * Since {@code Paint}'s {@code color} attribute is strictly confined, how could we change the {@code Paint} constructor
     * and {@code color} data type to disallow illegal states (like "RDE")? Could your implementation be used to make this
     * method less full of duplicates?
     * @param amount amount to restock everything to.
     * @difficulty HARD
     */
    void restockPrimaryColors(int amount) {
        // there are illegal states that will compile!
        paintStock.put(new Paint("RDE", 100), amount);
        paintStock.put(new Paint("ORANGE", 1000), amount);
        paintStock.put(new Paint("YELLOW", 100), amount);
        paintStock.put(new Paint("GREEN", 100), amount);
        paintStock.put(new Paint("BLUE", 1000), amount);
        paintStock.put(new Paint("INDIGO", 100), amount);
        paintStock.put(new Paint("VIOLET", 100), amount);
    }

    /**
     * Constructs the label text for a given painting.
     *
     * {@code paintingLabel} and {@code sculptureLabel} are somewhat redundant. How could you leverage the similarities
     * between {@code Painting} and {@code Sculpture} to simplify this program (think about Object-Oriented Programming).
     * Furthermore, where should this labeling function be?
     * @difficulty Hard
     * @param p painting to label
     * @return label text for the given painting
     */
    String paintingLabel(Painting p) {
        if (p == null) throw new IllegalArgumentException("Painting cannot be null");
        return p.getArtist().toUpperCase() + ": " + p.getTitle() + "( " + p.getMedium() + ")";
    }
    String sculptureLabel(Sculpture s) {
        if (s == null) throw new IllegalArgumentException("Sculpture cannot be null.");
        return s.getArtist().toUpperCase() + ": " + s.getTitle() + " (" + s.getMaterial() + ")";
    }

    /**
     * Generates a frame style code.
     *
     * How could this function be refactored to make it more maintainable and understandable?
     *
     * @difficulty MEDIUM
     * @param index position in the collection
     * @param widthCm width of the painting
     * @return styling code (format: "S-Gold")
     */
    static String frameStyle(int index, int widthCm) {
        return (index & 1) == 0 ? (widthCm < 30 ? "S-gold" : widthCm < 60 ? "M-gold" : "L-gold")
                : (widthCm < 30 ? "S-silver" : widthCm < 60 ? "M-silver" : "L-silver");
    }
}
