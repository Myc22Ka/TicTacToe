package pl.polsl.lab1.krzysztof.gach.model;

/**
 * The Cell class represents a single cell on the game board.
 * Each cell holds a value that can be updated to reflect the 
 * current state of the game (e.g., player moves).
 * 
 * @author: Krzysztof Gach
 * @version 1.0
 */
public class Cell {
    private String value;

    /**
     * Constructs a new Cell instance with a default value of "□".
     */
    public Cell() {
        this.value = "□";  // Default empty cell
    }

    /**
     * Retrieves the current value of the cell.
     *
     * @return the value of the cell
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets a new value for the cell.
     *
     * @param value the new value to be set in the cell
     */
    public void setValue(String value) {
        this.value = value;
    }
}
