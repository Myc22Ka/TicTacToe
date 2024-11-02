package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.List;
import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Player;

/**
 * The GameInstance interface defines the contract for game instances, specifying the 
 * essential operations required to manage a game.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public interface GameInstance {     
    
    /**
     * Retrieves the command-line arguments passed to the game.
     * 
     * @return an array of command-line arguments
     */
    String[] getArgs();
    
    /**
     * Sets command-line arguments for the game.
     * 
     * @param args an array of command-line arguments
     */
    void setArgs(String[] args);
    
    /**
     * Returns whether the game is in fullscreen mode.
     * 
     * @return true if fullscreen is enabled, false otherwise
     */
    boolean getIsFullScreen();
    
    /**
     * Sets the fullscreen mode for the game.
     * 
     * @param isFullScreen true to enable fullscreen mode, false otherwise
     */
    void setIsFullScreen(boolean isFullScreen);
    
    /**
     * Sets the current game state.
     * 
     * @param gameState the GameState to set
     */
    void setGameState(GameState gameState);   
    
    
    /**
     * Gets the current game state.
     * 
     * @return the current GameState
     */
    GameState getGameState();
    
    
    /**
     * Gets the current round number.
     * 
     * @return the current round number
     */
    int getRound();
    
    /**
     * Sets the current round number.
     * 
     * @param round the round number to set
     */
    void setRound(int round);
    
    /**
     * Advances the game to the next round and clears the board.
     */
    void nextRound();
    
    /**
     * Returns a list of all players in the game.
     * 
     * @return a list of Player objects
     */
    List<Player> getPlayers();
    
    /**
     * Retrieves the current player in the game.
     *
     * @return the current Player
     */
    Player getCurrentPlayer();
    
    /**
     * Retrieves the game board.
     *
     * @return the Board instance representing the game board
     */
    Board getBoard();
    
    /**
     * Advances to the next player's turn.
     */
    void nextTurn();
    
        /**
     * Adds a player to the list after validating their name.
     *
     * @param player the Player instance to be added
     */
    void addPlayer(Player player);
}
