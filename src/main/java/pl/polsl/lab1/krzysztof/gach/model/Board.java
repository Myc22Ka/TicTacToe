package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The Board class represents a game board consisting of a 2D array of cells.
 * It provides methods to initialize, resize, and update the board as well as 
 * check if the board is full.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
@Getter
@Setter
public final class Board {
    private final List<List<Cell>> cells;
    private int size = 0;

    /**
     * Constructs a new Board instance with a default size of 3x3.
     */
    public Board() {
        this.cells = new ArrayList<List<Cell>>();
        setSize(size);
    }

    /**
     * Initializes the board by creating new Cell objects for each position.
     */
    private void initializeBoard() {
        cells.clear();
        for (int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(new Cell());
            }
            cells.add(row);
        }
    }
    
    /**
     * Resets value in every Cell in Board.
     */
    public void clear() {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                cell.reset();
            }
        }
    }
    
    /**
     * Returns the total number of cells on the board.
     *
     * @return the total number of cells
     */
    public int length(){
        return size * size;
    }
    
    /**
     * Returns the size of one dimension of the board (number of rows).
     *
     * @return the number of rows in the board
     */
    public int size(){
        return size;
    }
    
    /**
     * Checks if the board is full based on the number of rounds played.
     *
     * @return true if the board is full, false otherwise
     */
    public boolean isBoardFull() {
        for (List<Cell> row : cells) {
            for (Cell cell : row) {
                if (cell.value().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Sets the size of the board based on user input.
     *
     * @param size the new size of the board
     */
    public void setSize(int size) {
        this.size = size;
        initializeBoard(); 
    }

    /**
     * Retrieves the Cell at the specified coordinates.
     *
     * @param x the x-coordinate (row index)
     * @param y the y-coordinate (column index)
     * @return the Cell at the specified position
     */
    public Cell getCell(int x, int y) {
        return cells.get(x).get(y);
    }

    /**
     * Sets the Cell at the specified coordinates.
     *
     * @param x the x-coordinate (row index)
     * @param y the y-coordinate (column index)
     * @param cell the Cell to set at the specified position
     */
    public void setCell(int x, int y, Cell cell) {
        cells.get(x).set(y, cell);
    }
    
    /**
     * Retrieves the entire board as a 2D array of Cells.
     *
     * @return the 2D array representing the game board
     */
    public List<List<Cell>> getCells() {
        return cells;
    }
    
    /**
     * Updates the specified cell on the board with a new value.
     *
     * @param x the x-coordinate (row index)
     * @param y the y-coordinate (column index)
     * @param input the value to set in the cell
     */
    public void updateBoard(int x, int y, String input){
        if (x >= 0 && x < size && y >= 0 && y < size) {
            setCell(x, y, new Cell(input)); 
        }
    }
    
    /**
     * Checks for a winning condition in the game by evaluating all rows, columns,
     * and diagonals. Returns the symbol of the winning player if a winner is found,
     * or an empty string if no winner exists.
     * 
     * @return the winning player's symbol, or an empty string if no winner
     */
    public String checkWin() {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            String rowWinner = checkRow(i);
            if (!rowWinner.isEmpty()) {
                return rowWinner;
            }

            String colWinner = checkColumn(i);
            if (!colWinner.isEmpty()) {
                return colWinner;
            }
        }
        
        // Check diagonals
        String mainDiagonalWinner = checkMainDiagonal();
        if (!mainDiagonalWinner.isEmpty()) {
            return mainDiagonalWinner;
        }

        String antiDiagonalWinner = checkAntiDiagonal();
        return antiDiagonalWinner;
    }

    /**
     * Checks a specific row for a winning condition. 
     * If all cells in the row contain the same non-empty value,
     * that value is returned as the winner. If not, an empty string is returned.
     * 
     * @param row the index of the row to check
     * @return the winning player's symbol if found, or an empty string if not
     */
    private String checkRow(int row) {
        String firstValue = cells.get(row).get(0).value();
        if (firstValue.isEmpty()) return "";

        for (int j = 1; j < size; j++) {
            if (!cells.get(row).get(j).value().equals(firstValue)) {
                return "";
            }
        }
        return firstValue;
    }

    /**
     * Checks a specific column for a winning condition.
     * If all cells in the column contain the same non-empty value,
     * that value is returned as the winner. If not, an empty string is returned.
     * 
     * @param col the index of the column to check
     * @return the winning player's symbol if found, or an empty string if not
     */
    private String checkColumn(int col) {
        String firstValue = cells.get(0).get(col).value();
        if (firstValue.isEmpty()) return "";

        for (int i = 1; i < size; i++) {
            if (!cells.get(i).get(col).value().equals(firstValue)) {
                return "";
            }
        }
        return firstValue;
    }

    /**
     * Checks the main diagonal (top-left to bottom-right) for a winning condition.
     * If all cells along the diagonal contain the same non-empty value,
     * that value is returned as the winner. If not, an empty string is returned.
     * 
     * @return the winning player's symbol if found, or an empty string if not
     */
    private String checkMainDiagonal() {
        String firstValue = cells.get(0).get(0).value();
        if (firstValue.isEmpty()) return "";

        for (int i = 1; i < size; i++) {
            if (!cells.get(i).get(i).value().equals(firstValue)) {
                return "";
            }
        }
        return firstValue;
    }

    /**
     * Checks the anti-diagonal (top-right to bottom-left) for a winning condition.
     * If all cells along the anti-diagonal contain the same non-empty value,
     * that value is returned as the winner. If not, an empty string is returned.
     * 
     * @return the winning player's symbol if found, or an empty string if not
     */
    private String checkAntiDiagonal() {
        String firstValue = cells.get(0).get(size - 1).value();
        if (firstValue.isEmpty()) return "";

        for (int i = 1; i < size; i++) {
            if (!cells.get(i).get(size - 1 - i).value().equals(firstValue)) {
                return "";
            }
        }
        return firstValue;
    }
}
