package pl.polsl.lab1.krzysztof.gach.model;

/**
 * The Cell class represents a single cell on the game board.
 * Each cell holds a value that can be updated to reflect the 
 * current state of the game (e.g., player moves).
 * 
 * @param value The value of the cell
 * 
 * @author Krzysztof Gach
 * @version 1.3
 */
@CellAdnotation("")
public record Cell(String value) {

    /**
     * Resets the cell to its default value (an empty string).
     * This method creates a new {@link Cell} with the default value and returns it.
     *
     * @return a new {@link Cell} object with the default value
     */
    public Cell reset() {
        return new Cell("");
    }
}