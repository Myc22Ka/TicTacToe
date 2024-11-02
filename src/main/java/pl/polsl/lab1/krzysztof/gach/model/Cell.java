package pl.polsl.lab1.krzysztof.gach.model;

/**
 * The Cell class represents a single cell on the game board.
 * Each cell holds a value that can be updated to reflect the 
 * current state of the game (e.g., player moves).
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public class Cell {
    private String value; // String value represent player's markers
    private final String DEFAULT_VALUE = ""; // String value represent default value of cell

    /**
     * Constructs a new Cell instance with a default value.
     */
    public Cell() {
        this.value = DEFAULT_VALUE;  // Default empty cell
    }
    
    /**
     * Reset cell value to default value
     */
    public void reset(){
        setValue(DEFAULT_VALUE);   
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
