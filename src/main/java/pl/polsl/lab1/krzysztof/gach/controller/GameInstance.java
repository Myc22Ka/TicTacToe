package pl.polsl.lab1.krzysztof.gach.controller;

import javax.swing.JFrame;
import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Player;

/**
 * The GameInstance interface defines the contract for game instances, specifying the 
 * essential operations required to manage a game.
 * 
 * @author Krzysztof Gach
 * @version 1.2
 */
public interface GameInstance {     
    
    void setGameState(GameState gameState);
    
    
    GameState getGameState();
    
    /**
     * Starts the game with the given command-line arguments.
     *
     */
    int startGame(JFrame frame);
    
    /**
     * Ends the current game and performs any necessary cleanup.
     */
    void endGame();
    
    /**
     * Restarts the game, resetting any game state as necessary.
     */
    void restartGame();
    
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
