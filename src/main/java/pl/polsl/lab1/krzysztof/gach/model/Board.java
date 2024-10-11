package pl.polsl.lab1.krzysztof.gach.model;

/**
 *
 * @author Krzysztof Gach
 */
public class Board {
    private Cell[][] cells;

    public Board() {
        this.cells = new Cell[3][3]; // Example for a 3x3 board
        initializeBoard();
    }

    private void initializeBoard() {
        for (Cell[] cell : cells) {
            for (int j = 0; j < cell.length; j++) {
                cell[j] = new Cell();
            }
        }
    }
    
    public boolean isBoardFull(int round) {
        int totalCells = cells.length * cells[0].length;
    
        return round >= totalCells;
}
    
    // Method to resize the board
    public void resize(int newRows, int newCols) {
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

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell) {
        cells[x][y] = cell;
    }
    
    public Cell[][] getBoard() {
        return cells;
    }
    
    public void updateBoard(int x, int y, String input){
        if (x >= 0 && x < cells.length && y >= 0 && y < cells[0].length) {
            Cell cellToUpdate = getCell(x, y);
            
            cellToUpdate.setValue(input);
        } else {
            System.out.println("Invalid coordinates: (" + x + ", " + y + "). Unable to update board.");
        }
    }
}
