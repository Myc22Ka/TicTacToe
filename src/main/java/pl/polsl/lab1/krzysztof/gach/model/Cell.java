package pl.polsl.lab1.krzysztof.gach.model;

/**
 * The Cell class represents a single cell on the game board.
 * Each cell holds a value that can be updated to reflect the 
 * current state of the game (e.g., player moves).
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public record Cell(String value) {
    public static final String DEFAULT_VALUE = "";

    /**
     * Constructs a new Cell instance with a default value.
     */
    public Cell() {
        this(DEFAULT_VALUE);  // Default empty cell
    }

    /**
     * Reset cell value to default value
     */
    public Cell reset() {
        return new Cell(DEFAULT_VALUE);
    }
}