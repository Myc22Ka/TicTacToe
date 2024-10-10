package pl.polsl.lab1.krzysztof.gach.view;

import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Cell;

/**
 *
 * @author Krzysztof Gach
 */
public class View {
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
