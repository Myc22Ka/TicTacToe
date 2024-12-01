package pl.polsl.lab1.krzysztof.gach.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * The PlayersList class manages a list of players in the game.
 * It allows adding players, tracking the current player, and printing player names.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
@Getter
@Setter
public class PlayersList {
    private final List<Player> players = new ArrayList<Player>(); // List of Player objects representing all players in the game.
    private int currentPlayerIndex = 0; // Index of the currently active player in the players list, used to manage turn-taking.

    /**
     * Adds a player to the list.
     *
     * @param player the Player instance to be added
     */
    public void add(Player player) {
        if (player.getName().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty.");
        }
        
        players.add(player);
    }

    /**
     * Advances to the next player in the list.
     * If the end of the list is reached, it wraps around to the first player.
     */
    public void nextPlayer() {
        if (players.isEmpty()) {
            throw new IllegalStateException("Cannot move to the next player, the player list is empty.");
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); 
    }

    /**
     * Retrieves the current player from the list.
     *
     * @return the current Player instance
     */
    public Player getCurrentPlayer() {
        if (players.isEmpty()) {
            throw new IllegalStateException("Player list is empty.");
        }
        
        return players.get(currentPlayerIndex);
    }

    /**
     * Retrieves the list of all players currently in the game.
     *
     * @return a List of Player objects representing all players
     */
    public List<Player> getAllPlayers() {
        return players;
    }
}
