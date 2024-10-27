package pl.polsl.lab1.krzysztof.gach.model;

import java.util.Scanner;

/**
 * The Board class represents a game board consisting of a 2D array of cells.
 * It provides methods to initialize, resize, and update the board as well as 
 * check if the board is full.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public class Board {
    private Cell[][] cells; // 2D array of Cell objects representing the individual cells of the game board.


    /**
     * Constructs a new Board instance with a default size of 3x3.
     */
    public Board() {
        this.cells = new Cell[3][3]; // Example for a 3x3 board
        initializeBoard();
    }

    /**
     * Initializes the board by creating new Cell objects for each position.
     */
    private void initializeBoard() {
        for (Cell[] cell : cells) {
            for (int j = 0; j < cell.length; j++) {
                cell[j] = new Cell();
            }
        }
    }
    
    public void clear() {
        for (Cell[] row : cells) {
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
        return cells.length * cells[0].length;
    }
    
    /**
     * Returns the size of one dimension of the board (number of rows).
     *
     * @return the number of rows in the board
     */
    public int size(){
        return cells.length;
    }
    
    /**
     * Checks if the board is full based on the number of rounds played.
     *
     * @param round the current round number
     * @return true if the board is full, false otherwise
     */
    public boolean isBoardFull(int round) {    
        return round >= this.length();
    }
    
    /**
     * Sets the size of the board based on user input.
     *
     * @param size the new size of the board as a string
     */
    public void setSize(String size) {
        boolean validSize = false;
        Scanner scanner = new Scanner(System.in);

        while (!validSize) {
            try {
                int newSize = Integer.parseInt(size);
                resize(newSize, newSize); // Assuming resize is a valid method in your class
                 System.out.println("Board size is set to: " + newSize);
                validSize = true; // If no exception is thrown, exit the loop
            } catch (NumberFormatException e) {
                System.out.print("Incorrect size! Please type new size: ");
                size = scanner.nextLine(); // Get new size input from the user
            }
        }
    }
    
    /**
     * Resizes the board to the specified number of rows and columns.
     *
     * @param newRows the new number of rows
     * @param newCols the new number of columns
     */
    private void resize(int newRows, int newCols) {
        // Create a new 2D array with the new size
        Cell[][] newCells = new Cell[newRows][newCols];
        
        // Initialize the new board with Cell objects
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                if (i < this.cells.length && j < this.cells[i].length) {
                    // If the current cell exists in the old board, copy it over
                    newCells[i][j] = this.cells[i][j];
                } else {
                    // Otherwise, initialize a new cell
                    newCells[i][j] = new Cell();
                }
            }
        }

        // Replace the old board with the new resized one
        this.cells = newCells;
    }

    /**
     * Retrieves the Cell at the specified coordinates.
     *
     * @param x the x-coordinate (row index)
     * @param y the y-coordinate (column index)
     * @return the Cell at the specified position
     */
    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    /**
     * Sets the Cell at the specified coordinates.
     *
     * @param x the x-coordinate (row index)
     * @param y the y-coordinate (column index)
     * @param cell the Cell to set at the specified position
     */
    public void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }
    
    /**
     * Retrieves the entire board as a 2D array of Cells.
     *
     * @return the 2D array representing the game board
     */
    public Cell[][] getCells() {
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
        if (x >= 0 && x < cells.length && y >= 0 && y < cells[0].length) {
            Cell cellToUpdate = getCell(x, y);
            
            cellToUpdate.setValue(input);
        }
    }
}
