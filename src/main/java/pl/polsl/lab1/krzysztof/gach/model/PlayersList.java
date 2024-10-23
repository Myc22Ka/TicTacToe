package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The PlayersList class manages a list of players in the game.
 * It allows adding players, tracking the current player, and printing player names.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 * 
 */
public class PlayersList {
    private final List<Player> players; // List of Player objects representing all players in the game.
    private int currentPlayerIndex; // Index of the currently active player in the players list, used to manage turn-taking.
    
    /**
     * Constructs a new PlayersList instance with an empty player list 
     * and sets the current player index to 0.
     */
    public PlayersList() {
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }

    /**
     * Adds a player to the list.
     *
     * @param player the Player instance to be added
     */
    public void add(Player player) {
        players.add(player);
    }

    /**
     * Retrieves size of players list.
     * 
     * @return current size of players list
     */
    public int getPlayersSizeList() {
        return players.size();
    }

    /**
     * Retrieves the current player from the list.
     *
     * @return the current Player instance
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Advances to the next player in the list.
     * If the end of the list is reached, it wraps around to the first player.
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    /**
     * Prints the names of all players in the list to the console.
     */
    public void printPlayersNames(){
        for(Player player : players){
            System.out.println(player.getName());
        }
    }
}
