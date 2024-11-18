package pl.polsl.lab1.krzysztof.gach.model;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PlayersList} class.
 * 
 * @author Krzysztof Gach
 * @veriosn 1.0
 */
class PlayersListTest {

    private PlayersList playersList;

    @BeforeEach
    void setUp() {
        playersList = new PlayersList();
    }

    @Test
    void testAddPlayer() {
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

    @Test
    void testNextPlayer() {
        // GIVEN
        Player player1 = new Player("Alice", "X", 1);
        Player player2 = new Player("Bob", "O", 2);
        playersList.add(player1);
        playersList.add(player2);

        // WHEN & THEN
        // Initially, the current player should be player1
        assertEquals(player1, playersList.getCurrentPlayer(), "The current player should be player1.");
        
        // Advance to the next player (player2)
        playersList.nextPlayer();
        assertEquals(player2, playersList.getCurrentPlayer(), "The current player should be player2.");

        // Advance again to player1 (wrap around)
        playersList.nextPlayer();
        assertEquals(player1, playersList.getCurrentPlayer(), "The current player should be player1 again.");
    }

    @Test
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
    void testNextPlayerWithNoPlayers() {
        // GIVEN
        // No players are added to the list

        // WHEN & THEN
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> playersList.nextPlayer(),
                "Calling nextPlayer on an empty list should throw an IllegalStateException.");
        assertEquals("Cannot move to the next player, the player list is empty.", thrown.getMessage(),
                "The exception message should match.");
    }

    @Test
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
}
