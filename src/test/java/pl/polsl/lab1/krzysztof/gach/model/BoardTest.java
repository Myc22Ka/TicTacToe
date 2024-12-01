package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit tests for the {@link Board} class, verifying its behavior 
 * such as square marking and win conditions.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.setSize(3); // Domyślny rozmiar planszy to 3x3
    }
    
    /**
     * Tests clearing the board.
     */
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3", 
        "0, 1, 2", 
        "0, 0, 0"
    })
    void testClear(int row, int col, String value) {
        // GIVEN
        board.updateBoard(row, col, value);

        // WHEN
        board.clear();

        // THEN
        board.getCells().forEach(r -> r.forEach(cell -> assertEquals("", cell.value())));
    }
    
    /**
     * Tests clearing the board when it's already empty.
     */
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

    /**
     * Tests clearing a partially filled board.
     */
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

    /**
     * Tests clearing the board after a win.
     */
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

    /**
     * Tests the length of the board based on size values.
     */
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

    /**
     * Tests setting and getting the board size.
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void testSize(int size) {
        // GIVEN
        board.setSize(size);

        // WHEN
        int newSize = board.size();

        // THEN
        assertEquals(size, newSize);
    }

    /**
     * Tests if the board is full based on specific values.
     */
    @ParameterizedTest
    @CsvSource({
        "X, true", 
        "O, true", 
        " , false"
    })
    void testIsBoardFull(String value, boolean expectedResult) {
        // GIVEN
        board.clear(); // Ensure board is cleared
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.updateBoard(i, j, value); // Fill board with the value
            }
        }

        // WHEN
        boolean isFull = board.isBoardFull();

        // THEN
        assertEquals(expectedResult, isFull); // Assert if the board is full as expected
    }

    /**
     * Tests if the board is full when it's not full.
     */
    @Test
    void testIsBoardFullWhenNotFull() {
        // GIVEN
        board.updateBoard(0, 0, "X");

        // WHEN
        boolean isFull = board.isBoardFull();

        // THEN
        assertFalse(isFull);
    }
    
    /**
     * Tests if the board is full when it's empty.
     */
    @Test
    void testIsBoardFullWhenEmpty() {
        // GIVEN
        board.clear(); // Empty the board

        // WHEN
        boolean isFull = board.isBoardFull();

        // THEN
        assertFalse(isFull);
    }

    /**
     * Tests updating the board with valid coordinates.
     */
    @ParameterizedTest
    @CsvSource({
        "0, 0, X, X", 
        "1, 1, O, O", 
        "2, 2, X, X"
    })
    void testUpdateBoard(int row, int col, String value, String expectedValue) {
        // GIVEN
        board.updateBoard(row, col, value);

        // WHEN
        String cellValue = board.getCell(row, col).value();

        // THEN
        assertEquals(expectedValue, cellValue);
    }
    
    /**
     * Tests updating the board with a larger board size.
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testUpdateBoardLargerBoard(int size) {
        // GIVEN
        board.setSize(size);

        // WHEN
        board.updateBoard(size - 1, size - 1, "X");

        // THEN
        assertEquals("X", board.getCell(size - 1, size - 1).value());
    }

    /**
     * Tests multiple updates to the board.
     */
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

    /**
     * Tests updating the board with invalid coordinates.
     */
    @ParameterizedTest
    @CsvSource({
        "5, 5, X, '', true", 
        "-1, -1, O, '', false", 
        "0, 0, X, X, true"
    })
    void testUpdateBoardWithInvalidCoordinates(int row, int col, String value, String expectedValue, boolean isValid) {
        // GIVEN
        board.updateBoard(row, col, value);

        // WHEN
        String cellValue = board.getCell(row, col).value();

        // THEN
        if (isValid) {
            assertEquals(expectedValue, cellValue);
        } else {
            assertEquals("", cellValue);
        }
    }

    /**
     * Tests updating the board with negative coordinates.
     */
    @Test
    void testUpdateBoardNegativeCoordinates() {
        // GIVEN
        board.updateBoard(-1, 0, "X");

        // WHEN
        board.updateBoard(0, 0, "O");

        // THEN
        assertEquals("O", board.getCell(0, 0).value());
    }

    /**
     * Tests updating the board with out-of-range coordinates.
     */
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

    /**
     * Tests updating the board with valid and invalid coordinates.
     */
    @Test
    void testUpdateBoardValidAndInvalidCoordinates() {
        // GIVEN
        board.updateBoard(0, 0, "X");

        // WHEN
        board.updateBoard(5, 5, "O");

        // THEN
        assertEquals("X", board.getCell(0, 0).value());
    }
    
    /**
     * Tests checking the winner for a column win.
     */
    @ParameterizedTest
    @ValueSource(strings = {"X", "O"})
    void testCheckWin(String symbol) {
        // GIVEN
        board.updateBoard(0, 0, symbol);
        board.updateBoard(1, 0, symbol);
        board.updateBoard(2, 0, symbol);

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals(symbol, winner);
    }

    /**
     * Tests checking the winner for a row win.
     */
    @ParameterizedTest
    @CsvSource({
        "X, O, X", 
        "O, X, O"
    })
    void testCheckWinRowWin(String symbol1, String symbol2, String expectedWinner) {
        // GIVEN
        board.updateBoard(0, 0, symbol1);
        board.updateBoard(0, 1, symbol1);
        board.updateBoard(0, 2, symbol1);

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals(expectedWinner, winner);
    }
    
    /**
     * Tests checking the winner for a column win.
     */
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
    
    /**
     * Tests checking the winner for a main diagonal win.
     */
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
    
    /**
     * Tests checking the winner for an anti-diagonal win.
     */
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
    
    /**
     * Tests checking the winner when there's no winner.
     */
    @ParameterizedTest
    @CsvSource({
        "X, O, ''", 
        "O, X, ''"
    })
    void testCheckWinNoWinner(String symbol1, String symbol2, String expectedWinner) {
        // GIVEN
        board.updateBoard(0, 0, symbol1);
        board.updateBoard(0, 1, symbol2);
        board.updateBoard(0, 2, symbol1);

        // WHEN
        String winner = board.checkWin();

        // THEN
        assertEquals(expectedWinner, winner);
    }
}
