package pl.polsl.lab1.krzysztof.gach.model;

/**
 * The Board class represents a game board consisting of a 2D array of cells.
 * It provides methods to initialize, resize, and update the board as well as 
 * check if the board is full.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public class Board {
    private Cell[][] cells;
    private int size = 0;


    /**
     * Constructs a new Board instance with a default size of 3x3.
     */
    public Board() {
        this.cells = new Cell[size][size];
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
        return size;
    }
    
    /**
     * Checks if the board is full based on the number of rounds played.
     *
     * @return true if the board is full, false otherwise
     */
    public boolean isBoardFull() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.getValue().isEmpty()) {
                    return false;
                }
            }
        }
    
        return true;
    }
    
    /**
     * Sets the size of the board based on user input.
     *
     * @param size the new size of the board as a string
     */
    public void setSize(int size) {
        this.size = size;
        
        resize(size, size); 
    }
    
    /**
     * Resizes the board to the specified number of rows and columns.
     *
     * @param newRows the new number of rows
     * @param newCols the new number of columns
     */
    private void resize(int newRows, int newCols) {
        Cell[][] newCells = new Cell[newRows][newCols];
        
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                if (i < this.cells.length && j < this.cells[i].length) {
                    newCells[i][j] = this.cells[i][j];
                } else {
                    newCells[i][j] = new Cell();
                }
            }
        }

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

    private String checkRow(int row) {
        String firstValue = cells[row][0].getValue();
        if (firstValue.isEmpty()) return "";

        for (int j = 1; j < size; j++) {
            if (!cells[row][j].getValue().equals(firstValue)) {
                return "";
            }
        }
        return firstValue; // Winner value
    }

    private String checkColumn(int col) {
        String firstValue = cells[0][col].getValue();
        if (firstValue.isEmpty()) return "";

        for (int i = 1; i < size; i++) {
            if (!cells[i][col].getValue().equals(firstValue)) {
                return "";
            }
        }
        return firstValue; // Winner value
    }

    private String checkMainDiagonal() {
        String firstValue = cells[0][0].getValue();
        if (firstValue.isEmpty()) return "";

        for (int i = 1; i < size; i++) {
            if (!cells[i][i].getValue().equals(firstValue)) {
                return "";
            }
        }
        return firstValue; // Winner value
    }

    private String checkAntiDiagonal() {
        String firstValue = cells[0][size - 1].getValue();
        if (firstValue.isEmpty()) return "";

        for (int i = 1; i < size; i++) {
            if (!cells[i][size - 1 - i].getValue().equals(firstValue)) {
                return "";
            }
        }
        return firstValue; // Winner value
    }
}
