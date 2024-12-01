package pl.polsl.lab1.krzysztof.gach.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for the {@link Player} class.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        // Create a new player before each test
        player = new Player("John", "X", 1);
    }

    /**
     * Tests adding score to a player and verifies the updated score.
     */
    @ParameterizedTest
    @CsvSource({
        "10, 10, 20",
        "0, 0, 1",
        "50, 50, 51"
    })
    void testAddScore(int pointsToAdd, int expectedScore) {
        // GIVEN
        int initialScore = player.getScore();

        // WHEN
        player.addScore(pointsToAdd);

        // THEN
        assertEquals(initialScore + pointsToAdd, player.getScore(), "Player's score should be updated correctly.");
    }

    /**
     * Tests valid and invalid player name check.
     */
    @ParameterizedTest
    @CsvSource({
        "Alice, true",
        "Al, false",
        "Jo, false",
        "'', false",
        "John123, false"
    })
    void testCheckNameValidName(String name, boolean shouldPass) {
        // GIVEN
        player.setName(name);

        // WHEN & THEN
        if (shouldPass) {
            assertDoesNotThrow(() -> player.checkName(), "No exception should be thrown for a valid name.");
        } else {
            InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
            assertTrue(exception.getMessage().contains("Name must be at least 3 characters long") ||
                       exception.getMessage().contains("Name cannot be null or empty") ||
                       exception.getMessage().contains("Name can only contain alphabetic characters"),
                       "Exception message should match.");
        }
    }
    
    /**
     * Tests adding a negative score and verifies the exception thrown.
     */
    @Test
    void testAddScoreNegative() {
        // GIVEN
        int negativePoints = -10;

        // WHEN & THEN
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> player.addScore(negativePoints));
        assertEquals("Score cannot be negative.", exception.getMessage(), "Exception message should match.");
    }

    /**
     * Tests checking player's name when it is null and verifies the exception thrown.
     */
    @Test
    void testCheckNameNullName() {
        // GIVEN
        player.setName(null);

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name cannot be null or empty.", exception.getMessage(), "Exception message should match.");
    }

    /**
     * Tests checking player's name when it is empty and verifies the exception thrown.
     */
    @Test
    void testCheckNameEmptyName() {
        // GIVEN
        player.setName("");

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name cannot be null or empty.", exception.getMessage(), "Exception message should match.");
    }

    /**
     * Tests checking player's name when it is too short and verifies the exception thrown.
     */
    @Test
    void testCheckNameShortName() {
        // GIVEN
        player.setName("Jo");

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name must be at least 3 characters long.", exception.getMessage(), "Exception message should match.");
    }

    /**
     * Tests checking player's name when it contains invalid characters and verifies the exception thrown.
     */
    @Test
    void testCheckNameInvalidCharacters() {
        // GIVEN
        player.setName("John123");

        // WHEN & THEN
        InvalidNameException exception = assertThrows(InvalidNameException.class, () -> player.checkName());
        assertEquals("Name can only contain alphabetic characters.", exception.getMessage(), "Exception message should match.");
    }
}
