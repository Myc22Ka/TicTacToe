package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    // Ustawienie początkowego stanu przed każdym testem
    @BeforeEach
    void setUp() {
        board = new Board();
        board.setSize(3); // Domyślny rozmiar planszy to 3x3
    }

    @Test
    void testClearWhenAlreadyEmpty() {
        // GIVEN
        board.clear();

        // WHEN
        board.clear();

        // THEN
        board.getCells().forEach(row ->
            row.forEach(cell -> assertEquals("", cell.value()))
        );
    }

    @Test
    void testClearPartiallyFilledBoard() {
        // GIVEN
        board.updateBoard(0, 0, "X");
        board.updateBoard(1, 1, "O");

        // WHEN
        board.clear();

        // THEN
        board.getCells().forEach(row ->
            row.forEach(cell -> assertEquals("", cell.value()))
        );
    }

    @Test
    void testClearAfterWin() {
        // GIVEN
        board.updateBoard(0, 0, "X");
        board.updateBoard(0, 1, "X");
        board.updateBoard(0, 2, "X");

        // WHEN
        board.clear();

        // THEN
        board.getCells().forEach(row ->
            row.forEach(cell -> assertEquals("", cell.value()))
        );
    }

    @Test
    void testLength() {
        // GIVEN
        var sizes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        var map = new HashMap<Integer, Integer>();
        
        sizes.forEach(size -> map.put(size, size * size));

        // WHEN & THEN
        map.forEach((size, length) -> {
            assertEquals(size * size, length);
        });
    }

    @Test
    void testSize() {
        // GIVEN
        var sizes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        
        sizes.forEach(size -> {
            board.setSize(size);
           
            // WHEN
            int newSize = board.size();
            
            // THEN
            assertEquals(size, newSize);
        });
    }

    @Test
    void testIsBoardFull() {
        // GIVEN
        board.updateBoard(0, 0, "X");
        board.updateBoard(0, 1, "O");
        board.updateBoard(0, 2, "X");
        board.updateBoard(1, 0, "O");
        board.updateBoard(1, 1, "X");
        board.updateBoard(1, 2, "O");
        board.updateBoard(2, 0, "X");
        board.updateBoard(2, 1, "O");
        board.updateBoard(2, 2, "X");

        // WHEN
        boolean isFull = board.isBoardFull();

        // THEN
        assertTrue(isFull);
    }

    @Test
    void testIsBoardFullWhenNotFull() {
        // GIVEN
        board.updateBoard(0, 0, "X");

        // WHEN
        boolean isFull = board.isBoardFull();

        // THEN
        assertFalse(isFull);
    }
    
    @Test
    void testIsBoardFullWhenEmpty() {
        // GIVEN
        board.clear(); // Empty the board

        // WHEN
        boolean isFull = board.isBoardFull();

        // THEN
        assertFalse(isFull);
    }

    @Test
    void testUpdateBoardLargerBoard() {
        // GIVEN
        board.setSize(4);

        // WHEN
        board.updateBoard(3, 3, "X");

        // THEN
        assertEquals("X", board.getCell(3, 3).value());
    }

    @Test
    void testUpdateBoardMultipleUpdates() {
        // GIVEN
        board.updateBoard(0, 0, "X");
        board.updateBoard(1, 1, "O");

        // WHEN
        board.updateBoard(0, 1, "X");
        board.updateBoard(1, 2, "O");

        // THEN
        assertEquals("X", board.getCell(0, 1).value());
        assertEquals("O", board.getCell(1, 2).value());
    }

    @Test
    void testUpdateBoardInvalidCoordinates() {
        // GIVEN
        board.updateBoard(5, 5, "X");

        // WHEN
        board.updateBoard(-1, -1, "O");

        // THEN
        assertEquals("", board.getCell(0, 0).value());
    }

    @Test
    void testUpdateBoardNegativeCoordinates() {
        // GIVEN
        board.updateBoard(-1, 0, "X");

        // WHEN
        board.updateBoard(0, 0, "O");

        // THEN
        assertEquals("O", board.getCell(0, 0).value());
    }

    @Test
    void testUpdateBoardOutOfRangeCoordinates() {
        // GIVEN
        board.setSize(4);

        // WHEN
        board.updateBoard(5, 5, "X");

        // THEN
        board.getCells().forEach(row ->
            row.forEach(cell -> assertEquals("", cell.value()))
        );
    }

    @Test
    void testUpdateBoardValidAndInvalidCoordinates() {
        // GIVEN
        board.updateBoard(0, 0, "X");

        // WHEN
        board.updateBoard(5, 5, "O");

        // THEN
        assertEquals("X", board.getCell(0, 0).value());
    }

    @Test
    void testCheckWinRowWin() {
        // GIVEN
        board.updateBoard(0, 0, "X");
        board.updateBoard(0, 1, "X");
        board.updateBoard(0, 2, "X");

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals("X", winner);
    }

    @Test
    void testCheckWinColumnWin() {
        // GIVEN
        board.updateBoard(0, 0, "O");
        board.updateBoard(1, 0, "O");
        board.updateBoard(2, 0, "O");

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals("O", winner);
    }

    @Test
    void testCheckWinMainDiagonalWin() {
        // GIVEN
        board.updateBoard(0, 0, "X");
        board.updateBoard(1, 1, "X");
        board.updateBoard(2, 2, "X");

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals("X", winner);
    }

    @Test
    void testCheckWinAntiDiagonalWin() {
        // GIVEN
        board.updateBoard(0, 2, "O");
        board.updateBoard(1, 1, "O");
        board.updateBoard(2, 0, "O");

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals("O", winner);
    }

    @Test
    void testCheckWinNoWinner() {
        // GIVEN
        board.updateBoard(0, 0, "X");
        board.updateBoard(0, 1, "O");
        board.updateBoard(0, 2, "X");

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals("", winner);
    }
}
