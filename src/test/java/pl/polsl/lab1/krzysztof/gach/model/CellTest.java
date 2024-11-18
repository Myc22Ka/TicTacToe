/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.lab1.krzysztof.gach.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Krzysztof
 */
public class CellTest {
    
    public CellTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of reset method, of class Cell.
     */
    @Test
    void testReset() {
        // GIVEN
        Cell cell = new Cell("X");

        // WHEN
        Cell resetCell = cell.reset();

        // THEN
        assertNotSame(cell, resetCell, "The reset cell should be a new instance.");
        assertEquals("", resetCell.value(), "The reset cell should have an empty value.");
        assertEquals("X", cell.value(), "The original cell should retain its value.");
    }
    
    /**
     * Test of reset method for empty Cell, of class Cell.
     */
    @Test
    void testResetEmptyCell() {
        // GIVEN
        Cell cell = new Cell("");

        // WHEN
        Cell resetCell = cell.reset();

        // THEN
        assertNotSame(cell, resetCell, "The reset cell should be a new instance.");
        assertEquals("", resetCell.value(), "The reset cell should have an empty value.");
    }

    /**
     * Test of value method, of class Cell.
     */
    @Test
    void testValue() {
        // GIVEN
        String initialValue = "O";

        // WHEN
        Cell cell = new Cell(initialValue);

        // THEN
        assertEquals(initialValue, cell.value(), "The cell's value should match the initialized value.");
    }
    
    @Test
    void testValueX() {
        // GIVEN
        String initialValue = "X";

        // WHEN
        Cell cell = new Cell(initialValue);

        // THEN
        assertEquals(initialValue, cell.value(), "The cell's value should match the initialized value.");
    }

    @Test
    void testValueEmpty() {
        // GIVEN
        String initialValue = "";

        // WHEN
        Cell cell = new Cell(initialValue);

        // THEN
        assertEquals(initialValue, cell.value(), "The cell's value should be an empty string.");
    }
}
