package pl.polsl.lab1.krzysztof.gach.model;

import lombok.*;

/**
 * The Cell class represents a single cell on the game board.
 * Each cell holds a value that can be updated to reflect the 
 * current state of the game (e.g., player moves).
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
@Getter
@Setter
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
}
