package pl.polsl.lab1.krzysztof.gach.view;

import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Cell;

/**
 * The View class handles the display of the game board.
 * It provides methods to print the current state of the board to the console.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class View {
    
    /**
     * Constructs a new View instance
     */
    public View(){
        // This will be used when I start implement GUI...
    }
    
    /**
     * Prints the game board to the console.
     *
     * @param board the Board instance representing the current state of the game board
     */
    public static void printBoard(Board board) {
        Cell[][] cells = board.getBoard(); // Get the 2D array of cells
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                // Print the value of each cell
                System.out.print(cells[i][j].getValue());
            }
            System.out.println(); // Move to the next row after printing a row
        }
    }
}
