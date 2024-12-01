package pl.polsl.lab1.krzysztof.gach.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Cell class covering correct, incorrect, and boundary scenarios.
 * @author Krzysztof Gach
 * @version 1.1
 */
public class CellTest {

    /**
     * Test of reset method with various initial values.
     * This test checks if the reset operation works for any initial value.
     */
    @ParameterizedTest
    @ValueSource(strings = {"X", "O", "A", " "})
    void testReset(String initialValue) {
        // GIVEN
        Cell cell = new Cell(initialValue);

        // WHEN
        Cell resetCell = cell.reset();

        // THEN
        assertNotSame(cell, resetCell, "The reset cell should be a new instance.");
        assertEquals("", resetCell.value(), "The reset cell should have an empty value.");
    }

    /**
     * Test the value method for various initial values.
     * This test checks if the value is properly set for different input values.
     */
    @ParameterizedTest
    @CsvSource({
        "'X', X",
        "'O', O",
        "'', ''",
        "'A', A"
    })
    void testValue(String initialValue, String expectedValue) {
        // GIVEN
        Cell cell = new Cell(initialValue);

        // WHEN
        String value = cell.value();

        // THEN
        assertEquals(expectedValue, value, "The cell's value should match the initialized value.");
    }

    /**
     * Test the value method when the cell is initialized with an empty string.
     * This test checks if the value method returns an empty string when the cell is empty.
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void testValueEmpty(String initialValue) {
        // GIVEN
        Cell cell = new Cell(initialValue);

        // WHEN
        String value = cell.value();

        // THEN
        assertEquals(initialValue, value, "The cell's value should be an empty string or space.");
    }

    /**
     * Test the reset method for an invalid state where the reset operation is not allowed.
     * This test checks if the reset method can handle invalid input or edge cases.
     * For example, assuming there's some validation for values, we simulate it here.
     */
    @Test
    void testResetThrowsException() {
        // GIVEN
        Cell cell = new Cell("invalid");

        // WHEN & THEN
        Executable resetAction = () -> cell.reset(); // Here we're assuming reset throws an exception for invalid values.
        assertThrows(IllegalArgumentException.class, resetAction, "Resetting the cell with 'invalid' should throw an exception.");
    }

    /**
     * Test the value method for invalid input that may lead to an exception.
     * This is a boundary test where we force an invalid value (e.g., null or unexpected characters).
     */
    @Test
    void testValueThrowsExceptionOnNull() {
        // GIVEN
        Cell cell = new Cell(null);

        // WHEN & THEN
        Executable resetAction = () -> cell.reset();
        assertThrows(IllegalArgumentException.class, resetAction, "Resetting the cell with null should throw an exception.");
    }

    /**
     * Test the edge case of initializing the cell with an empty string.
     * This is a boundary test for the reset and value methods with minimal input.
     */
    @Test
    void testValueWithEmptyString() {
        // GIVEN
        Cell cell = new Cell("");

        // WHEN
        String value = cell.value();

        // THEN
        assertEquals("", value, "The cell's value should be an empty string.");
    }
}
