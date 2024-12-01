package pl.polsl.lab1.krzysztof.gach.model;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PlayersList} class.
 * 
 * @author Krzysztof Gach
 * @veriosn 1.1
 */
class PlayersListTest {

    private PlayersList playersList;

    @BeforeEach
    void setUp() {
        playersList = new PlayersList();
    }

    @ParameterizedTest
    @CsvSource({
        "'Alice', 'X', 1",  // Valid case, size should be 1
        "'Bob', 'O', 1",    // Valid case, size should be 1
        "'', 'X', 0",       // Invalid case, should throw exception
    })
    /**
     * Tests adding a player to the list and verifies the list size and contents.
     */
    void testAddPlayer(String name, String symbol, int expectedSize) {
        // GIVEN
        Player player = new Player(name, symbol, 1);

        // WHEN & THEN
        if (expectedSize == 0) {
            assertThrows(IllegalArgumentException.class, () -> playersList.add(player));
        } else {
            playersList.add(player);
            assertEquals(expectedSize, playersList.getAllPlayers().size(), "The list should contain 1 player after adding.");
            assertTrue(playersList.getAllPlayers().contains(player), "The list should contain the player.");
        }
    }

    @Test
    /**
     * Tests adding multiple players to the list and verifies their presence.
     */
    void testAddMultiplePlayers() {
        // GIVEN
        Player player1 = new Player("Alice", "X", 1);
        Player player2 = new Player("Bob", "O", 2);

        // WHEN
        playersList.add(player1);
        playersList.add(player2);

        // THEN
        assertEquals(2, playersList.getAllPlayers().size(), "The list should contain 2 players.");
        assertTrue(playersList.getAllPlayers().contains(player1), "The list should contain player1.");
        assertTrue(playersList.getAllPlayers().contains(player2), "The list should contain player2.");
    }

    @Test
    /**
     * Tests retrieving the current player from the list.
     */
    void testGetCurrentPlayer() {
        // GIVEN
        Player player1 = new Player("Alice", "X", 1);
        Player player2 = new Player("Bob", "O", 2);

        // WHEN
        playersList.add(player1);
        playersList.add(player2);

        // THEN
        assertEquals(player1, playersList.getCurrentPlayer(), "The current player should be player1.");
    }

    @ParameterizedTest
    @CsvSource({
        "1, 'Alice', 'X', 1",   // One player, should stay the same
        "2, 'Alice', 'X', 2",   // Two players, should cycle
        "3, 'Alice', 'X', 3",   // Three players, cycling
    })
    /**
     * Tests cycling through players based on the player count.
     */
    void testNextPlayerWithDifferentCounts(int playerCount, String name, String symbol, int expectedSize) {
        // GIVEN
        for (int i = 0; i < playerCount; i++) {
            playersList.add(new Player(name + i, symbol, i));
        }

        // WHEN & THEN
        assertEquals(expectedSize, playersList.getAllPlayers().size(), "The size of the player list should match.");
        Player currentPlayer = playersList.getCurrentPlayer();

        // If there's only one player, it should stay the same
        if (playerCount == 1) {
            playersList.nextPlayer(); // No change, should stay the same player
            assertEquals(currentPlayer, playersList.getCurrentPlayer(), "The current player should remain the same with one player.");
        } else {
            playersList.nextPlayer(); // Move to the next player
            assertNotEquals(currentPlayer, playersList.getCurrentPlayer(), "The current player should change after nextPlayer call.");
        }
    }

    @Test
    /**
     * Tests behavior when there is only one player in the list.
     */
    void testNextPlayerWithOnePlayer() {
        // GIVEN
        Player player1 = new Player("Alice", "X", 1);
        playersList.add(player1);

        // WHEN
        // The current player should be player1
        assertEquals(player1, playersList.getCurrentPlayer(), "The current player should be player1.");
        
        // Advancing to next player with only one player should still return player1
        playersList.nextPlayer();
        assertEquals(player1, playersList.getCurrentPlayer(), "With one player, the current player should remain the same.");
    }

    @Test
    /**
     * Tests behavior when the player list is empty.
     */
    void testNextPlayerWithNoPlayers() {
        // GIVEN
        // No players are added to the list

        // WHEN & THEN
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> playersList.nextPlayer(),
                "Calling nextPlayer on an empty list should throw an IllegalStateException.");
        assertEquals("Cannot move to the next player, the player list is empty.", thrown.getMessage(),
                "The exception message should match.");
    }
    
    @ParameterizedTest
    @CsvSource({
        "0, 'Cannot move to the next player, the player list is empty.'",   // Empty list, should throw exception
        "1, 'Cannot move to the next player, the player list has only one player.'" // One player, should stay the same
    })
    /**
     * Tests behavior when invalid conditions are met for moving to the next player.
     */
    void testNextPlayerWithInvalidConditions(int playerCount, String expectedMessage) {
        // GIVEN
        if (playerCount == 1) {
            playersList.add(new Player("Alice", "X", 1)); // Add one player for the second case
        }
        // For playerCount == 0, no players are added, leaving the list empty.

        // WHEN & THEN
        if (playerCount == 0) {
            IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> playersList.nextPlayer(),
                    "Calling nextPlayer on an empty list should throw an IllegalStateException.");
            assertEquals(expectedMessage, thrown.getMessage(), "The exception message should match the expected.");
        } else {
            // For playerCount == 1, no exception should be thrown, and the player should stay the same
            assertDoesNotThrow(() -> playersList.nextPlayer(), "nextPlayer() should not throw an exception when there's one player.");
            assertEquals("Alice", playersList.getCurrentPlayer().getName(), "The current player should remain the same when there's only one player.");
        }
    }

    @Test
    /**
     * Tests retrieving all players in the list.
     */
    void testGetAllPlayers() {
        // GIVEN
        Player player1 = new Player("Alice", "X", 1);
        Player player2 = new Player("Bob", "O", 2);
        playersList.add(player1);
        playersList.add(player2);

        // WHEN
        List<Player> allPlayers = playersList.getAllPlayers();

        // THEN
        assertNotNull(allPlayers, "The list of all players should not be null.");
        assertEquals(2, allPlayers.size(), "The list should contain 2 players.");
        assertTrue(allPlayers.contains(player1), "The list should contain player1.");
        assertTrue(allPlayers.contains(player2), "The list should contain player2.");
    }

    @Test
    /**
     * Tests adding a large number of players to the list.
     */
    void testAddLargeNumberOfPlayers() {
        // GIVEN
        for (int i = 0; i < 1000; i++) {
            playersList.add(new Player("Player" + i, "X", i));
        }

        // WHEN
        int size = playersList.getAllPlayers().size();

        // THEN
        assertEquals(1000, size, "The list should contain 1000 players.");
    }
}
