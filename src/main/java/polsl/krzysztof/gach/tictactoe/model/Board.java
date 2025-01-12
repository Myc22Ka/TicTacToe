package polsl.krzysztof.gach.tictactoe.model;

public class Board {
    private int size = 3;
    private Cell[][] board;

    public Board() {
        this.board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public int getLength(){
        return size * size;
    }

    public Cell getCell(Coordinates coordinates) {
        var x = coordinates.getX();
        var y = coordinates.getY();

        if (x >= 0 && x < size && y >= 0 && y < size) {
            return this.board[x][y];
        } else {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    public void setCell(Coordinates coordinates, Cell cell) {
        var x = coordinates.getX();
        var y = coordinates.getY();

        if (x >= 0 && x < size && y >= 0 && y < size) {
            this.board[x][y] = cell;  // Set the Cell at the specified position
        } else {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    public TicToe checkWin() {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            TicToe rowWinner = checkRow(i);
            if (rowWinner != null) {
                return rowWinner;  // Winner found in a row
            }

            TicToe colWinner = checkColumn(i);
            if (colWinner != null) {
                return colWinner;  // Winner found in a column
            }
        }

        // Check diagonals
        TicToe mainDiagonalWinner = checkMainDiagonal();
        if (mainDiagonalWinner != null) {
            return mainDiagonalWinner;  // Winner found in main diagonal
        }

        return checkAntiDiagonal();  // Winner found in anti-diagonal or no winner
    }

    // Checks a specific row for a winning condition
    private TicToe checkRow(int row) {
        int firstValue = board[row][0].getValue();
        if (firstValue == 0) return null;  // Empty cell

        TicToe winner = (firstValue == 1) ? TicToe.X : TicToe.O;
        for (int j = 1; j < size; j++) {
            if (board[row][j].getValue() != firstValue) {
                return null;  // No winner in this row
            }
        }
        return winner;  // All values in the row are the same (winner)
    }

    // Checks a specific column for a winning condition
    private TicToe checkColumn(int col) {
        int firstValue = board[0][col].getValue();
        if (firstValue == 0) return null;  // Empty cell

        TicToe winner = (firstValue == 1) ? TicToe.X : TicToe.O;
        for (int i = 1; i < size; i++) {
            if (board[i][col].getValue() != firstValue) {
                return null;  // No winner in this column
            }
        }
        return winner;  // All values in the column are the same (winner)
    }

    // Checks the main diagonal (top-left to bottom-right) for a winning condition
    private TicToe checkMainDiagonal() {
        int firstValue = board[0][0].getValue();
        if (firstValue == 0) return null;  // Empty cell

        TicToe winner = (firstValue == 1) ? TicToe.X : TicToe.O;
        for (int i = 1; i < size; i++) {
            if (board[i][i].getValue() != firstValue) {
                return null;  // No winner in main diagonal
            }
        }
        return winner;  // All values along the diagonal are the same (winner)
    }

    // Checks the anti-diagonal (top-right to bottom-left) for a winning condition
    private TicToe checkAntiDiagonal() {
        int firstValue = board[0][size - 1].getValue();
        if (firstValue == 0) return null;  // Empty cell

        TicToe winner = (firstValue == 1) ? TicToe.X : TicToe.O;
        for (int i = 1; i < size; i++) {
            if (board[i][size - 1 - i].getValue() != firstValue) {
                return null;  // No winner in anti-diagonal
            }
        }
        return winner;  // All values along the anti-diagonal are the same (winner)
    }
}
