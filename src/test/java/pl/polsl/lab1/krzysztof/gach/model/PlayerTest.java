package pl.polsl.lab1.krzysztof.gach.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Player} class.
 * 
 * @author Krzysztof
 */
class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        // Create a new player before each test
        player = new Player("John", "X", 1);
    }

    @Test
    void testAddScore() {
        // GIVEN
        int initialScore = player.getScore();
        int pointsToAdd = 10;

        // WHEN
        player.addScore(pointsToAdd);

        // THEN
        assertEquals(initialScore + pointsToAdd, player.getScore(), "Player's score should be updated correctly.");
    }
    
    @Test
    void testAddScoreNegative() {
        // GIVEN
        int initialScore = player.getScore();
        int negativePoints = -10;

        // WHEN & THEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> player.addScore(negativePoints));
        assertEquals("Score cannot be negative.", exception.getMessage(), "Exception message should match.");
    }
    
    @Test
    void testAddScoreZero() {
        // GIVEN
        int initialScore = player.getScore();
        int negativePoints = 0;

        // WHEN
        player.addScore(negativePoints);

        // THEN
        assertEquals(initialScore, player.getScore(), "Player's score should not decrease with negative points.");
    }

    @Test
    void testCheckNameValidName() {
        // GIVEN
        String validName = "Alice";

        // WHEN
        player.setName(validName);

        // THEN
        assertDoesNotThrow(() -> player.checkName(), "No exception should be thrown for a valid name.");
    }

    @Test
    void testCheckNameNullName() {
        // GIVEN
        player.setName(null);

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name cannot be null or empty.", exception.getMessage(), "Exception message should match.");
    }

    @Test
    void testCheckNameEmptyName() {
        // GIVEN
        player.setName("");

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name cannot be null or empty.", exception.getMessage(), "Exception message should match.");
    }

    @Test
    void testCheckNameShortName() {
        // GIVEN
        player.setName("Jo");

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name must be at least 3 characters long.", exception.getMessage(), "Exception message should match.");
    }

    @Test
    void testCheckNameInvalidCharacters() {
        // GIVEN
        player.setName("John123");

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name can only contain alphabetic characters.", exception.getMessage(), "Exception message should match.");
    }
}
